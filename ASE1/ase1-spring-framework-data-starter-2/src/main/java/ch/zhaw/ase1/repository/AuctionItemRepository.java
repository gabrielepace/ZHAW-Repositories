package ch.zhaw.ase1.repository;



import org.springframework.data.repository.CrudRepository;

import ch.zhaw.ase1.model.AuctionItemEntity;

public interface AuctionItemRepository extends CrudRepository<AuctionItemEntity, Integer> {
}

