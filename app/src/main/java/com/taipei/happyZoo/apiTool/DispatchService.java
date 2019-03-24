package com.taipei.happyZoo.apiTool;


import com.taipei.happyZoo.apiTool.model.HouseInfo;
import com.taipei.happyZoo.apiTool.model.PlantInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DispatchService {

    @GET(ApiPub.url_path)
    Observable<HouseInfo>
    getHouseIntroductionList(@Query(ApiPub.query_scope) String scope,
                             @Query(ApiPub.query_rid)   String rid,
                             @Query(ApiPub.query_limit) int limit,
                             @Query(ApiPub.query_offset)int offset);

    @GET(ApiPub.url_path)
    Observable<PlantInfo>
    getPlantInfoList(@Query(ApiPub.query_scope) String scope,
                     @Query(ApiPub.query_rid)   String rid,
                     @Query(ApiPub.query_q)     String q,
                     @Query(ApiPub.query_limit) int limit,
                     @Query(ApiPub.query_offset)int offset);

}
