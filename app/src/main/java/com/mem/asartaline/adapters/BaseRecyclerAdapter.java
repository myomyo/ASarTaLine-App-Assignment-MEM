package com.mem.asartaline.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.mem.asartaline.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends BaseViewHolder<D>, D> extends RecyclerView.Adapter<VH> { // VH : view holder, D: data

    protected List<D> mData;

    public BaseRecyclerAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setWarDeeList(List<D> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void appendWarDeeList(List<D> data){

        mData.addAll(data);
        notifyDataSetChanged();
    }
}