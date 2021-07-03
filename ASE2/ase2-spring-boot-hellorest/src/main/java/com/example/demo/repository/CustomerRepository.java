package com.example.demo.repository;


import com.example.demo.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository class for Customer
 */
@RepositoryRestResource(exported = true)
// @RepositoryRestResource(path = "customers")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findByFirstnameAndLastname(String firstname, String lastname);
}
