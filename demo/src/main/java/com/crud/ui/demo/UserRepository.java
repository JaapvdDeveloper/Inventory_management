package com.crud.ui.demo;

import com.crud.ui.demo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUsername(String username);
}
