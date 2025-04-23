package com.lattmat.devop.repository;

import com.lattmat.devop.entity.Payments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payments, String> {
}
