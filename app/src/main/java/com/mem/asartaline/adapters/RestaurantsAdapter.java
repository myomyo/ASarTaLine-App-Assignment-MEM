package com.mem.asartaline.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mem.asartaline.R;
import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.delegates.RestaurantDelegate;
import com.mem.asartaline.viewholders.BaseWarDeeViewHolder;
import com.mem.asartaline.viewholders.RestaurantsViewHolder;
import com.mem.asartaline.viewholders.WarDeeMenuViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends BaseRecyclerAdapter<BaseWarDeeViewHolder,WarDeeVO> {

    private RestaurantDelegate mRestaurantDelegate;

    private List<WarDeeVO> mWarDeeList;

    private static final int VT_MENU =1000;
    private static final int VT_WARDEE =2000;

    public RestaurantsAdapter(RestaurantDelegate restaurantDelegate) {
        this.mRestaurantDelegate = restaurantDelegate;
        mWarDeeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseWarDeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == VT_MENU){
            View view = layoutInflater.inflate(R.layout.view_holder_wartee_menu, parent,false);
            return new WarDeeMenuViewHolder(view);
        }else if(viewType == VT_WARDEE) {
            View view = layoutInflater.inflate(R.layout.view_holder_restaurants, parent, false);
            return new RestaurantsViewHolder(view, mRestaurantDelegate);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VT_MENU;
        }else {
            return VT_WARDEE;
        }
    }
}
