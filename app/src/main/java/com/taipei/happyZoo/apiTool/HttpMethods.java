package com.taipei.happyZoo.apiTool;


import com.taipei.happyZoo.apiTool.model.HouseInfo;
import com.taipei.happyZoo.apiTool.model.PlantInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HttpMethods {

    private static HttpMethods mHttpMethods;

    public static HttpMethods getInstance() {
        if (mHttpMethods == null) {
            mHttpMethods = new HttpMethods();
        }
        return mHttpMethods;
    }

    public void getHouseIntroductionList(DisposableObserver<HouseInfo> subscriber
            ,int limit
            ,int offset) {

        ServiceGenerator.createService(DispatchService.class)
                .getHouseIntroductionList(ApiPub.value_scope_resource_aquire, ApiPub.rid_house_introduction, limit, offset)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getPlantInfoList(DisposableObserver<PlantInfo> subscriber
            ,String q
            ,int limit
            ,int offset) {

        ServiceGenerator.createService(DispatchService.class)
                .getPlantInfoList(ApiPub.value_scope_resource_aquire, ApiPub.rid_plant_information, q, limit, offset)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
