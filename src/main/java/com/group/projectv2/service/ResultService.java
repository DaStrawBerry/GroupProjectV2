package com.group.projectv2.service;

import com.group.projectv2.dto.ResultDTO;
import com.group.projectv2.dto.ResultInDTO;
import com.group.projectv2.entity.Result;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface ResultService {
    public ResponseEntity<?> getAllResult();
    public ResponseEntity<?> doTest(ResultDTO resultDTO);
    public ResponseEntity<?> finish(ResultInDTO resultInDTO);
    public ResponseEntity<?> getResult(ResultDTO resultDTO);
    public ResponseEntity<?> getResultsByTestId(String testid);
    public ResponseEntity<?> getResultsByDate(LocalDate start);
    public ResponseEntity<?> getStatistic();
    public ResponseEntity<?> updateResult(Result result);
    public ResponseEntity<?> deleteResult(Result result);
}
