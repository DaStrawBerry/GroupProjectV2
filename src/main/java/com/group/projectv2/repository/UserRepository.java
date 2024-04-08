package com.group.projectv2.repository;

import com.group.projectv2.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findAllById(String id);
    public List<User> findByUsername(String username);
    public List<User> findAllByFullnameLike(String fullname);
    public List<User> findAllByCodeLike(String code);
    public List<User> findByEmail(String email);
}
