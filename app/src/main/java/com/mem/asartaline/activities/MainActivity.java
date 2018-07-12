package com.mem.asartaline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mem.asartaline.R;
import com.mem.asartaline.activities.BaseActivity;
import com.mem.asartaline.activities.RestaurantDetailsActivity;
import com.mem.asartaline.adapters.RestaurantsAdapter;
import com.mem.asartaline.data.models.WarDeeModel;
import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.delegates.RestaurantDelegate;
import com.mem.asartaline.events.ApiErrorEvent;
import com.mem.asartaline.events.SuccessGetWarDeeEvent;
import com.mem.asartaline.utils.WarDeeConstants;
import com.mem.asartaline.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RestaurantDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_restaurants)
    RecyclerView rvRestaurants;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private RestaurantsAdapter mRestaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        mRestaurantAdapter = new RestaurantsAdapter(this);
        rvRestaurants.setAdapter(mRestaurantAdapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        WarDeeModel.getObjInstance().loadWarDeeList();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WarDeeModel.getObjInstance().loadWarDeeList();
            }
        });

        // Empty view
        vpEmpty.setEmptyData(R.drawable.placeholder_image, getString(R.string.empty_msg));


    }

    @Override
    public void onTapViewDetails(WarDeeVO warDee) {
        Intent intent = new Intent(getApplicationContext(), RestaurantDetailsActivity.class);
        intent.putExtra(WarDeeConstants.WAR_DEE_ID, warDee.getWarDeeId());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetWarDee(SuccessGetWarDeeEvent event) {
        Log.d("onSuccessGetWarDee", "onSuccessGetWarDee :" + event.getWarDeeList().size());
        mRestaurantAdapter.setWarDeeList(event.getWarDeeList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetWarDee(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(swipeRefreshLayout, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        vpEmpty.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetWarDee(SuccessGetWarDeeEvent event){
        mRestaurantAdapter.setWarDeeList(event.getWarDeeList());
        Log.d("onSuccessForceRefresh", "onSuccessForceRefreshGetWarDee : "+event.getWarDeeList().size());
        swipeRefreshLayout.setRefreshing(false);
    }
}
