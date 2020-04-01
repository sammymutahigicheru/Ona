package com.sammy.ona.home;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.sammy.ona.create.CreateOrganisationController;
import com.sammy.ona.trending.OrganisationController;

import io.neverstoplearning.advancedandroid.R;
import com.sammy.ona.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new OrganisationController();
    }

}
