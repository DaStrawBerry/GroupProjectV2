package com.group.projectv2.repository;

import com.group.projectv2.entity.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResultRepository extends MongoRepository<Result, String> {
    public List<Result> findAllByUserid(String userid);
    public List<Result> findAllByTestid(String Testid);
}
