package com.sammy.ona.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.sammy.ona.model.Organisation;

import java.util.List;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.R;
import com.sammy.ona.di.ScreenScope;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class OrganisationViewModel {

    private final BehaviorRelay<List<Organisation>> organisationRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    OrganisationViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<List<Organisation>> organisations() {
        return organisationRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<List<Organisation>> organisationsUpdated() {
        errorRelay.accept(-1);
        return organisationRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_error_repos);
        };
    }
}
