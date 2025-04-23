package com.lattmat.devop.repository;

import com.lattmat.devop.entity.Inventories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<Inventories, String> {
}