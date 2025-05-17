package com.crud.ui.demo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.ui.demo.model.Customer;
import com.crud.ui.demo.repository.CustomerRepository;

@SpringBootApplication
public class InventoryCrudApplication {

	private static final Logger log = LoggerFactory.getLogger(InventoryCrudApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryCrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			//Save a few customers 
			repository.save(new Customer("John", "Doe"));
			repository.save(new Customer("Jane", "Doe"));
			repository.save(new Customer("Jack", "Smith"));
			repository.save(new Customer("Jill", "Johnson"));
			repository.save(new Customer("Jim", "Brown"));
			repository.save(new Customer("Jake", "White"));
			repository.save(new Customer("Judy", "Green"));
			repository.save(new Customer("Jerry", "Black"));
			repository.save(new Customer("Jessica", "Blue"));
			repository.save(new Customer("Jordan", "Red"));


			// fetch all customers 
			log.info("Customers found with findAll();");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");


			// Fetch an individual customer by ID
			Customer customer = repository.findById(1l).get();
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Doe'):");
			log.info("--------------------------------------------");
			for (Customer doe : repository. findByLastNameStartsWithIgnoreCase("Doe")) {
				log.info(doe.toString());
			}
			log.info("");
		};
	}

}
