package com.group.projectv3.repository;

import com.group.projectv3.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends MongoRepository<Result, Integer> {
    public Optional<Result> findAllById(String username);
    public List<Result> findAllByUsername(String username);
    public List<Result> findAllByTestcode(String testcode);
}
