package com.group.projectv2.repository;

import com.group.projectv2.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    public List<Question> findAllByTestid(String testid);
    public void deleteAllByTestid(String testid);
}
