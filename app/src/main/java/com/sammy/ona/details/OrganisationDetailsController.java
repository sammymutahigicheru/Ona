package com.sammy.ona.details;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.sammy.ona.model.Organisation;
import com.sammy.ona.model.OrganisationUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.neverstoplearning.advancedandroid.R;
import com.sammy.ona.base.BaseController;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class OrganisationDetailsController extends BaseController {



    private static Organisation organisation;

    static final String ORGANISATION_NAME_KEY = "repo_name";
    static final String ORGANISATION_OWNER_KEY = "repo_owner";

    public static Controller newInstance(Organisation organisation) {
        OrganisationDetailsController.organisation = organisation;
        Bundle bundle = new Bundle();
        bundle.putString(ORGANISATION_NAME_KEY, organisation.organisation());
        bundle.putString(ORGANISATION_OWNER_KEY, organisation.city());
        return new OrganisationDetailsController(bundle);
    }

    @Inject
    OrganisationDetailsViewModel viewModel;
    @Inject
    OrganisationDetailsPresenter presenter;
    @BindView(R.id.tv_organisation)
    TextView orgNameText;
    @BindView(R.id.tv_country) TextView countryText;
    @BindView(R.id.tv_creation_date) TextView createdDateText;
    @BindView(R.id.contributor_list) RecyclerView userList;
    @BindView(R.id.loading_indicator) View detailsLoadingView;
    @BindView(R.id.user_loading_indicator) View userLoadingView;
    @BindView(R.id.content) View contentContainer;
    @BindView(R.id.tv_error) TextView errorText;
    @BindView(R.id.tv_user_error) TextView userErrorText;

    public OrganisationDetailsController(Bundle bundle) {
        super(bundle);
    }

    @Override
    protected void onViewBound(View view) {
        userList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        userList.setAdapter(new OrganisationDetailsAdapter());
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.details()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(details -> {
                    if (details.loading()) {
                        detailsLoadingView.setVisibility(View.VISIBLE);
                        contentContainer.setVisibility(View.GONE);
                        errorText.setVisibility(View.GONE);
                        errorText.setText(null);
                    } else {
                        if (details.isSuccess()) {
                            errorText.setText(null);
                        } else {
                            //noinspection ConstantConditions
                            errorText.setText(details.errorRes());
                        }
                        detailsLoadingView.setVisibility(View.GONE);
                        contentContainer.setVisibility(details.isSuccess() ? View.VISIBLE : View.GONE);
                        errorText.setVisibility(details.isSuccess() ? View.GONE : View.VISIBLE);
                        orgNameText.setText(details.name());
                        countryText.setText(details.description());
                        createdDateText.setText(details.createdDate());
                    }
                }),
                viewModel.contributors()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(contributorsDetails -> {
                    if (contributorsDetails.loading()) {
                        userLoadingView.setVisibility(View.VISIBLE);
                        userList.setVisibility(View.GONE);
                        userErrorText.setVisibility(View.GONE);
                        userErrorText.setText(null);
                    } else {
                        userLoadingView.setVisibility(View.GONE);
                        userList.setVisibility(contributorsDetails.isSuccess() ? View.VISIBLE : View.GONE);
                        userErrorText.setVisibility(contributorsDetails.isSuccess() ? View.GONE : View.VISIBLE);
                        if (contributorsDetails.isSuccess()) {
                            userErrorText.setText(null);
                            List<OrganisationUser> users = new ArrayList<>();
                            users.add(contributorsDetails.contributors());
                            ((OrganisationDetailsAdapter) userList.getAdapter()).setData(users);
                        } else {
                            //noinspection ConstantConditions
                            userErrorText.setText(contributorsDetails.errorRes());
                        }
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_organisation_details;
    }
}
