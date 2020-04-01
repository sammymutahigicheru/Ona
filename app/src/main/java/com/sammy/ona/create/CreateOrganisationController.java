package com.sammy.ona.create;

import android.os.Bundle;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.sammy.ona.base.BaseController;
import com.sammy.ona.details.OrganisationDetailsController;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.R;
import io.reactivex.disposables.Disposable;

public class CreateOrganisationController extends BaseController {
    @Inject
    CreateOrganisationViewModel viewModel;
    @Inject
    CreateOrganisationPresenter presenter;

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
    }

    @Override
    protected Disposable[] subscriptions() {
        return super.subscriptions();
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_create_organisation;
    }
}
