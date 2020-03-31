package com.sammy.ona.details;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import com.sammy.ona.di.ScreenScope;

@ScreenScope
@Subcomponent
public interface OrganisationDetailsComponent extends AndroidInjector<OrganisationDetailsController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<OrganisationDetailsController> {

        @BindsInstance
        public abstract void bindRepoOwner(@Named("repo_owner") String repoOwner);

        @BindsInstance
        public abstract void bindRepoName(@Named("repo_name") String repoName);

        @Override
        public void seedInstance(OrganisationDetailsController instance) {
            bindRepoOwner(instance.getArgs().getString(OrganisationDetailsController.ORGANISATION_OWNER_KEY));
            bindRepoName(instance.getArgs().getString(OrganisationDetailsController.ORGANISATION_NAME_KEY));
        }
    }
}
