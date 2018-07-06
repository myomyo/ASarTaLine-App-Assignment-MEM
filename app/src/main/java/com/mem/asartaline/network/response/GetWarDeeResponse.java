package com.mem.asartaline.network.response;

import com.google.gson.annotations.SerializedName;
import com.mem.asartaline.data.vos.WarDeeVO;

import java.util.List;

public class GetWarDeeResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("astlWarDee")
    private List<WarDeeVO> astlWarDee;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<WarDeeVO> getAstlWarDee() {
        return astlWarDee;
    }

    public boolean isResponseOK(){
        return (code == 200 && astlWarDee != null);
    }
}
