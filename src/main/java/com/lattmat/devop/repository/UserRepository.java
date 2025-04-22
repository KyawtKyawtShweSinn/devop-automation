package com.lattmat.devop.repository;
import com.lattmat.devop.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, Object> {
    Optional<Users> findByNameIgnoreCase(String name);
}