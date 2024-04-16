package com.group.projectv3.controller;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.TestDTO;
import com.group.projectv3.service.implement.TestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class TestController {
    @Autowired
    private TestServiceImp testService;

    @GetMapping("/tests")
    public ResponseEntity<ReqRes> allTest(){
        return ResponseEntity.ok(testService.allTest());
    }
    @PostMapping("/tests/search")
    public ResponseEntity<ReqRes> searchTest(@RequestBody TestDTO testDTO){
        return ResponseEntity.ok(testService.searchTest(testDTO));
    }

    @PostMapping("/tests/create")
    public ResponseEntity<ReqRes> createTest(@RequestBody TestDTO testDTO){
        return ResponseEntity.ok(testService.createTest(testDTO));
    }

    @PutMapping("/tests/edit")
    public ResponseEntity<ReqRes> editTest(@RequestBody TestDTO testDTO){
        return ResponseEntity.ok(testService.editTest(testDTO));
    }

    @DeleteMapping("/tests/delete")
    public ResponseEntity<ReqRes> deleteTest(@RequestBody TestDTO testReq){
        return ResponseEntity.ok(testService.deleteTest(testReq));
    }
}
