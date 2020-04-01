package com.sammy.ona.data;

import com.sammy.ona.model.Organisation;
import com.sammy.ona.model.OrganisationUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;

@Singleton
public class Repository {

    private final Provider<Requester>requesterProvider;
    private final Scheduler scheduler;
    private final List<Organisation> organisations = new ArrayList<>();
    private final Map<String, OrganisationUser> organisationUserHashMap = new HashMap<>();

    @Inject
    Repository(
            Provider<Requester> requesterProvider,
            @Named("network_scheduler") Scheduler scheduler) {
        this.requesterProvider = requesterProvider;
        this.scheduler = scheduler;
    }

    public Single<List<Organisation>> getOrganisations(String token) {
        return Maybe.concat(cachedOrganisations(), apiOrganisations(token))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    public Single<OrganisationUser> getUser(String token) {
        return Maybe.concat(cachedUsers(token), apiUsers(token))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    private Maybe<OrganisationUser> cachedUsers(String token) {
        return Maybe.create(e -> {
            if (organisationUserHashMap.containsKey(token)) {
                e.onSuccess(organisationUserHashMap.get(token));
            }
            e.onComplete();
        });
    }

    private Maybe<OrganisationUser> apiUsers(String token) {
        return requesterProvider.get().getUser(token)
                .doOnSuccess(contributors -> organisationUserHashMap.put(token, contributors))
                .toMaybe();
    }


    private Maybe<List<Organisation>> cachedOrganisations() {
        return Maybe.create(e -> {
            if (!organisations.isEmpty()) {
                e.onSuccess(organisations);
            }
            e.onComplete();
        });
    }

    private Maybe<List<Organisation>> apiOrganisations(String token) {
        return requesterProvider.get().getOrganisations(token)
                .doOnSuccess(repos -> {
                    organisations.clear();
                    organisations.addAll(repos);
                })
                .toMaybe();
    }
    public Single<Organisation> createOrganisation(String token,Organisation organisation){
        return requesterProvider.get().createOrganisation(token,organisation)
                .subscribeOn(scheduler);
    }
}
