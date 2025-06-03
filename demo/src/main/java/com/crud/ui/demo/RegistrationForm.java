package com.crud.ui.demo;

import java.util.stream.Stream;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

// Register form extends the formlayout component
// This is a layout that arranges its children in a form
// It is a good choice for creating forms with labels and fields
public class RegistrationForm extends FormLayout {
    private H3 title;
    private TextField firstName;
    private TextField lastName;
    private TextField usernameField; // Added username field
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private Checkbox allowMarketing;
    private Span errorMessageField;
    private Button submitButton;
    
    public RegistrationForm() {
        title = new H3("Signup form");
        firstName = new TextField("First name");
        lastName = new TextField("Last name");
        usernameField = new TextField("Username");
        email = new EmailField("Email");
        allowMarketing = new Checkbox("I want to receive marketing emails");
        allowMarketing.getStyle().set("margin-top", "10px");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");
        
        setRequiredIndicatorVisible(firstName, lastName, usernameField, email, password, passwordConfirm);
        
        errorMessageField = new Span();
        submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        // Add username field to the form layout
        add(title, firstName, lastName, usernameField, email, password, passwordConfirm,
            allowMarketing, errorMessageField, submitButton);
        
        // Max width of the Form
        setMaxWidth("500px");
        
        // Allow the form layout to be responsive
        // On devices widths 0-490px we have one column
        // Otherwise, we have two columns
        setResponsiveSteps(
            new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
            new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
        );
        
        // These components always take full width
        setColspan(title, 2);
        setColspan(firstName, 2);
        setColspan(usernameField, 2); // Username takes full width
        setColspan(email, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);
    }
    
    // Existing getter methods
    public TextField getFirstNameField() {
        return firstName;
    }
    
    public TextField getLastNameField() {
        return lastName;
    }
    
    // New getter for username field
    public TextField getUsernameField() {
        return usernameField;
    }
    
    public EmailField getEmailField() {
        return email;
    }
    
    public PasswordField getPasswordField() {
        return password;
    }
    
    public PasswordField getPasswordConfirmField() {
        return passwordConfirm;
    }
    
    public Checkbox getAllowMarketingField() {
        return allowMarketing;
    }
    
    public Span getErrorMessageField() {
        return errorMessageField;
    }
    
    public Button getSubmitButton() {
        return submitButton;
    }
    
    // Add this method to match LoginForm naming convention
    public Button getSubmiButton() {
        return submitButton;
    }
    
    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}