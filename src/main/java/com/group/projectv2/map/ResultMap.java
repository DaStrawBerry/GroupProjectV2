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
        result.setUsedtime(resultInDTO.getUsedtime());
        result.setIscompleted(resultInDTO.getIscompleted());
        result.setAnslist(resultInDTO.getAnslist());
        result.setTestid(resultInDTO.getTestid());
        result.setUserid(resultInDTO.getUserid());
        return result;
    }

    public List<Result> dtoToEntity(List<ResultInDTO> resultInDTOS){
        return resultInDTOS.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

    public ResultOutDTO entityToDto(Result result){
        ResultOutDTO resultOutDTO = new ResultOutDTO();
        resultOutDTO.setStart(result.getStart());
        resultOutDTO.setUsedtime(result.getUsedtime());
        resultOutDTO.setIscompleted(result.getIscompleted());
        resultOutDTO.setAnslist(result.getAnslist());
        resultOutDTO.setMark(result.getMark());
        resultOutDTO.setTestid(result.getTestid());
        resultOutDTO.setUserid(result.getUserid());
        return resultOutDTO;
    }

    public List<ResultOutDTO> entityToDto(List<Result> results){
        return results.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
}
