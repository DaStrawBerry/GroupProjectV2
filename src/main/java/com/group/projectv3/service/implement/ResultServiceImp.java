package com.group.projectv3.service.implement;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.ResultDTO;
import com.group.projectv3.map.ResultDTOMap;
import com.group.projectv3.model.Question;
import com.group.projectv3.model.Result;
import com.group.projectv3.model.Test;
import com.group.projectv3.model.User;
import com.group.projectv3.repository.QuestionRepository;
import com.group.projectv3.repository.ResultRepository;
import com.group.projectv3.repository.TestRepository;
import com.group.projectv3.repository.UserRepository;
import com.group.projectv3.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImp implements ResultService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ResultDTOMap resultDTOMap;
    @Override
    public ReqRes allResult() {
        ReqRes resp = new ReqRes();
        try {
            List<Result> results = resultRepository.findAll();
            resp.setStatusCode(200);
            resp.setData(results);
            resp.setMessage("Found " + results.size() + " results");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes searchResult(ResultDTO resultDTO) {
        ReqRes resp = new ReqRes();
        try {
            List<Result> results = resultRepository.findAll();
            if(resultDTO.getUserfulname().trim() != null){
                String name = resultDTO.getUserfulname().trim();
                List<User> userList = userRepository.findAllByNameLike(name);
                results = new ArrayList<>();
                for (User user : userList){
                    List<Result> results1 = resultRepository.findAllByUsername(user.getUsername());
                    for(Result r : results1){
                        results.add(r);
                    }
                }
            }else if(resultDTO.getUsercode().trim() != null){
                String code = resultDTO.getUsercode().trim();
                List<User> userList = userRepository.findAllByCodeLike(code);
                results = new ArrayList<>();
                for (User user : userList){
                    List<Result> results1 = resultRepository.findAllByUsername(user.getUsername());
                    for(Result r : results1){
                        results.add(r);
                    }
                }
            }else if(resultDTO.getTestcode().trim() != null) {
                String code = resultDTO.getTestcode().trim();
                List<Test> testList = testRepository.findAllByCodeLike(code);
                results = new ArrayList<>();
                for (Test t : testList) {
                    List<Result> results1 = resultRepository.findAllByTestcode(t.getCode());
                    for (Result r : results1) {
                        results.add(r);
                    }
                }
            }else if(resultDTO.getStart() != null){
                List<Result> resultstmp = results;
                results = new ArrayList<>();
                for(Result result : resultstmp){
                    if(result.getStart().toLocalDate().compareTo(resultDTO.getStart().toLocalDate()) == 0){
                        results.add(result);
                    }
                }
            }
            resp.setStatusCode(200);
            resp.setData(results);
            resp.setMessage("Found " + results.size() + " results");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes editResult(ResultDTO resultDTO) {
        ReqRes resp = new ReqRes();
        try {
            Result results = resultRepository.findAllById(resultDTO.getId()).orElseThrow();
            resp.setStatusCode(200);
            resp.setData(resultRepository.save(results));
            resp.setMessage("Saved results");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes deleteResult(ResultDTO resultDTO) {
        ReqRes resp = new ReqRes();
        try {
            Result results = resultRepository.findAllById(resultDTO.getId()).orElseThrow();
            resultRepository.delete(results);

            resp.setStatusCode(200);
            resp.setMessage("Deleted results");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes doTest(ResultDTO resultDTO) {
        ReqRes resp = new ReqRes();
        try {
            Result result = new Result();
            result.setUsername(resultDTO.getUsername());
            result.setTestcode(resultDTO.getTestcode());
            result.setMark(0.0);
            result.setIscompleted(false);
            result.setStart(LocalDateTime.now());
            result.setSub(LocalDateTime.of(9999,12,31,24,59,59));
            result.setUsedtime(Duration.ofMinutes(0));

            int n = questionRepository.findAllByTestcode(resultDTO.getTestcode()).size();
            Integer[] anslist = new Integer[n];
            for (int i = 0; i < n; i++) {
                anslist[i] = -1;
            }

            result.setAnslist(anslist);

            resp.setStatusCode(200);
            resp.setData(resultRepository.save(result));
            resp.setMessage("Time start now");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes finish(ResultDTO resultDTO) {
        ReqRes resp = new ReqRes();
        try {
            List<Result> results = resultRepository.findAllByTestcode(resultDTO.getTestcode());
            Result result = null;
            for (Result r : results){
                if(r.getUsername().compareTo(resultDTO.getUsername())==0){
                    result = r;
                    break;
                }
            }

            result.setIscompleted(true);
            result.setSub(LocalDateTime.now());
            result.setUsedtime(Duration.between(result.getStart(), resultDTO.getSub()));
            result.setAnslist(resultDTO.getAnslist());

            int correct = 0;
            List<Question> questions = questionRepository.findAllByTestcode(resultDTO.getTestcode());
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).getAnswer().compareTo(result.getAnslist()[i])==0){
                    correct++;
                }
            }
            result.setMark((double)correct*10 / (double) questions.size());

            resp.setStatusCode(200);
            resp.setData(resultRepository.save(result));
            resp.setMessage("You have finish the test");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
}
