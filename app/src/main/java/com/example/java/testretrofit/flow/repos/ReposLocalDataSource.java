package com.example.java.testretrofit.flow.repos;

import com.example.java.testretrofit.base.BaseLocalDataSource;
import com.example.java.testretrofit.models.Repo;

import java.util.List;

import io.realm.RealmResults;
import rx.Single;



public class ReposLocalDataSource extends BaseLocalDataSource implements ReposDataSource {
    @Override
    public Single<List<Repo>> getRepos(String user) {
        return Single.create(subscriber -> {
            realm.executeTransaction(innerRealm -> {
                RealmResults list = innerRealm.where(Repo.class).contains("name", user).findAll();
                if (list == null)
                    subscriber.onError(new Exception("vse ploho"));
                else {
                    List repos = innerRealm.copyFromRealm(list);
                    if (repos != null)
                        subscriber.onSuccess(list);
                    else
                        subscriber.onError(new Exception("vse ploho2"));
                }
            });
        });
    }

    @Override
    public void clearRepos() {

    }

    public Single<List<Repo>> saveRepos(List<Repo> list){

        realm.executeTransaction(query -> {
           realm.copyToRealm(list);
        });
        return Single.just(list);
    }
}
