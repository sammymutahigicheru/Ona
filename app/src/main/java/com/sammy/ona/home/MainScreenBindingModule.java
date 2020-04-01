package com.sammy.ona.home;

import com.bluelinelabs.conductor.Controller;
import com.sammy.ona.create.CreateOrganisationComponent;
import com.sammy.ona.create.CreateOrganisationController;
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
        CreateOrganisationComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(OrganisationController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindOrganisationInjector(OrganisationComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(OrganisationDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindOrganisationDetailsInjector(OrganisationDetailsComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(CreateOrganisationController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindCreateOrganisationInjector(CreateOrganisationComponent.Builder builder);
}
