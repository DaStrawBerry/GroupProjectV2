package com.group.projectv2.repository;

import com.group.projectv2.entity.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResultRepository extends MongoRepository<Result, String> {
    public List<Result> findAllByUser_id(String user_id);
    public List<Result> findAllByTest_id(String Test_id);
}
