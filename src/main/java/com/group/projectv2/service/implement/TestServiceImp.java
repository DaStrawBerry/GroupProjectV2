package com.group.projectv2.service.implement;

import com.group.projectv2.dto.TestDTO;
import com.group.projectv2.dto.TestIdDTO;
import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.ResponseObject;
import com.group.projectv2.entity.Test;
import com.group.projectv2.map.TestMap;
import com.group.projectv2.repository.TestRepository;
import com.group.projectv2.service.QuestionService;
import com.group.projectv2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImp implements TestService {
    @Autowired
    TestRepository repository;
    @Autowired
    QuestionService qService;
    @Autowired
    TestMap testMap;
    @Override
    public ResponseEntity<?> createTest(TestDTO testDTO) {
        Test test = testMap.dtoToEntity(testDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        "SUCCESS",
                        "Test created",
                        repository.save(test)
                ));
    }

    @Override
    public ResponseEntity<?> retrieveAllTest() {
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
    public ResponseEntity<?> retrieveTestById(String id) {
        Optional<Test> test = repository.findById(id);
        if(test.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Found test: " + test.get().getName(),
                            test
                    ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(
                        "NOT_FOUND",
                        "Not found test: " + id,
                        ""
                ));
    }

    @Override
    public ResponseEntity<?> retrieveTestByNameOrType(Test test) {
        List<Test> testss = repository.findAllByNameLike(test.getName());
        List<Test> tests = new ArrayList<>();
        if(!testss.isEmpty()){
            for(Test t : testss){
                if(t.getIslimit() && test.getIslimit()){
                    tests.add(t);
                }
            }
        }else{
            tests = repository.findAllByIslimit(test.getIslimit());
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
    public ResponseEntity<?> addQuestions(Test test, List<Question> questions) {
        return qService.createQuestion(test, questions);
    }

    @Override
    public ResponseEntity<?> retrieveAllQuestion(String id) {
        return qService.retrieveQuestionByTestId(id);
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
