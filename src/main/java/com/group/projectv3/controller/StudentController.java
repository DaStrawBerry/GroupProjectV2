package com.group.projectv3.controller;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.ResultDTO;
import com.group.projectv3.dto.TestDTO;
import com.group.projectv3.dto.UserDTO;
import com.group.projectv3.service.implement.ResultServiceImp;
import com.group.projectv3.service.implement.TestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private TestServiceImp testService;
    @Autowired
    private ResultServiceImp resultService;

    @GetMapping("/tests")
    public ResponseEntity<ReqRes> allTest(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(testService.allTest());
    }
    @PostMapping("/tests/search")
    public ResponseEntity<ReqRes> searchTest(@RequestBody TestDTO testDTO){
        return ResponseEntity.ok(testService.searchTest(testDTO));
    }
    @PostMapping("/tests/doTest")
    public ResponseEntity<ReqRes> doTest(@RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.doTest(resultDTO));
    }

    @PostMapping("/tests/submit")
    public ResponseEntity<ReqRes> submitTest(@RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.finish(resultDTO));
    }
    @PostMapping("/tests/result")
    public ResponseEntity<ReqRes> findResult(@RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.searchResult(resultDTO));
    }
}
