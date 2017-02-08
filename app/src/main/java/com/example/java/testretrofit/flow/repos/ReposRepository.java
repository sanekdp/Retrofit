package com.example.java.testretrofit.flow.repos;

import android.content.Context;

import com.example.java.testretrofit.models.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by java on 08.02.2017.
 */

public class ReposRepository implements ReposDataSource {

    private ReposLocalDataSource localSource = null;
    private ReposRemoteDataSource remoteDataSource = null;

    @Override
    public Single<List<Repo>> getRepos(String user) {
        return null;
    }

    @Override
    public void clearRepos() {

    }

    @Override
    public void init(Context context) {

    }
}
