package com.example.java.testretrofit.flow.repos;

import android.content.Context;
import android.view.View;

import com.example.java.testretrofit.models.Repo;

import java.util.List;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;


public class ReposRepository implements ReposDataSource {

    private ReposLocalDataSource localSource = new ReposLocalDataSource();
    private ReposRemoteDataSource remoteDataSource = new ReposRemoteDataSource();




    @Override
    public Single<List<Repo>> getRepos(String user) {

        return remoteDataSource.getRepos(user)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(list -> localSource.saveRepos(list))
                .onErrorResumeNext(localSource.getRepos(user));
    }

    @Override
    public void clearRepos() {

    }

    @Override
    public void init(Context context) {
        localSource.init(context);
        remoteDataSource.init(context);

    }
}
