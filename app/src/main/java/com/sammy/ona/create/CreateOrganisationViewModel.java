package com.sammy.ona.create;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.sammy.ona.di.ScreenScope;
import com.sammy.ona.model.Organisation;

import java.util.List;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.R;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
public class CreateOrganisationViewModel {
    private final BehaviorRelay<Organisation> createOrganisationRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    CreateOrganisationViewModel(
    ) {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<Organisation> createOrganisation() {
        return createOrganisationRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<Organisation> createOrganisationUpdated() {
        errorRelay.accept(-1);
        return createOrganisationRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_error_create_organisation);
        };
    }
}
