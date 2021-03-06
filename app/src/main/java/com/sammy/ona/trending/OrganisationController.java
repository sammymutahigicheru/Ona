package com.sammy.ona.trending;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import io.neverstoplearning.advancedandroid.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sammy.ona.base.BaseController;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class OrganisationController extends BaseController {

    @Inject
    OrganisationPresenter presenter;
    @Inject
    OrganisationViewModel viewModel;

    @BindView(R.id.repo_list) RecyclerView organisationList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onViewBound(View view) {
        toolbar.setTitle("Organisations");
        organisationList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        organisationList.setAdapter(new OrganisationAdapter(presenter));
        fab.setOnClickListener(v ->{
            presenter.goToCreateOrganisation();
        });
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    organisationList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.organisations()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(((OrganisationAdapter) organisationList.getAdapter())::setData),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        organisationList.setVisibility(View.GONE);
                        errorText.setText(errorRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_organisation;
    }
}
