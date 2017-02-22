package com.example.java.testretrofit.flow.comment;

import com.example.java.testretrofit.base.BaseLocalDataSource;
import com.example.java.testretrofit.models.Comment;

import java.util.List;

import rx.Single;

public class CommentsLocalDataSource extends BaseLocalDataSource implements CommentsDataSource {
    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        return null;
    }
}
