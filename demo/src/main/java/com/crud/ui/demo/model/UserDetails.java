package com.crud.ui.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;

@Entity
@Table(name = "registered_users")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @Column(unique = true)
    private String username;


    private String lastName;


    @Email
    @Column(unique = true)
    private String email;

    private boolean allowsMarketing;

    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private String password;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isAllowsMarketing() { return allowsMarketing; }

    public void setAllowsMarketing(boolean allowsMarketing) { this.allowsMarketing = allowsMarketing; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
