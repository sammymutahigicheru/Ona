package com.sammy.ona.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Organisation {
    @Json(name="org")
    public abstract String organisation();
    public abstract String city();
    public abstract String country();
    @Json(name = "date_modified")
    public abstract String dateModified();

    public static JsonAdapter<Organisation> jsonAdapter(Moshi moshi) {
        return new AutoValue_Organisation.MoshiJsonAdapter(moshi);
    }
}
