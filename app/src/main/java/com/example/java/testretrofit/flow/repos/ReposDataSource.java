package com.example.java.testretrofit.flow.repos;

import com.example.java.testretrofit.base.BaseDataSource;
import com.example.java.testretrofit.models.Repo;

import java.util.List;

import rx.Single;

public interface ReposDataSource extends BaseDataSource {
    Single<List<Repo>> getRepos(String user);

    void clearRepos();
}
