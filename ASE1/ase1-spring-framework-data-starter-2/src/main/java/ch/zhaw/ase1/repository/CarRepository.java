package ch.zhaw.ase1.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.zhaw.ase1.model.CarEntity;

import java.util.List;

public interface CarRepository extends CrudRepository<CarEntity, Integer> {

    public List<CarEntity> findByModel(String model);

    @Query("select c from CarEntity c join c.owners p where (p.name = :name)")
    public List<CarEntity> findByOwnerName(@Param("name")String name);

}
