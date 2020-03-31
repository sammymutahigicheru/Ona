package com.sammy.ona.data;

import com.sammy.ona.model.Organisation;
import com.sammy.ona.model.OrganisationUser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class Requester {

    private final Service service;

    @Inject
    Requester(Service service) {
        this.service = service;
    }

    Single<List<Organisation>> getOrganisations(String token) {
        return service.getOrganisations("Token "+token);
    }

    Single<OrganisationUser> getUser(String token){
        return service.getUser("Token "+token);
    }

}
