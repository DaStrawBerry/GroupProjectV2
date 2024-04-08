package com.group.projectv2.service;

import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    public ResponseEntity<?> createQuestion(Test test, Question question);
    public ResponseEntity<?> createQuestion(Test test, List<Question> questions);
    public ResponseEntity<?> retrieveQuestionById(Question question);
    public ResponseEntity<?> retrieveQuestionByTestId(Test test);
    public ResponseEntity<?> updateQuestion(Question question);
    public ResponseEntity<?> deleteQuestion(Question question);
    public ResponseEntity<?> deleteQuestionByTestId(Test test);
}
