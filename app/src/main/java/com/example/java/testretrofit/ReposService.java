package com.example.java.testretrofit;

import com.example.java.testretrofit.models.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;



public interface ReposService {
    @GET("/users/{user}/repos")
    Single<List<Repo>> getRepos(@Path("user") String user);
}
