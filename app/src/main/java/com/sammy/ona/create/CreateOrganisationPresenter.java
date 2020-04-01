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
      //  loadRepos("d2c01abda5138831cc8f63b1e94ee053f204d0ad");
    }

    public void createOrganisation(String token, Organisation organisation) {
        repoRepository.createOrganisation(token,organisation)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.createOrganisationUpdated(), viewModel.onError());
    }
}
