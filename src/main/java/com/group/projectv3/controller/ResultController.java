package com.group.projectv3.controller;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.ResultDTO;
import com.group.projectv3.service.implement.ResultServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class ResultController {
    @Autowired
    private ResultServiceImp resultService;

    @GetMapping("/results")
    public ResponseEntity<ReqRes> allResult(){
        return ResponseEntity.ok(resultService.allResult());
    }
    @PostMapping("/results/search")
    public ResponseEntity<ReqRes> searchResult(@RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.searchResult(resultDTO));
    }

    @PutMapping("/results/edit")
    public ResponseEntity<ReqRes> editResult(@RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.editResult(resultDTO));
    }
    @DeleteMapping("/results/delete")
    public ResponseEntity<ReqRes> deleteResult(@RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.deleteResult(resultDTO));
    }
}
