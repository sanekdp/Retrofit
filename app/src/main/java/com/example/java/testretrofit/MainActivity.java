package com.example.java.testretrofit;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.java.testretrofit.adapters.RecyclerAdapter;
import com.example.java.testretrofit.flow.repos.ReposPresenter;
import com.example.java.testretrofit.models.Repo;
import com.example.java.testretrofit.views.ReposView;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity implements ReposView {

    protected  Toolbar toolbar = null;
    protected RecyclerView recyclerView = null;
    private Observable<List<Repo>> queryObservable = null;
    private RecyclerAdapter mAdapter = new RecyclerAdapter(new String[0]);
    private SearchView searchView = null;
    private ReposPresenter presenter = new ReposPresenter();
    private String[] mDataSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        presenter.onAttach(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
////                this,
////                RecyclerView.VERTICAL,
////                false);
//        //recyclerView.setLayoutManager(layoutManager);
//        //List<Repo> list = new ArrayList<Repo>();
//        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);

        recyclerView.setLayoutManager(layoutManager);

        //presenter = new ReposPresenter();
        //presenter.onAttach(this);
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
        mDataSource = new String[list.size()];
        for(Repo repo : list){
            mDataSource[list.indexOf(repo)] = repo.getName();
        }

        mAdapter.setDataSource(mDataSource);
        //recyclerView.setAdapter(mAdapter);
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
