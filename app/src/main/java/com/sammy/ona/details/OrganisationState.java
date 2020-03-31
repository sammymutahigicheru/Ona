package com.sammy.ona.details;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class OrganisationState {

    abstract boolean loading();

    @Nullable
    abstract String name();

    @Nullable
    abstract String description();

    @Nullable
    abstract String createdDate();


    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }

    static Builder builder() {
        return new AutoValue_OrganisationState.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder name(String name);

        abstract Builder description(String description);

        abstract Builder createdDate(String createdDate);

        abstract Builder errorRes(Integer errorRes);

        abstract OrganisationState build();
    }
}
