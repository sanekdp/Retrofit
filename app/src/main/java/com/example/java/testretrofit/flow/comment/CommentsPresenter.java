package com.example.java.testretrofit.flow.comment;

import android.content.Context;

import com.example.java.testretrofit.models.Comment;
import com.example.java.testretrofit.views.CommentsView;

import java.util.List;

import rx.Single;
import rx.internal.util.SubscriptionList;


public class CommentsPresenter implements CommentsDataSource {

    CommentsRepository commentsRepository = new CommentsRepository();
    CommentsView commentsView = null;
    SubscriptionList subscriptionList = new SubscriptionList();

    public void onAttach(CommentsView view){
        commentsView = view;
        commentsRepository.init(view.getContext());
    }

    public void onDetach(){
        subscriptionList.unsubscribe();
    }


    @Override
    public Single<List<Comment>> getComments(String user, String repo) {
        Single<List<Comment>> single = commentsRepository.getComments(user, repo);
        single.subscribe(list -> commentsView.showComments(list),
                throwable -> throwable.printStackTrace());
        return single;
    }

    @Override
    public void init(Context context) {

    }
}
