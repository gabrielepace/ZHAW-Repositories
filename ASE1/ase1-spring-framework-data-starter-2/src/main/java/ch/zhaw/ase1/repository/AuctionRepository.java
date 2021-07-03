package ch.zhaw.ase1.repository;



import org.springframework.data.repository.CrudRepository;

import ch.zhaw.ase1.model.AuctionEntity;

public interface AuctionRepository extends CrudRepository<AuctionEntity, Integer> {
}
