package com.sammy.ona.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.sammy.ona.model.Organisation;

public interface ScreenNavigator {

    void initWithRouter(Router router, Controller rootScreen);

    boolean pop();

    void goToOrganisationDetails(Organisation organisation);

    void goToCreateOrganisation();

    void clear();
}
