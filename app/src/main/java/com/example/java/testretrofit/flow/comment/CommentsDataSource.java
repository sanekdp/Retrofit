package com.example.java.testretrofit.flow.comment;

import com.example.java.testretrofit.base.BaseDataSource;
import com.example.java.testretrofit.models.Comment;

import java.util.List;

import rx.Single;


public interface CommentsDataSource extends BaseDataSource {
    Single<List<Comment>> getComments(String user, String repo);

    //void clearRepos();
}
