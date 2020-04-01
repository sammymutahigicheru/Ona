package com.sammy.ona.data;

import com.sammy.ona.model.Organisation;
import com.sammy.ona.model.OrganisationUser;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Service {

    @GET("orgs")
    Single<List<Organisation>> getOrganisations(@Header("Authorization")String token);

    @GET("user")
    Single<OrganisationUser> getUser(@Header("Authorization") String token);

    @GET("orgs")
    Single<Organisation> createOrganisation(@Header("Authorization")String token,@Body Organisation organisation);

}
