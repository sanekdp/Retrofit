package com.example.java.testretrofit.views;

import android.content.Context;

import com.example.java.testretrofit.models.Repo;

import java.util.List;



public interface ReposView {

    void showRepos(List<Repo> list);

    Context getContext();
}
