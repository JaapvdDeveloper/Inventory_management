package com.crud.ui.demo;

import com.crud.ui.demo.model.UserDetails;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

// Data binder for the login form
public class LoginBinder {
    private LoginForm loginForm;
    private UserService userService;
    
    /**
     * Constructs a LoginBinder with just the login form
     * 
     * @param loginForm the login form to bind
     */
    public LoginBinder(LoginForm loginForm) {
        this.loginForm = loginForm;
        throw new IllegalArgumentException("UserService implementation must be provided.");
    }
    
    /**
     * Constructs a LoginBinder with login form and user service
     * 
     * @param loginForm the login form to bind
     * @param userService the service to handle user operations
     */
    public LoginBinder(LoginForm loginForm, UserService userService) {
        this.loginForm = loginForm;
        this.userService = userService;
    }
    
    /**
     * Method to add the data binding and validation logics
     * to the login form
     */
    public void addBindingAndValidation() {
        BeanValidationBinder<UserDetails> binder = new BeanValidationBinder<>(UserDetails.class);
        
        // bind username field
        binder.forField(loginForm.getUsernameField())
            .withValidator((username, context) -> {
                if (username == null || username.trim().isEmpty()) {
                    return ValidationResult.error("Username cannot be empty");
                }
                return ValidationResult.ok();
            })
            .bind("username");
        
        // Bind password field with validation
        binder.forField(loginForm.getPasswordField())
            .withValidator(this::passwordValidator)
            .bind("password");
        
        // Add value change listener to validate on the fly
        loginForm.getPasswordField().addValueChangeListener(e -> {
            binder.validate();
        });
        
        // Set the error message field for the binder
        binder.setStatusLabel(loginForm.getErrorMessageField());
        
        // Add click listener to the submit button
        loginForm.getSubmiButton().addClickListener(event -> {
            try {
                // Create empty bean to store the details into
                UserDetails userBean = new UserDetails();
                binder.writeBean(userBean);
                
                boolean authenticated = authenticateUser(userBean);
                if (authenticated) {
                    showLoginSuccess(userBean);
                } else {
                    showLoginFailure();
                }
            } catch (ValidationException exception) {
                // Validation errors are already displayed by the binder
                loginForm.getErrorMessageField().setText("Please correct the errors and try again.");
            }
        });
    }
    
    /**
     * Validates the password field
     * 
     * @param password the password to validate
     * @param ctx the value context
     * @return ValidationResult indicating if validation passed
     */
    private ValidationResult passwordValidator(String password, ValueContext ctx) {
        if (password == null || password.trim().isEmpty()) {
            return ValidationResult.error("Password cannot be empty");
        }
        
        if (password.length() < 6) {
            return ValidationResult.error("Password must be at least 6 characters long");
        }
        
        return ValidationResult.ok();
    }
    
    /**
     * Authenticates a user against the registered users in the database
     * 
     * @param userBean the user details to authenticate
     * @return true if authentication succeeds, false otherwise
     */
    private boolean authenticateUser(UserDetails userBean) {
        try {
            // Find the user in the database by username
            UserDetails registeredUser = userService.findByUsername(userBean.getUsername());
            
            // Check if user exists and password matches
            if (registeredUser != null) {
                // In a production system, you would use password hashing 
                // (e.g., BCrypt) here instead of plain text comparison
                return registeredUser.getPassword().equals(userBean.getPassword());
            }
            return false;
        } catch (Exception e) {
            // Log the error
            System.err.println("Authentication error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Shows a success notification and navigates to the main view
     * 
     * @param userBean the authenticated user details
     */
    private void showLoginSuccess(UserDetails userBean) {
        Notification notification = Notification.show("Login successful! Welcome " + userBean.getUsername());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        
        // Navigate to main view
        
      loginForm.getUI().ifPresent(ui -> ui.navigate("home"));
    }
    
    /**
     * Shows a failure notification
     */
    private void showLoginFailure() {
        Notification notification = Notification.show("Invalid username or password.");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}