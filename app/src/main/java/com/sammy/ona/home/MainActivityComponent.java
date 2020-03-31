package com.sammy.ona.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import com.sammy.ona.di.ActivityScope;
import com.sammy.ona.ui.NavigationModule;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
