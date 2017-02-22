package com.example.java.testretrofit.views;

import android.content.Context;

import com.example.java.testretrofit.models.Comment;

import java.util.List;


public interface CommentsView {

    void showComments(List<Comment> list);

    Context getContext();
}
