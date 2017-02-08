package com.example.java.testretrofit.flow.repos;

import com.example.java.testretrofit.base.BaseRemoteDataSource;
import com.example.java.testretrofit.models.Repo;

import java.util.List;

import rx.Single;



public class ReposRemoteDataSource extends BaseRemoteDataSource implements ReposDataSource {
    @Override
    public Single<List<Repo>> getRepos(String user) {
        return reposService.getRepos(user);
    }

    @Override
    public void clearRepos() {

    }
}
