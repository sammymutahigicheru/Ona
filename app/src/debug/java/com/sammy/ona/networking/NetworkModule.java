package com.sammy.ona.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Call.Factory provideOkHttp() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AppInterceptor())
                .build();
    }

    @Provides
    @Named("base_url")
    static String provideBaseUrl() {
        return "https://api.ona.io/api/v1/";
    }
}
