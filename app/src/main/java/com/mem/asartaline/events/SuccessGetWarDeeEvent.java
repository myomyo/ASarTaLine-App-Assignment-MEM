package com.mem.asartaline.events;

import com.mem.asartaline.data.vos.WarDeeVO;


import java.util.List;

public class SuccessGetWarDeeEvent {

    private List<WarDeeVO> warDeeList;

    public SuccessGetWarDeeEvent(List<WarDeeVO> warDeeList) {
        this.warDeeList = warDeeList;
    }

    public List<WarDeeVO> getWarDeeList() {
        return warDeeList;
    }
}
