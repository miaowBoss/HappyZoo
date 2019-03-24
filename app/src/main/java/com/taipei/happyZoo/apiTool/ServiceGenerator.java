package com.taipei.happyZoo.apiTool;

import com.taipei.happyZoo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    private static final int DEFAULT_TIMEOUT = 20;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(ApiPub.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //--設置超時時間
        httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder
                .baseUrl(ApiPub.API_SERVER_URL)
                .client(client).build();
        return retrofit.create(serviceClass);
    }
}
