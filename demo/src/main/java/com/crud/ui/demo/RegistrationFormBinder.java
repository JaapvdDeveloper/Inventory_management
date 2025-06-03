package com.crud.ui.demo;

import com.crud.ui.demo.model.UserDetails;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

// Data binder for the register form
public class RegistrationFormBinder {
    private RegistrationForm registrationForm;
    private UserService userService;
    private boolean enablePasswordValidation;
    
    /**
     * Constructor with UserService injection
     * 
     * @param registrationForm the registration form to bind
     * @param userService the service to handle user operations
     */
    public RegistrationFormBinder(RegistrationForm registrationForm, UserService userService) {
        this.registrationForm = registrationForm;
        this.userService = userService;
    }
    
    /**
     * Method to add the data binding and validation logics
     * to the registration form
     */
    public void addBindingAndValidation() {
        BeanValidationBinder<UserDetails> binder = new BeanValidationBinder<>(UserDetails.class);
        
      
        // Bind individual fields explicitly to avoid issues with bindInstanceFields
        binder.forField(registrationForm.getUsernameField())
            .withValidator((username, context) -> {
                if (username == null || username.trim().isEmpty()) {
                    return ValidationResult.error("Username cannot be empty");
                }
                if (username.length() < 3) {
                    return ValidationResult.error("Username must be at least 3 characters long");
                }
                // Check if username already exists
                if (userService.findByUsername(username) != null) {
                    return ValidationResult.error("Username already exists");
                }
                return ValidationResult.ok();
            })
            .bind("username");
        
        // Bind email field
        binder.forField(registrationForm.getEmailField())
            .withValidator((email, context) -> {
                if (email == null || email.trim().isEmpty()) {
                    return ValidationResult.error("Email cannot be empty");
                }
                return ValidationResult.ok();
            })
            .bind("email");
        
        // Bind first name
        binder.forField(registrationForm.getFirstNameField())
            .withValidator((firstName, context) -> {
                if (firstName == null || firstName.trim().isEmpty()) {
                    return ValidationResult.error("First name cannot be empty");
                }
                return ValidationResult.ok();
            })
            .bind("firstName");
        
        // Bind last name
        binder.forField(registrationForm.getLastNameField())
            .withValidator((lastName, context) -> {
                if (lastName == null || lastName.trim().isEmpty()) {
                    return ValidationResult.error("Last name cannot be empty");
                }
                return ValidationResult.ok();
            })
            .bind("lastName");
        
        // A custom validator for password field
        binder.forField(registrationForm.getPasswordField())
            .withValidator(this::passwordValidator)
            .bind("password");
        
        // The second password field is not connected to the binder, but we
        // want the binder to re-check the password validator when the field value
        // changes. The easiest way is to do that manually
        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
            // The user has modified the second field, now we can validate and show errors.
            // See passwordValidator() for how this flag is used
            enablePasswordValidation = true;
            binder.validate();
        });
        
        binder.setStatusLabel(registrationForm.getErrorMessageField());
        
        registrationForm.getSubmitButton().addClickListener(event -> {
            try {
                // Create empty bean to store the details into
                UserDetails userBean = new UserDetails();
                // Run validators and write the values to the bean
                binder.writeBean(userBean);
                
                // Save the user to the database/service
                UserDetails savedUser = saveUser(userBean);
                
                if (savedUser != null) {
                   
                    showSuccess(savedUser);
                } else {
                    showError("Failed to register user. Please try again.");
                }
            } catch (ValidationException exception) {
               
                registrationForm.getErrorMessageField().setText("Please correct the errors and try again.");
            }
        });
    }
    
    /**
     * Method to validate that:
     * <p>
     * 1) Password is at least 8 characters long
     * <p>
     * 2) Values in both fields match each other
     */
    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
     
        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should at least be 8 characters long");
        }
        
        if (!enablePasswordValidation) {
            // User hasn't visited the field yet, so don't validate just yet, but next time.
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }
        
        String pass2 = registrationForm.getPasswordConfirmField().getValue();
        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }
        
        return ValidationResult.error("Passwords do not match");
    }
    
    /**
     * Saves the user using the UserService
     * 
     * @param userBean the user details to save
     * @return saved UserDetails or null if failed
     */
    private UserDetails saveUser(UserDetails userBean) {
        try {

            return userService.saveUser(userBean);
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
            return null;
        }
    }
    
    private void showSuccess(UserDetails userBean) {
        Notification notification = Notification.show("Registration successful! Welcome " + userBean.getUsername() + "!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        registrationForm.getUI().ifPresent(ui -> ui.navigate("LoginView"));
    }
    
    private void showError(String message) {
        Notification notification = Notification.show(message);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}