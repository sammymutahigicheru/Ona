package com.sammy.ona.base;

import com.sammy.ona.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        com.sammy.ona.data.ServiceModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);
}
