package com.example.java.testretrofit.base;

import android.content.Context;

import com.example.java.testretrofit.services.ReposService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


public abstract class BaseRemoteDataSource implements BaseDataSource {

    private static final String NEWS_ENDPOINT = "https://api.github.com/";
    protected ReposService reposService = null;
    @Override
    public void init(Context context) {
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NEWS_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                //.client(client)
                .build();
        //return retrofit.create(NewsApiInterface.class);
        reposService = retrofit.create(ReposService.class);
    }
}
