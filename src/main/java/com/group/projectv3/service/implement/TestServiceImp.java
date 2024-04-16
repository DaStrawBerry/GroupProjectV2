package com.group.projectv3.service.implement;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.TestDTO;
import com.group.projectv3.map.TestDTOMap;
import com.group.projectv3.model.Test;
import com.group.projectv3.repository.TestRepository;
import com.group.projectv3.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImp implements TestService {

    @Autowired
    private TestDTOMap map;
    @Autowired
    private TestRepository repository;
    @Override
    public ReqRes allTest() {
        ReqRes resp = new ReqRes();
        try {
            List<Test> tests = repository.findAll();
            resp.setStatusCode(200);
            resp.setData(tests);
            resp.setMessage("Found " + tests.size() + " test");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;

    }

    @Override
    public ReqRes searchTest(TestDTO testDTO) {
        ReqRes resp = new ReqRes();
        try {
            List<Test> tests = new ArrayList<>();

            if(testDTO.getName() != null){
                tests = repository.findAllByNameLike(testDTO.getName());
            } else if (testDTO.getIslimit() != null) {
                tests = repository.findAllByIslimit(testDTO.getIslimit());
            }

            resp.setStatusCode(200);
            resp.setData(tests);
            resp.setMessage("Found " + tests.size() + " test");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes createTest(TestDTO testDTO) {
        ReqRes resp = new ReqRes();
        try {
            Test test = map.TestDTOToTest(testDTO);
            if(test.getCode() == null){
                test.setCode(test.getId().toUpperCase());
            }else if(repository.findByCode(test.getCode()).isPresent()){
                throw new Exception("Test code existed");
            }

            if(test.getName() == null){
                test.setName(testDTO.getCode());
            }
            if (test.getIslimit() == null){
                test.setIslimit(false);
            }
            if (test.getDuration() == null){
                Duration dur = Duration.ofDays(100*365);
                test.setDuration(dur);
            }
            if (test.getStart() == null){
                LocalDateTime sta = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                test.setStart(sta);
            }
            if (test.getEnd() == null){
                LocalDateTime edn = LocalDateTime.of(9999,12,31,23,59,59);
                test.setEnd(edn);
            }

            resp.setStatusCode(200);
            resp.setData(repository.save(test));
            resp.setMessage("Test Created");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes editTest(TestDTO testDTO) {
        ReqRes resp = new ReqRes();
        try {
            Test test = map.TestDTOToTest(testDTO);
            System.out.println(test);
            Optional<Test> pret = repository.findByCode(test.getCode());
            if(pret.isEmpty()){
                throw new Exception("No test: "+ test.getCode() + " exist");
            }
            test.setId(pret.get().getId());

            resp.setStatusCode(200);
            resp.setData(repository.save(test));
            resp.setMessage("Test Edited");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes deleteTest(TestDTO testDTO) {
        ReqRes resp = new ReqRes();
        try {
            Optional<Test> test = repository.findByCode(testDTO.getCode());
            if(test.isEmpty()){
                throw new Exception("Test :" + testDTO.getCode() + "does not exits");
            }
            resp.setStatusCode(200);
            resp.setMessage("Test Deleted");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
}
