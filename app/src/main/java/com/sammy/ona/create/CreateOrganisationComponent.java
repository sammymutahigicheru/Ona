package com.sammy.ona.create;

import com.sammy.ona.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface CreateOrganisationComponent extends AndroidInjector<CreateOrganisationController> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CreateOrganisationController>{
        @Override
        public void seedInstance(CreateOrganisationController instance) {

        }
    }
}
