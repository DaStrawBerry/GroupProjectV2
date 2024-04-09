package com.group.projectv2.controller;

import com.group.projectv2.dto.ResultDTO;
import com.group.projectv2.service.implement.ResultServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/results")
public class ResultController {
    @Autowired
    ResultServiceImp service;
    @GetMapping("/")
    public ResponseEntity<?> getAllResult(){
        return service.getAllResult();
    }

    @PostMapping("/search")
    public ResponseEntity<?> findByStudent(@RequestParam(required = false, defaultValue = "") String student_id){
        ResultDTO resultDTO = new ResultDTO("",student_id);
        return service.getResult(resultDTO);
    }

    @PostMapping("/student_results")
    public ResponseEntity<?> getResult(@RequestBody ResultDTO resultDTO){
        return service.getResult(resultDTO);
    }

    @GetMapping("statistic/filter/{date}")
    public ResponseEntity<?> getResultsByDate(@PathVariable LocalDate date){
        return service.getResultsByDate(date);
    }

    @GetMapping("statistic/filter/{id}")
    public ResponseEntity<?> getResultsByTestId(@PathVariable String testid){
        return service.getResultsByTestId(testid);
    }

    @GetMapping("statistic")
    public ResponseEntity<?> getStatistic(){
        return service.getStatistic();
    }
}
