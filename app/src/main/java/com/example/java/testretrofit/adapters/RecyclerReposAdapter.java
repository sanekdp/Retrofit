package com.example.java.testretrofit.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.java.testretrofit.R;
import com.example.java.testretrofit.models.Repo;

import java.util.List;

public class RecyclerReposAdapter extends RecyclerView.Adapter<RecyclerReposAdapter.ViewHolder> {


    private List<Repo> dataSource = null;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mNameRepo = null;

        public ViewHolder(View itemView) {
            super(itemView);
            mNameRepo = (TextView) itemView.findViewById(R.id.tv_recycler_item);
        }
    }

    public RecyclerReposAdapter(List<Repo> dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNameRepo.setText(dataSource.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dataSource == null ? 0 : dataSource.size();
    }

}
