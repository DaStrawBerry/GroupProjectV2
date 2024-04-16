package com.group.projectv3.map;

import com.group.projectv3.dto.TestDTO;
import com.group.projectv3.model.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDTOMap {
    @Autowired
    private ModelMapper modelMapper;

    public Test TestDTOToTest(TestDTO testDTO){
        return modelMapper.map(testDTO, Test.class);
    }

    public TestDTO TestToTestDTO(Test test){
        return modelMapper.map(test, TestDTO.class);
    }
}
