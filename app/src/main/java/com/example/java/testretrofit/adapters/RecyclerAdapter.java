package com.example.java.testretrofit.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.java.testretrofit.R;
import com.example.java.testretrofit.models.Repo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Repo> mDataset = new ArrayList<Repo>();
    private View.OnClickListener mOnItemClickListener;

    public RecyclerAdapter() {
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        mOnItemClickListener = onClickListener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Repo repo = mDataset.get(position);
        holder.bind(repo);
        holder.mTextView.setText(repo.getName());
        holder.itemView.setOnClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setDataSource(List<Repo> dataSource) {
        this.mDataset = dataSource;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private Repo mRepo;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
        }

        private  void bind(@NonNull Repo repo){
            mRepo = repo;
        }

        public Repo getRepo() {
            return mRepo;
        }
    }
}
