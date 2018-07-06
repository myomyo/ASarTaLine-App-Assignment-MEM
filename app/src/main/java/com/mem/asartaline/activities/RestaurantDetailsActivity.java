package com.mem.asartaline.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mem.asartaline.R;
import com.mem.asartaline.data.models.WarDeeModel;
import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.utils.WarDeeConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_details_image)
    ImageView ivImage;

    @BindView(R.id.tv_title)
    TextView tvName;

    @BindView(R.id.tv_description)
    TextView tvDesc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        ButterKnife.bind(this, this);

        String warDeeId = getIntent().getStringExtra(WarDeeConstants.WAR_DEE_ID);

        WarDeeVO warDee = WarDeeModel.getObjInstance().getWarDeeById(warDeeId);

        if(warDee != null){
            bindData(warDee);
        }

    }

    private void bindData(WarDeeVO warDee){

        tvName.setText(warDee.getName());

        if(!warDee.getGeneralTaste().isEmpty()){
            tvDesc.setText(warDee.getGeneralTaste().get(0).getTasteDesc());
        }

        if(!warDee.getImages().isEmpty()){
            Glide.with(ivImage.getContext())
                    .load(warDee.getImages().get(0))
                    .into(ivImage);
        }

    }
}
