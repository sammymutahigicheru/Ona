package com.sammy.ona.data;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@Module
public abstract class ServiceModule {

    @Provides
    @Singleton
    static Service provideRepoService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

    @Provides
    @Named("network_scheduler")
    static Scheduler provideNetworkScheduler() {
        return Schedulers.io();
    }
}
