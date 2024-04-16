package com.group.projectv3.repository;

import com.group.projectv3.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, Integer> {
    public List<Question> findAllByTestcode(String testcode);
    public Optional<Question> findByCode(String code);
}
