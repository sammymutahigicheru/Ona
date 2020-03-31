package com.sammy.ona.trending;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import com.sammy.ona.di.ScreenScope;

@ScreenScope
@Subcomponent
public interface OrganisationComponent extends AndroidInjector<OrganisationController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<OrganisationController> {

    }
}
