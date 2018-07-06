package com.mem.asartaline.network;

import com.mem.asartaline.network.response.GetWarDeeResponse;
import com.mem.asartaline.utils.WarDeeConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WarDeeApi {

    @FormUrlEncoded
    @POST(WarDeeConstants.GET_WAR_DEE)
    Call<GetWarDeeResponse> loadWarDeeList(
            @Field(WarDeeConstants.PARAM_ACCESS_TOKEN) String accessToken);
}
