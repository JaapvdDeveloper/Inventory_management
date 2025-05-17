package com.crud.ui.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.ui.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
    
} 
