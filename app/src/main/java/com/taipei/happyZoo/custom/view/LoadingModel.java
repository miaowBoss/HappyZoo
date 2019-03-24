package com.taipei.happyZoo.custom.view;

import com.taipei.happyZoo.page.houseDetail.HouseDetailAdapter;

public class LoadingModel implements HouseDetailAdapter.DetailType {
    @Override
    public int getType() {
        return 0;
    }
}
