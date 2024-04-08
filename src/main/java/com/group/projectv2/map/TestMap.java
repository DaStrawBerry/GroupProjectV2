package com.group.projectv2.map;

import com.group.projectv2.dto.TestDTO;
import com.group.projectv2.entity.Test;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class TestMap {
    public Test dtoToEntity(TestDTO testDTO){
        Test test = new Test();
        test.setName(testDTO.getName());
        test.setIslimit(testDTO.getIslimit());
        test.setDuration(testDTO.getDuration());
        test.setStart(testDTO.getStart());
        test.setEnd(testDTO.getEnd());
        return test;
    }

    public List<Test> dtoToEntity(List<TestDTO> testDTOS){
        return testDTOS.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

    public TestDTO entityToDto(Test test){
        TestDTO testDTO = new TestDTO();
        testDTO.setName(test.getName());
        testDTO.setIslimit(test.getIslimit());
        testDTO.setDuration(test.getDuration());
        testDTO.setStart(test.getStart());
        testDTO.setEnd(test.getEnd());
        return testDTO;
    }
    public List<TestDTO> entityToDto(List<Test> tests){
        return tests.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
}
