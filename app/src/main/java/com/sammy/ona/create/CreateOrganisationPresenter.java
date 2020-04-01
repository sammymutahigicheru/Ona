package com.sammy.ona.create;

import com.sammy.ona.data.Repository;
import com.sammy.ona.di.ScreenScope;
import com.sammy.ona.model.Organisation;

import javax.inject.Inject;

@ScreenScope
public class CreateOrganisationPresenter {
    private final CreateOrganisationViewModel viewModel;
    private final Repository repoRepository;

    @Inject
    CreateOrganisationPresenter(
            CreateOrganisationViewModel viewModel,
            Repository repoRepository) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
    }

    public void createOrganisation(String token, Organisation organisation) {
        repoRepository.createOrganisation(token,organisation)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.createOrganisationUpdated(), viewModel.onError());
    }
}
