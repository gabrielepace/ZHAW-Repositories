package com.example.demo.repository;

import com.example.demo.model.Bid;
import com.example.demo.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Repository class for Category
 */
@RepositoryRestResource(exported = true)
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
