package com.example.java.testretrofit.flow.repos;

import android.content.Context;
import android.view.View;

import com.example.java.testretrofit.models.Repo;
import com.example.java.testretrofit.views.ReposView;

import java.util.List;

import rx.Single;
import rx.Subscription;
import rx.functions.Action1;
import rx.internal.util.SubscriptionList;


public class ReposPresenter implements ReposDataSource {

    ReposRepository reposRepository = new ReposRepository();
    ReposView reposView = null;
    SubscriptionList subscriptionList = new SubscriptionList();


    public void onAttach(ReposView view){
        reposView = view;
        reposRepository.init(view.getContext());
    }

    public void onDetach(){
        subscriptionList.unsubscribe();
    }

    @Override
    public Single<List<Repo>> getRepos(String user) {
        //user = "sanekdp";
        Single<List<Repo>> single = reposRepository.getRepos(user);
        single.subscribe(new Action1<List<Repo>>() {
                             @Override
                             public void call(List<Repo> list) {
                                 reposView.showRepos(list);
                             }
                         },
                throwable -> throwable.printStackTrace()); // Throwable::printStackTrace);
        return single;
    }

    @Override
    public void clearRepos() {
        reposRepository.clearRepos();
    }

    @Override
    public void init(Context context) {

    }
}
