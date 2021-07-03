package ch.zhaw.ase1.repository;



import org.springframework.data.repository.CrudRepository;

import ch.zhaw.ase1.model.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
}
