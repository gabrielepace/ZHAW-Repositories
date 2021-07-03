package ch.zhaw.ase1.repository;



import org.springframework.data.repository.CrudRepository;

import ch.zhaw.ase1.model.BidEntity;

public interface BidRepository extends CrudRepository<BidEntity, Integer> {
}

