package com.example.demo.repository;

import com.example.demo.model.Auction;
import com.example.demo.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Repository class for Auction
 */
@RepositoryRestResource(exported = true)
public interface AuctionRepository extends CrudRepository<Auction, Long> {
}
