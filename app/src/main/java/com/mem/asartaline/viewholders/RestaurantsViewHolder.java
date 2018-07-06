package com.mem.asartaline.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mem.asartaline.R;
import com.mem.asartaline.data.vos.TasteVO;
import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.delegates.RestaurantDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsViewHolder extends RecyclerView.ViewHolder {

    private RestaurantDelegate mRestaurantDelegate;
    private WarDeeVO mWarDee;

    @BindView(R.id.tv_view_details)
    TextView tvViewDetails;

    @BindView(R.id.tv_restaurant_name)
    TextView tvName;

    @BindView(R.id.tv_food_type)
    TextView tvFoodType;

    @BindView(R.id.tv_restaurant_photo)
    ImageView ivImage;

    public RestaurantsViewHolder(View itemView, RestaurantDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mRestaurantDelegate = delegate;

        tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRestaurantDelegate.onTapViewDetails(mWarDee);
            }
        });

    }

    public void bindData(WarDeeVO warDee){
        mWarDee = warDee;
        tvName.setText(warDee.getName());

        if(!warDee.getGeneralTaste().isEmpty()){
            tvFoodType.setText(warDee.getGeneralTaste().get(0).getTaste());
        }

        if(!warDee.getImages().isEmpty()){
            Glide.with(ivImage.getContext())
                    .load(warDee.getImages().get(0))
                    .into(ivImage);
        }

    }
}
