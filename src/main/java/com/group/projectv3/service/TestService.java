package com.group.projectv3.service;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.TestDTO;

public interface TestService {
    public ReqRes allTest();
    public ReqRes searchTest(TestDTO testDTO);
    public ReqRes createTest(TestDTO testDTO);
    public ReqRes editTest(TestDTO testDTO);
    public ReqRes deleteTest(TestDTO testDTO);

}
