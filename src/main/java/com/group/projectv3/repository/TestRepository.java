package com.group.projectv3.repository;

import com.group.projectv3.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends MongoRepository<Test, Integer> {
    public List<Test> findAllByNameLike(String name);
    public List<Test> findAllByCodeLike(String code);
    public List<Test> findAllByIslimit (Boolean islimit);
    public Optional<Test> findByCode(String code);
}
