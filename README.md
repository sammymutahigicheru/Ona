<p align="center"><img src="images/logo1.jpeg" alt="Ona Organisations App" height="200px"></p>

# Ona Organisations App  [![CircleCI](https://circleci.com/gh/jumaallan/AndelaMedManager/tree/master.svg?style=shield)](https://circleci.com/gh/jumaallan/Andela-Med-Manager/tree/master)   [![Maintainability](https://api.codeclimate.com/v1/badges/029ded8e7747a58f1095/maintainability)](https://codeclimate.com/github/jumaallan/Andela-Med-Manager/maintainability)  [![Test Coverage](https://api.codeclimate.com/v1/badges/029ded8e7747a58f1095/test_coverage)](https://codeclimate.com/github/jumaallan/Andela-Med-Manager/test_coverage)

Ona organisations app is a simple app that provides ease of accessability and usage of ona restful services.

The app uses the **MVP Architecture**. I have used the following components for development:

* Dependency Injection - Dagger2
* Presenter - Handles the views
* ViewModel - Manage your UI's data in a lifecycle-aware fashion
* DataBinding -  minimize the glue code necessary to bind your application logic and layouts.


## Prerequisites
You will need the following to run this project:
1. A laptop or desktop machine with internet access
2. Android Studio 3.1 Stable Channel (Latest Stable Release)

## Setting Up
* Clone the Repository from Github
* Open the project folder using Android Studio IDE

### Welcome Screen
The User will the be presented with the welcome page, where they will be presented with info about ona,ona goals and what the 
app does.
<img src="images/welcome.png" width="280"/>   <img src="images/goal.png" width="280"/>       <img src="images/about.png" width="280"/>

### Main Dashboard 
After welcome page, the use is redirected to the Main Dashboard Page, which is the Main Activity in our application. The Dashboard shows organisations, and gives the User the options to:

* Add New Organisation via + button

This is how the Dashboard looks like:

<img src="images/organisations.png" width="280"/>

### Organisations [Add -> create] 
The User is required to add organisation, using the Add button option on the Main Dashboard. This is how the process looks like :

<img src="images/organisations.png" width="280"/> <img src="images/create.png" width="280"/>  

### Organisation's Details Page
This page shows the organisation's Details in an easy to understand way! Just click on the organisation's card 

<img src="images/organisations.png" width="280"/>   <img src="images/details.png"/> 
 

### Undone Parts
* Not done all Unit Tests
* No Log in page(the app currently use my api_token key)
* No charts implented

