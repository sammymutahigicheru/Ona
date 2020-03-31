package com.sammy.ona.details;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.sammy.ona.model.OrganisationUser;

@AutoValue
abstract class OrganisationUserState {

    abstract boolean loading();

    @Nullable
    abstract OrganisationUser contributors();

    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }

    static Builder builder() {
        return new AutoValue_OrganisationUserState.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder contributors(OrganisationUser contributors);

        abstract Builder errorRes(Integer errorRes);

        abstract OrganisationUserState build();
    }
}
