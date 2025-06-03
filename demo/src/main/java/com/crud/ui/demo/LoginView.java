package com.crud.ui.demo;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class LoginView extends VerticalLayout {
    
    // Constructor with UserService injection
    public LoginView(UserService userService) {
        // Center the login form
        LoginForm loginForm = new LoginForm();
        setHorizontalComponentAlignment(Alignment.CENTER, loginForm);
        add(loginForm);
        
        // Pass both loginForm and userService to LoginBinder
        LoginBinder loginBinder = new LoginBinder(loginForm, userService);
        loginBinder.addBindingAndValidation();
    }
}