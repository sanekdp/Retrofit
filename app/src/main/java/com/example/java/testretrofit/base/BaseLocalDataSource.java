package com.example.java.testretrofit.base;

import android.content.Context;

import io.realm.Realm;



public abstract class BaseLocalDataSource implements BaseDataSource {
    protected Realm realm = null;

    @Override
    public void init(Context context) {

    }
}
