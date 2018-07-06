package com.mem.asartaline.data.models;

import com.mem.asartaline.data.vos.WarDeeVO;
import com.mem.asartaline.events.SuccessGetWarDeeEvent;
import com.mem.asartaline.network.RetrofitDataAgentImpl;
import com.mem.asartaline.network.WarDeeDataAgent;
import com.mem.asartaline.utils.WarDeeConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarDeeModel {

    private static WarDeeModel objInstance;

    private WarDeeDataAgent mDataAgent;

    private Map<String, WarDeeVO> mWarDeeMap;

    private WarDeeModel() {
        mDataAgent = RetrofitDataAgentImpl.getObjInstance();
        mWarDeeMap = new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static WarDeeModel getObjInstance() {
        if(objInstance == null){
            objInstance = new WarDeeModel();
        }
        return objInstance;
    }

    public void loadWarDeeList(){
        mDataAgent.loadWarDeeList(WarDeeConstants.ACCESS_TOKEN, false);
    }

    public WarDeeVO getWarDeeById(String warDeeId){
        return mWarDeeMap.get(warDeeId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNews(SuccessGetWarDeeEvent event) {

        setDataIntoRepository(event.getWarDeeList());

    }

    private void setDataIntoRepository(List<WarDeeVO> warDeeList) {
        for (WarDeeVO warDee : warDeeList) {
            mWarDeeMap.put(warDee.getWarDeeId(), warDee);
        }

    }
}
