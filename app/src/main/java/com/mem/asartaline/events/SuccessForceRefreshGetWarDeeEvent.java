package com.mem.asartaline.events;

import com.mem.asartaline.data.vos.WarDeeVO;

import java.util.List;

public class SuccessForceRefreshGetWarDeeEvent extends SuccessGetWarDeeEvent {

    public SuccessForceRefreshGetWarDeeEvent(List<WarDeeVO> warDeeList) {
        super(warDeeList);
    }
}
