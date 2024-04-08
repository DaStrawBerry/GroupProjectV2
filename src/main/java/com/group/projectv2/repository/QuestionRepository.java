package com.group.projectv2.repository;

import com.group.projectv2.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    public List<Question> findAllByTest_id(String test_id);
    public void deleteAllByTest_id(String test_id);
}
