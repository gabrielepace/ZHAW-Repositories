package com.example.demo.repository;

import com.example.demo.model.Auction;
import com.example.demo.model.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Repository class for Bid
 */
@RepositoryRestResource(exported = true)
public interface BidRepository extends CrudRepository<Bid, Long> {
}
