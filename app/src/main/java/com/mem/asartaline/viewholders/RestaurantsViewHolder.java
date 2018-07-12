package com.mem.asartaline.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mem.asartaline.R;
import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.delegates.RestaurantDelegate;
import com.mem.asartaline.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsViewHolder extends BaseWarDeeViewHolder {

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

    @BindView(R.id.tv_food_cost)
    TextView tvCost;

    public RestaurantsViewHolder(View itemView, RestaurantDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mRestaurantDelegate = delegate;

        tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRestaurantDelegate.onTapViewDetails(mWarDee);
            }
        });

    }

    @Override
    public void bindData(WarDeeVO warDee) {
        mWarDee = warDee;
        tvName.setText(warDee.getName());

        if (!warDee.getGeneralTaste().isEmpty()) {
            tvFoodType.setText(warDee.getGeneralTaste().get(0).getTaste());
        }

        if (!warDee.getImages().isEmpty()) {
            GlideApp.with(ivImage.getContext())
                    .load(warDee.getImages().get(0))
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.empty_image)
                    .into(ivImage);
        }

        tvCost.setText(tvCost.getContext().getString(R.string.format_price_limit, warDee.getPriceRangeMin(), warDee.getPriceRangeMax()));

    }
}
