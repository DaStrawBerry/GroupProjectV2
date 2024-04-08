package com.group.projectv2.service.implement;

import com.group.projectv2.dto.ResultDTO;
import com.group.projectv2.dto.ResultInDTO;
import com.group.projectv2.entity.*;
import com.group.projectv2.map.ResultMap;
import com.group.projectv2.repository.QuestionRepository;
import com.group.projectv2.repository.ResultRepository;
import com.group.projectv2.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImp implements ResultService {
    @Autowired
    ResultRepository repository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ResultMap rmap;
    @Autowired
    TestServiceImp testService;

    @Override
    public ResponseEntity<?> getAllResult() {
        List<Result> results = repository.findAll();
        if(results.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No result found",
                            results
                    ));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(
                        "OK",
                        "Found " + results.size() + " results",
                        results
                ));
    }

    @Override
    public ResponseEntity<?> doTest(ResultDTO resultDTO) {
        Result result = new Result();
        result.setUser_id(resultDTO.getUser_id());
        result.setTest_id(resultDTO.getTest_id());
        result.setMark(0.0);
        result.setIs_completed(false);

        List<Object> data = new ArrayList<>();
        Test test = new Test();
        test.setId(resultDTO.getTest_id());
        data.add(
                ((ResponseObject)
                        testService.retrieveTestById(resultDTO.getTest_id())
                                .getBody())
                        .getData()
        );
        data.add(
                ((ResponseObject)
                        testService.retrieveAllQuestion(test)
                                .getBody())
                        .getData()
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseObject(
                        "START",
                        "Do the test now",
                        data
                ));
    }

    @Override
    public ResponseEntity<?> finish(ResultInDTO resultInDTO) {
        Result result = rmap.dtoToEntity(resultInDTO);
        List<Result> results = repository.findAllByUser_id(result.getUser_id());
        for (Result r : results){
            if(r.getTest_id().compareTo(result.getTest_id())==0){
                result = r;
                break;
            }
        }
        int correct = 0;
        List<Question> questions = questionRepository.findAllByTest_id(result.getTest_id());
        for (int i = 1; i <= questions.size(); i++) {
            if (questions.get(i).getAnswer().compareTo(result.getAns_list()[i])==0){
                correct++;
            }
        }

        result.setMark((double) (correct / questions.size())*10);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseObject(
                        "SUBMITTED",
                        "FINISHED",
                        rmap.entityToDto(repository.save(result))
                ));
    }

    @Override
    public ResponseEntity<?> updateResult(Result result) {
        try{
            repository.save(result);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "UPDATED",
                            "",
                            rmap.entityToDto(result)
                    ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseObject(
                            "FAILED",
                            "Unable to update",
                            ""
                    ));
        }
    }

    @Override
    public ResponseEntity<?> getResult(ResultDTO resultDTO) {
        List<Result> results = repository.findAllByUser_id(resultDTO.getUser_id());
        if(resultDTO.getTest_id().compareTo("")==0){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Found " + results.size() + " result",
                            results
                    ));
        }
        Result result = null;
        for (Result r : results){
            if(r.getTest_id().compareTo(resultDTO.getTest_id())==0){
                result = r;
                break;
            }
        }
        if(result != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "FOUND",
                            "Found a result",
                            result
                    ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(
                        "EMPTY",
                        "Can not found a result",
                        null
                ));
    }

    @Override
    public ResponseEntity<?> deleteResult(Result result) {
        try{
            repository.deleteById(result.getId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "DELETED",
                            "Delete a result",
                            ""
                    ));
        }catch (Exception e){
            repository.deleteById(result.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            "FAILED",
                            "Unable to delete the result",
                            ""
                    ));
        }
    }
}
