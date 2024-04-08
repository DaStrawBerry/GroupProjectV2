package com.group.projectv2.service;

import com.group.projectv2.dto.ResultDTO;
import com.group.projectv2.dto.ResultInDTO;
import com.group.projectv2.entity.Result;
import org.springframework.http.ResponseEntity;

public interface ResultService {
    public ResponseEntity<?> getAllResult();
    public ResponseEntity<?> doTest(ResultDTO resultDTO);
    public ResponseEntity<?> finish(ResultInDTO resultInDTO);
    public ResponseEntity<?> updateResult(Result result);
    public ResponseEntity<?> getResult(ResultDTO resultDTO);
    public ResponseEntity<?> deleteResult(Result result);
}
