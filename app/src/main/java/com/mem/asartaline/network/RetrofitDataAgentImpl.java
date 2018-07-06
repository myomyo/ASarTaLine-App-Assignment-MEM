package com.mem.asartaline.network;

import com.mem.asartaline.events.ApiErrorEvent;
import com.mem.asartaline.events.SuccessForceRefreshGetWarDeeEvent;
import com.mem.asartaline.events.SuccessGetWarDeeEvent;
import com.mem.asartaline.network.response.GetWarDeeResponse;
import com.mem.asartaline.utils.WarDeeConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements WarDeeDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;

    private WarDeeApi mWarDeeApi;

    public RetrofitDataAgentImpl() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WarDeeConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mWarDeeApi = retrofit.create(WarDeeApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadWarDeeList(String accessToken, final boolean isForceRefresh) {

        Call<GetWarDeeResponse> loadWarDeeCall = mWarDeeApi.loadWarDeeList(accessToken);
        loadWarDeeCall.enqueue(new Callback<GetWarDeeResponse>() {
            @Override
            public void onResponse(Call<GetWarDeeResponse> call, Response<GetWarDeeResponse> response) {
                GetWarDeeResponse warDeeResponse = response.body();
                if (warDeeResponse != null && warDeeResponse.isResponseOK()) {
                    if (isForceRefresh) {
                        SuccessForceRefreshGetWarDeeEvent event = new SuccessForceRefreshGetWarDeeEvent(warDeeResponse.getAstlWarDee());
                        EventBus.getDefault().post(event);
                    } else {
                        SuccessGetWarDeeEvent event = new SuccessGetWarDeeEvent(warDeeResponse.getAstlWarDee());
                        EventBus.getDefault().post(event);
                    }

                } else {
                    if (warDeeResponse == null) {
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(warDeeResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetWarDeeResponse> call, Throwable t) {

                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }

}
