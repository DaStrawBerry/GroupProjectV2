package com.group.projectv3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDTO extends ReqRes{
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean islimit;
    private Duration duration;
    private String name;
    private String code;
}
