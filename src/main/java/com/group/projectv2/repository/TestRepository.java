package com.group.projectv2.repository;

import com.group.projectv2.entity.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<Test, String> {
    public List<Test> findAllById(String id);
    public List<Test> findAllByNameLike(String name);
    public List<Test> findAllByIs_limit(Boolean is_limit);
}
