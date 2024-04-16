package com.group.projectv3.service;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.ResultDTO;
import com.group.projectv3.model.Result;

import java.util.List;

public interface ResultService {
    public ReqRes allResult();
    public ReqRes searchResult(ResultDTO resultDTO);
    public ReqRes editResult(ResultDTO resultDTO);
    public ReqRes deleteResult(ResultDTO resultDTO);
    public ReqRes doTest(ResultDTO resultDTO);
    public ReqRes finish(ResultDTO resultDTO);
}
