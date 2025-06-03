package com.crud.ui.demo;

import java.util.stream.Stream;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class LoginForm extends FormLayout {
    private H3 title;
    private TextField usernameField; 
    private PasswordField password;
    private Button submitButton;
    private Span errorMessageField;
    
    public LoginForm() {
        title = new H3("Login");
        usernameField = new TextField("Username"); 
        password = new PasswordField("Password");
        submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        errorMessageField = new Span();
        
        setRequiredIndicatorVisible(usernameField, password);
        
        add(title, usernameField, password, submitButton, errorMessageField);
        
  
        setMaxWidth("500px");
        setResponsiveSteps(
            new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
            new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
        );
        
        setColspan(usernameField, 2);
        setColspan(password, 2);
        setColspan(submitButton, 2);
        setColspan(errorMessageField, 2); 
    }
    
    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
    
    public TextField getUsernameField() {
        return usernameField;
    }
    
    public PasswordField getPasswordField() {
        return password;
    }
    
    public Span getErrorMessageField() {
        return errorMessageField;
    }
    
    public Button getSubmiButton() { 
        return submitButton;
    }
}