package com.group.projectv3.controller;

import com.group.projectv3.dto.QuestionDTO;
import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.service.implement.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/test/")
public class QuestionController {
    @Autowired
    private QuestionServiceImp questionService;

    @GetMapping("/questions")
    public ResponseEntity<ReqRes> allQuestion(@RequestBody QuestionDTO questionDTO){
        return ResponseEntity.ok(questionService.allQuestion(questionDTO));
    }
    @PostMapping("/questions/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDTO questionDTO){
        return ResponseEntity.ok(questionService.addQuestion(questionDTO));
    }
    @PutMapping("/questions/edit")
    public ResponseEntity<?> editQuestion(@RequestBody QuestionDTO questionDTO){
        return ResponseEntity.ok(questionService.editQuestion(questionDTO));
    }
    @DeleteMapping("/questions/delete")
    public ResponseEntity<?> deleteQuestion(@RequestBody QuestionDTO questionDTO){
        return ResponseEntity.ok(questionService.deleteQuestion(questionDTO));
    }
    @DeleteMapping("/questions/deleteall")
    public ResponseEntity<?> deleteAllQuestion(@RequestBody QuestionDTO questionDTO){
        return ResponseEntity.ok(questionService.deleteQuestionByTestId(questionDTO));
    }
}
