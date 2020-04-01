package com.sammy.ona.trending;

import com.sammy.ona.create.CreateOrganisationInterface;
import com.sammy.ona.model.Organisation;

import javax.inject.Inject;

import com.sammy.ona.data.Repository;
import com.sammy.ona.di.ScreenScope;
import com.sammy.ona.ui.ScreenNavigator;
import timber.log.Timber;

@ScreenScope
class OrganisationPresenter implements OrganisationAdapter.RepoClickedListener, CreateOrganisationInterface {

    private final OrganisationViewModel viewModel;
    private final Repository repoRepository;
    private final ScreenNavigator screenNavigator;

    @Inject
    OrganisationPresenter(
            OrganisationViewModel viewModel,
            Repository repoRepository,
            ScreenNavigator screenNavigator) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        loadRepos("d2c01abda5138831cc8f63b1e94ee053f204d0ad");
    }

    private void loadRepos(String token) {
        repoRepository.getOrganisations(token)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.organisationsUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Organisation repo) {
        Timber.d("Clicked Organisation: %s",repo.organisation());
        screenNavigator.goToOrganisationDetails(repo);
    }

    @Override
    public void fabClicked() {
        screenNavigator.goToCreateOrganisation();
    }

    public void goToCreateOrganisation() {
        screenNavigator.goToCreateOrganisation();
    }
}
