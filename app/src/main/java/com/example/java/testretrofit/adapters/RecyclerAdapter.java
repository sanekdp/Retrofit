package com.example.java.testretrofit.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.java.testretrofit.R;


//public class RecyclerReposAdapter extends RecyclerView.Adapter<RecyclerReposAdapter.ViewHolder> {
//
//
//    private String[] dataSource = null;
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView mNameRepo = null;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mNameRepo = (TextView) itemView.findViewById(R.id.tv_recycler_item);
//        }
//    }
//
//    public RecyclerReposAdapter(String[] dataSource) {
//        this.dataSource = dataSource;
//        //notifyDataSetChanged();
//    }
//
//    public void setDataSource(String[] dataSource) {
//        this.dataSource = dataSource;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recycler_item, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mNameRepo.setText(dataSource[position]);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataSource == null ? 0 : dataSource.length;
//    }
//




public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] mDataset;

    // Конструктор
    public RecyclerAdapter(String[] dataset) {
        mDataset = dataset;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
        }
    }

    public void setDataSource(String[] dataSource) {
        this.mDataset = dataSource;
        notifyDataSetChanged();
    }

}
