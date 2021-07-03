package ch.zhaw.ase1.repository;



import org.springframework.data.repository.CrudRepository;

import ch.zhaw.ase1.model.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
