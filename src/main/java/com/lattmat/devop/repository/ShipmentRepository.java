package com.lattmat.devop.repository;

import com.lattmat.devop.entity.Shipments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends MongoRepository<Shipments, String> {
}
