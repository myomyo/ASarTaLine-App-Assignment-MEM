package com.mem.asartaline.data.vos;

import java.util.ArrayList;
import java.util.List;

public class WarDeeVO {

    private String warDeeId;

    private String name;

    private List<String> images;

    private List<TasteVO> generalTaste;

    private List<SuitedForVO> suitedFor;

    private int priceRangeMin;

    private int priceRangeMax;

    private List<WarDeeVO> matchWarDeeList;

    private List<ShopByDistanceVO> shopByDistance;

    private List<ShopByPopularity> shopByPopularity;

    public String getWarDeeId() {
        return warDeeId;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        if(images == null){
            return new ArrayList<>();
        }
        return images;
    }

    public List<TasteVO> getGeneralTaste() {
        return generalTaste;
    }

    public List<SuitedForVO> getSuitedFor() {
        return suitedFor;
    }

    public int getPriceRangeMin() {
        return priceRangeMin;
    }

    public int getPriceRangeMax() {
        return priceRangeMax;
    }

    public List<WarDeeVO> getMatchWarDeeList() {
        return matchWarDeeList;
    }

    public List<ShopByDistanceVO> getShopByDistance() {
        return shopByDistance;
    }

    public List<ShopByPopularity> getShopByPopularity() {
        return shopByPopularity;
    }
}