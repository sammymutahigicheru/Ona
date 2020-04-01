package com.sammy.ona.create;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.bluelinelabs.conductor.Controller;
import com.sammy.ona.base.BaseController;
import com.sammy.ona.details.OrganisationDetailsController;
import com.sammy.ona.model.Organisation;

import javax.inject.Inject;

import butterknife.BindView;
import io.neverstoplearning.advancedandroid.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static android.text.TextUtils.isEmpty;

public class CreateOrganisationController extends BaseController {
    @Inject
    CreateOrganisationViewModel viewModel;
    @Inject
    CreateOrganisationPresenter presenter;

    @BindView(R.id.et_org_name)
    EditText orgName;
    @BindView(R.id.et_country)
    EditText country;
    @BindView(R.id.et_city)
    EditText city;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.et_name)
    EditText name;

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
        toolbar.setTitle("Create Organisation");
        save.setOnClickListener(v ->{
            if (!isEmpty(orgName.getText().toString()) || !isEmpty(city.getText().toString()) || !isEmpty(name.getText().toString())){
                /*NB country name must be us*/
                Organisation organisation = new Organisation() {
                    @Override
                    public String organisation() {
                        return orgName.getText().toString();
                    }

                    @Override
                    public String name() {
                        return name.getText().toString();
                    }

                    @Override
                    public String city() {
                        return city.getText().toString();
                    }

                    @Override
                    public String country() {
                        return country.getText().toString();
                    }

                    @Override
                    public String dateModified() {
                        return null;
                    }
                };
                presenter.createOrganisation("d2c01abda5138831cc8f63b1e94ee053f204d0ad",organisation);
            }else{
                Toast.makeText(getActivity(), "All Fields Are Required !!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.createOrganisation()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(t ->{
                            orgName.getText().clear();
                            country.getText().clear();
                            city.getText().clear();
                    Toast.makeText(getApplicationContext(), "Successfully Created An Organisation", Toast.LENGTH_LONG).show();
                }),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        errorText.setText(errorRes);
                    }
                })
        };


    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_create_organisation;
    }
}
