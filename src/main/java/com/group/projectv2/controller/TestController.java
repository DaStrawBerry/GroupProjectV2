package com.group.projectv2.controller;

import com.group.projectv2.dto.Pack1;
import com.group.projectv2.dto.TestDTO;
import com.group.projectv2.dto.TestIdDTO;
import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.Test;
import com.group.projectv2.helper.ExcelUploadHelper;
import com.group.projectv2.service.implement.QuestionServiceImp;
import com.group.projectv2.service.implement.TestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/test")
public class TestController {
    @Autowired
    TestServiceImp service;
    @Autowired
    QuestionServiceImp qService;
    @GetMapping("/")
    public ResponseEntity<?> allTest(){
        return service.retrieveAllTest();
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchTest(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "true") Boolean is_limit) {
        Test test = new Test();
        test.setName(name);
        test.setIslimit(is_limit);
        return service.retrieveTestByNameOrType(test);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTest(@RequestBody TestDTO testDTO){
        return service.createTest(testDTO);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editTest(@RequestBody Test test){
        return service.updateTest(test);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTest(@RequestBody Test test){
        return service.deleteTest(test);
    }
    @PostMapping("/questions/test/{id}")
    public ResponseEntity<?> getAllQuestion(@PathVariable String id){
        return qService.retrieveQuestionByTestId(id);
    }
    @PostMapping("/questions")
    public ResponseEntity<?> addQuestion(@RequestBody Pack1 pack){
        return service.addQuestion(pack.getTest(), pack.getQuestion());
    }
    @PostMapping(path = "/questions/excelUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addQuestionsByExcel(@RequestBody Test test, @RequestPart MultipartFile file) throws IOException {
        List<Question> questions = ExcelUploadHelper.excelToQuestions(file.getInputStream());
        return service.addQuestions(test, questions);
    }
    @PutMapping("/questions")
    public ResponseEntity<?> editQuestion(@RequestBody Question question){
        return service.editQuestion(question);
    }

    @DeleteMapping("/questions")
    public ResponseEntity<?> removeQuestion(@RequestBody Question question){
        return service.deleteQuestion(question);
    }
}
