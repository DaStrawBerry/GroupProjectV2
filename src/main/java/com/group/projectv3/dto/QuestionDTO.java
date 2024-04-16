package com.group.projectv3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDTO extends ReqRes{
    private String testcode;
    private String code;
    private Integer nth;
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer answer;
}
