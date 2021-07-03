package com.example.demo.repository;

import com.example.demo.model.Checkout;
import com.example.demo.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Repository class for Checkout
 */
@RepositoryRestResource(exported = true)
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
}
