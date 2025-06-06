package com.group.projectv2.service;

import com.group.projectv2.dto.TestDTO;
import com.group.projectv2.dto.TestIdDTO;
import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TestService {
    public ResponseEntity<?> createTest(TestDTO testDTO);
    public ResponseEntity<?> retrieveAllTest();
    public ResponseEntity<?> retrieveTestById(String id);
    public ResponseEntity<?> retrieveTestByNameOrType(Test test);
    public ResponseEntity<?> updateTest(Test test);
    public ResponseEntity<?> addQuestion(Test test, Question question);
    public ResponseEntity<?> addQuestions(Test test, List<Question> questions);
    public ResponseEntity<?> retrieveAllQuestion(String id);
    public ResponseEntity<?> editQuestion(Question question);
    public ResponseEntity<?> deleteQuestion(Question question);
    public ResponseEntity<?> deleteAllQuestion(Test test, Question question);
    public ResponseEntity<?> deleteTest(Test test);
}
