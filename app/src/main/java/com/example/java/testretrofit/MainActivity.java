package com.example.java.testretrofit;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.java.testretrofit.adapters.RecyclerAdapter;
import com.example.java.testretrofit.flow.repos.ReposPresenter;
import com.example.java.testretrofit.models.Repo;
import com.example.java.testretrofit.views.ReposView;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity implements ReposView {

    protected  Toolbar toolbar = null;
    protected RecyclerView recyclerView = null;
    private Observable<List<Repo>> queryObservable = null;
    private RecyclerAdapter mAdapter = new RecyclerAdapter();
    private SearchView searchView = null;
    private ReposPresenter presenter = new ReposPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        presenter.onAttach(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search...");

        RxSearchView.queryTextChanges(searchView)
                .debounce(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(query -> presenter.getRepos(query.toString()));

        return true;
    }

    @Override
    public void showRepos(List<Repo> list) {
        mAdapter.setDataSource(list);
        mAdapter.setOnItemClickListener(view -> {

            Toast.makeText(this, "Text", Toast.LENGTH_SHORT).show();
            final RecyclerAdapter.ViewHolder holder = (RecyclerAdapter.ViewHolder) recyclerView.findContainingViewHolder(view);
            if (holder == null) return;
            final Repo repo = holder.getRepo();

            int primaryColor = ContextCompat.getColor(
                    this,
                    R.color.colorPrimary);
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                    .setToolbarColor(primaryColor)
                    .setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left)
                    .setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right)
                    .build();
            customTabsIntent.launchUrl(this, Uri.parse(repo.getHtmlUrl()));
        });
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
