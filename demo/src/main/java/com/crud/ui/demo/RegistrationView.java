package com.crud.ui.demo;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("RegistrationView")
public class RegistrationView extends VerticalLayout {


    public RegistrationView(UserService userService) {
    RegistrationForm registrationForm = new RegistrationForm();
    add(registrationForm);
    
    RegistrationFormBinder binder = new RegistrationFormBinder(registrationForm, userService);
    binder.addBindingAndValidation();
}

}
