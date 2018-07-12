package com.mem.asartaline.network.response;

import com.google.gson.annotations.SerializedName;
import com.mem.asartaline.data.vos.WarDeeVO;

import java.util.List;

public class SearchWarDeeResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("tasteType")
    private String tasteType;

    @SerializedName("suited")
    private String suited;

    @SerializedName("minPrice")
    private double minPrice;

    @SerializedName("maxPrice")
    private int maxPrice;

    @SerializedName("isNearBy")
    private int isNearBy;

    @SerializedName("currentTownship")
    private String currentTownship;

    @SerializedName("currentTLat")
    private double currentTLat;

    @SerializedName("currentLng")
    private double currentLng;

    @SerializedName("searchResults")
    private List<WarDeeVO> searchResults;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getTasteType() {
        return tasteType;
    }

    public String getSuited() {
        return suited;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getIsNearBy() {
        return isNearBy;
    }

    public String getCurrentTownship() {
        return currentTownship;
    }

    public double getCurrentTLat() {
        return currentTLat;
    }

    public double getCurrentLng() {
        return currentLng;
    }

    public List<WarDeeVO> getSearchResults() {
        return searchResults;
    }
}
