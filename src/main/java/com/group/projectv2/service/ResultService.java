package com.group.projectv2.service;

import com.group.projectv2.dto.ResultInDTO;
import com.group.projectv2.entity.Result;
import com.group.projectv2.entity.Test;
import com.group.projectv2.entity.User;
import org.springframework.http.ResponseEntity;

public interface ResultService {
    public ResponseEntity<?> doTest(User user, Test test);
    public ResponseEntity<?> finish(ResultInDTO resultInDTO);
    public ResponseEntity<?> updateResult(Result result);
    public ResponseEntity<?> getResult(ResultInDTO resultInDTO);
    public ResponseEntity<?> deleteResult(Result result);
}
