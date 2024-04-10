package com.group.projectv2.controller;

import com.group.projectv2.dto.ResultDTO;
import com.group.projectv2.dto.ResultInDTO;
import com.group.projectv2.entity.Test;
import com.group.projectv2.service.implement.ResultServiceImp;
import com.group.projectv2.service.implement.TestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    TestServiceImp testService;
    @Autowired
    ResultServiceImp resultService;
    @GetMapping("/tests")
    public ResponseEntity<?> testList(){
        return testService.retrieveAllTest();
    }
    @PostMapping("/tests/search")
    public ResponseEntity<?> searchTest(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "true") Boolean is_limit){
        Test test = new Test();
        test.setName(name);
        test.setIslimit(is_limit);
        return testService.retrieveTestByNameOrType(test);
    }
    @PostMapping("/tests/doTest")
    public ResponseEntity<?> doTest(@RequestBody ResultDTO resultDTO){
        return resultService.doTest(resultDTO);
    }
    @PostMapping("/tests/submit")
    public ResponseEntity<?> submitTest(@RequestBody ResultInDTO resultInDTO){
        return resultService.finish(resultInDTO);
    }
    @PostMapping("/tests/result/more")
    public ResponseEntity<?> findResult(@RequestBody ResultDTO resultDTO){
        return resultService.getResult(resultDTO);
    }
}
