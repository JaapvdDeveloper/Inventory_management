package com.crud.ui.demo;

import com.crud.ui.demo.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails saveUser(UserDetails user) {
        System.out.println("Saving user to database: " + user.getUsername());
        return userRepository.save(user);
    }

    public UserDetails findByUsername(String username) {
        return (UserDetails) userRepository.findByUsername(username);
    }

}
