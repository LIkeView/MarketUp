package com.example.marketup.api;

import com.davemorrissey.labs.subscaleview.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://www.eatfresh.cc/arch_daily/api/";
    private static RetrofitClient mInstance;
    private static Retrofit retrofit = null;

    public static synchronized RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }

    public static Retrofit getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit==null) {
            OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
            okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
            okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
            okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                okhttpClientBuilder.addInterceptor(logging);
            }
            OkHttpClient client = okhttpClientBuilder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client( client )
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}