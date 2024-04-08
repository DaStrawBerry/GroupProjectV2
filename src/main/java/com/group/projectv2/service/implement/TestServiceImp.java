package com.group.projectv2.service.implement;

import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.ResponseObject;
import com.group.projectv2.entity.Test;
import com.group.projectv2.repository.TestRepository;
import com.group.projectv2.service.QuestionService;
import com.group.projectv2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImp implements TestService {
    @Autowired
    TestRepository repository;
    @Autowired
    QuestionService qService;
    @Override
    public ResponseEntity<?> createTest(Test test) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        "SUCCESS",
                        "Test created",
                        repository.save(test)
                ));
    }

    @Override
    public ResponseEntity<?> retrieveAllTest(Test test) {
        List<Test> tests = repository.findAll();
        if(!tests.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Found " + tests.size() + " tests",
                            tests
                    ));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseObject(
                        "EMPTY",
                        "No tests found",
                        tests
                ));
    }

    @Override
    public ResponseEntity<?> retrieveTestByNameOrType(Test test) {
        List<Test> tests = repository.findAllByNameLike(test.getName());
        if(!tests.isEmpty()){
            tests = repository.findAllByIs_limit(test.getIs_limit());
        }
        if(!tests.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Found " + tests.size() + " tests",
                            tests
                    ));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseObject(
                        "EMPTY",
                        "No tests found",
                        tests
                ));
    }

    @Override
    public ResponseEntity<?> updateTest(Test test) {
        List<Test> tests = repository.findAllById(test.getId());
        if (!tests.isEmpty()) {
            try {
                repository.save(test);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(
                                "OK",
                                "Test " + test.getName() + " updated",
                                test));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                        .body(new ResponseObject(
                                "FAILED",
                                "Unable to update test: " + test.getName(),
                                test));
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseObject(
                        "EMPTY",
                        "No test found",
                        test));
    }

    @Override
    public ResponseEntity<?> addQuestion(Test test, Question question) {
        return qService.createQuestion(test, question);
    }

    @Override
    public ResponseEntity<?> addQuestion(Test test, List<Question> questions) {
        return qService.createQuestion(test, questions);
    }

    @Override
    public ResponseEntity<?> retrieveAllQuestion(Test test) {
        return qService.retrieveQuestionByTestId(test);
    }

    @Override
    public ResponseEntity<?> editQuestion(Question question) {
        return qService.updateQuestion(question);
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Question question) {
        return qService.deleteQuestion(question);
    }

    @Override
    public ResponseEntity<?> deleteAllQuestion(Test test, Question question) {
        return qService.deleteQuestionByTestId(test);
    }

    @Override
    public ResponseEntity<?> deleteTest(Test test) {
        try {
            qService.deleteQuestionByTestId(test);
            repository.deleteById(test.getId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "OK",
                            "Test " + test.getName() + " deleted",
                            ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No test found",
                            test));
        }
    }
}
