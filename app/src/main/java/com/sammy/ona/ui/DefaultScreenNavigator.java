package com.sammy.ona.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.sammy.ona.details.OrganisationDetailsController;
import com.sammy.ona.model.Organisation;

import javax.inject.Inject;

import com.sammy.ona.di.ActivityScope;

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

    private Router router;

    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void goToOrganisationDetails(Organisation organisation) {
        if (router != null) {
            router.pushController(RouterTransaction.with(OrganisationDetailsController.newInstance(organisation))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void clear() {
        router = null;
    }
}
