package com.sammy.ona.details;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.sammy.ona.model.Organisation;
import com.sammy.ona.model.OrganisationUser;

import java.util.List;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.R;
import com.sammy.ona.di.ScreenScope;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class OrganisationDetailsViewModel {

    private final BehaviorRelay<OrganisationState> detailStateRelay = BehaviorRelay.create();
    private final BehaviorRelay<OrganisationUserState> organisationUserStateBehaviorRelay = BehaviorRelay.create();

    @Inject
    OrganisationDetailsViewModel() {
        detailStateRelay.accept(OrganisationState.builder().loading(true).build());
        organisationUserStateBehaviorRelay.accept(OrganisationUserState.builder().loading(true).build());
    }

    Observable<OrganisationState> details() {
        return detailStateRelay;
    }

    Observable<OrganisationUserState> contributors() {
        return organisationUserStateBehaviorRelay;
    }

    Consumer<List<Organisation>> processOrganisation() {
        return organisations -> detailStateRelay.accept(
                OrganisationState.builder()
                        .loading(false)
                        .name(organisations.iterator().next().organisation())
                        .description(organisations.iterator().next().country())
                        .createdDate(organisations.iterator().next().dateModified())
                        .build()
        );
    }

    Consumer<OrganisationUser> processOrganisationUser() {
        return organisationUser -> organisationUserStateBehaviorRelay.accept(
                OrganisationUserState.builder()
                        .loading(false)
                        .contributors(organisationUser)
                        .build());
    }

    Consumer<Throwable> detailsError() {
        return throwable -> {
            Timber.e(throwable, "Error loading repo details");
            detailStateRelay.accept(
                    OrganisationState.builder()
                            .loading(false)
                            .errorRes(R.string.api_error_single_organisation)
                            .build()
            );
        };
    }

    Consumer<Throwable> organisationUserError() {
        return throwable -> {
            Timber.e(throwable, "Error loading contributors");
            organisationUserStateBehaviorRelay.accept(
                    OrganisationUserState.builder()
                            .loading(false)
                            .errorRes(R.string.api_error_user)
                            .build());
        };
    }
}
