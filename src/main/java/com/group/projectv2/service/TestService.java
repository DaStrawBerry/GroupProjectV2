package com.group.projectv2.service;

import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TestService {
    public ResponseEntity<?> createTest(Test test);
    public ResponseEntity<?> retrieveAllTest(Test test);
    public ResponseEntity<?> retrieveTestByNameOrType(Test test);
    public ResponseEntity<?> updateTest(Test test);
    public ResponseEntity<?> addQuestion(Test test, Question question);
    public ResponseEntity<?> addQuestion(Test test, List<Question> questions);
    public ResponseEntity<?> retrieveAllQuestion(Test test);
    public ResponseEntity<?> editQuestion(Question question);
    public ResponseEntity<?> deleteQuestion(Question question);
    public ResponseEntity<?> deleteAllQuestion(Test test, Question question);
    public ResponseEntity<?> deleteTest(Test test);
}
