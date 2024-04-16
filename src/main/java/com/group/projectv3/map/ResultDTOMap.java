package com.group.projectv3.map;

import com.group.projectv3.dto.ResultDTO;
import com.group.projectv3.model.Result;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultDTOMap {
    @Autowired
    private ModelMapper modelMapper;

    public Result ResultDTOToResult(ResultDTO resultDTO){
        return modelMapper.map(resultDTO, Result.class);
    }
    public ResultDTO ResultToResultDTO(Result result){
        return modelMapper.map(result, ResultDTO.class);
    }
}
