package com.sammy.ona.home;

import com.bluelinelabs.conductor.Controller;
import com.sammy.ona.details.OrganisationDetailsComponent;
import com.sammy.ona.details.OrganisationDetailsController;
import com.sammy.ona.trending.OrganisationComponent;
import com.sammy.ona.trending.OrganisationController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

import com.sammy.ona.di.ControllerKey;

@Module(subcomponents = {
        OrganisationComponent.class,
        OrganisationDetailsComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(OrganisationController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(OrganisationComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(OrganisationDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(OrganisationDetailsComponent.Builder builder);
}
