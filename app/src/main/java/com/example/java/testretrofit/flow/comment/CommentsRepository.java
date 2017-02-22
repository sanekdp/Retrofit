package com.example.java.testretrofit.flow.comment;

import android.content.Context;

import com.example.java.testretrofit.models.Comment;

import java.util.List;

import rx.Single;

public class CommentsRepository implements CommentsDataSource {

    private CommentsLocalDataSource localSource = new CommentsLocalDataSource();
    private CommentsRemoteDataSource remoteDataSource = new CommentsRemoteDataSource();

    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        return null;
    }

    @Override
    public void init(Context context) {
        localSource.init(context);
        remoteDataSource.init(context);
    }
}
