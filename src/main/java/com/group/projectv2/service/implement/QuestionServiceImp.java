package com.group.projectv2.service.implement;

import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.ResponseObject;
import com.group.projectv2.entity.Test;
import com.group.projectv2.repository.QuestionRepository;
import com.group.projectv2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    QuestionRepository repository;
    @Override
    public ResponseEntity<?> createQuestion(Test test, Question question) {
        question.setTest_id(test.getId());
        question.setN_th(repository.findAllByTest_id(test.getId()).size() + 1);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        "SUCCESS",
                        "Question created",
                        repository.save(question)
                ));
    }

    @Override
    public ResponseEntity<?> createQuestion(Test test, List<Question> questions) {
        int n = repository.findAllByTest_id(test.getId()).size();
        for(Question q : questions){
            n++;
            q.setN_th(n);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        "SUCCESS",
                        questions.size() + " question created",
                        repository.saveAll(questions)
                ));
    }

    @Override
    public ResponseEntity<?> retrieveQuestionById(Question question) {
        Optional<Question> q = repository.findById(question.getId());
        if(q.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Question found",
                            q
                            ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(
                        "EMPTY",
                        "Question " + question.getId() + " not found",
                        ""
                ));
    }

    @Override
    public ResponseEntity<?> retrieveQuestionByTestId(Test test) {
        List<Question> questions = repository.findAllByTest_id(test.getId());
        if(questions.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Test question list found",
                            questions
                            ));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseObject(
                        "EMPTY",
                        "No question in this test",
                        questions
                        ));
    }

    @Override
    public ResponseEntity<?> updateQuestion(Question question) {
        Optional<Question> q = repository.findById(question.getId());
        if (q.isEmpty()) {
            try {
                repository.save(question);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(
                                "OK",
                                "Question " + question.getId() + " updated",
                                question));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                        .body(new ResponseObject(
                                "FAILED",
                                "Unable to update question: " + question.getId(),
                                question));
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseObject(
                        "EMPTY",
                        "No question found",
                        question));
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Question question) {
        try {
            List<Question> questions = repository.findAllByTest_id(question.getTest_id());
            for(int i = question.getN_th(); i < questions.size(); i++){
                questions.get(i).setN_th(questions.get(i).getN_th() - 1);
            }
            repository.deleteById(question.getId());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "OK",
                            "Question " + question.getId() + " deleted",
                            ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No question found",
                            question));
        }
    }

    @Override
    public ResponseEntity<?> deleteQuestionByTestId(Test test) {
        try {
            repository.deleteAllByTest_id(test.getId());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "OK",
                            "All test: " + test.getName() + "'s questions deleted",
                            ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No question found for test: " + test.getName(),
                            test));
        }
    }
}
