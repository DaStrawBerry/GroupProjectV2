package com.group.projectv2.map;

import com.group.projectv2.dto.ResultInDTO;
import com.group.projectv2.dto.ResultOutDTO;
import com.group.projectv2.entity.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultMap {
    public Result dtoToEntity(ResultInDTO resultInDTO){
        Result result = new Result();
        result.setStart(resultInDTO.getStart());
        result.setUsed_time(resultInDTO.getUsed_time());
        result.setIs_completed(resultInDTO.getIs_completed());
        result.setAns_list(resultInDTO.getAns_list());
        result.setTest_id(resultInDTO.getTest_id());
        result.setUser_id(resultInDTO.getUser_id());
        return result;
    }

    public List<Result> dtoToEntity(List<ResultInDTO> resultInDTOS){
        return resultInDTOS.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

    public ResultOutDTO entityToDto(Result result){
        ResultOutDTO resultOutDTO = new ResultOutDTO();
        resultOutDTO.setStart(result.getStart());
        resultOutDTO.setUsed_time(result.getUsed_time());
        resultOutDTO.setIs_completed(result.getIs_completed());
        resultOutDTO.setAns_list(result.getAns_list());
        resultOutDTO.setMark(result.getMark());
        resultOutDTO.setTest_id(result.getTest_id());
        resultOutDTO.setUser_id(result.getUser_id());
        return resultOutDTO;
    }

    public List<ResultOutDTO> entityToDto(List<Result> results){
        return results.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
}
