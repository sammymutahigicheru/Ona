package com.sammy.ona.details;

import javax.inject.Inject;
import javax.inject.Named;

import com.sammy.ona.data.Repository;
import com.sammy.ona.di.ScreenScope;

@ScreenScope
class OrganisationDetailsPresenter {

    @Inject
    OrganisationDetailsPresenter(
            Repository repoRepository,
            OrganisationDetailsViewModel viewModel) {
        repoRepository.getOrganisations("d2c01abda5138831cc8f63b1e94ee053f204d0ad")
                .doOnSuccess(viewModel.processOrganisation())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getUser("d2c01abda5138831cc8f63b1e94ee053f204d0ad")
                        .doOnError(viewModel.organisationUserError()))
                .subscribe(viewModel.processOrganisationUser(), throwable -> {
                    // We handle logging in the view model
                });
    }
}
