package com.sammy.ona.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class OrganisationUser {
    public abstract String username();
    public abstract String gravatar();
    public static JsonAdapter<OrganisationUser> jsonAdapter(Moshi moshi) {
        return new AutoValue_OrganisationUser.MoshiJsonAdapter(moshi);
    }
}
