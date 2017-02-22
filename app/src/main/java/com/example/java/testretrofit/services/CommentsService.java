package com.example.java.testretrofit.services;

import com.example.java.testretrofit.models.Comment;
import com.example.java.testretrofit.models.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

public interface CommentsService {
    @GET("/repos/{user}/{repo}/issues/comments")
    Single<List<Comment>> getComments(@Path("user") String user, @Path("repo") String repo);
}
