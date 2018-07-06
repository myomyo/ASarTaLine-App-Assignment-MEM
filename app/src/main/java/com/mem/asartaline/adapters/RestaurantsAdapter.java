package com.mem.asartaline.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mem.asartaline.R;
import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.delegates.RestaurantDelegate;
import com.mem.asartaline.viewholders.RestaurantsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsViewHolder> {

    private RestaurantDelegate mRestaurantDelegate;

    private List<WarDeeVO> mWarDeeList;

    public RestaurantsAdapter(RestaurantDelegate restaurantDelegate) {
        this.mRestaurantDelegate = restaurantDelegate;
        mWarDeeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_restaurants, parent,false);
        return new RestaurantsViewHolder(view, mRestaurantDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {

        holder.bindData(mWarDeeList.get(position));
    }


    public void setWarDeeList(List<WarDeeVO> warDeeList) {
        this.mWarDeeList = warDeeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWarDeeList.size();
    }
}
