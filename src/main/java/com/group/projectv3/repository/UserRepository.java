package com.group.projectv3.repository;

import com.group.projectv3.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User> findAllByNameLike(String name);
    List<User> findAllByCodeLike(String code);
    Optional<User> findByEmail(String email);
}
