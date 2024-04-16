package com.group.projectv3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDTO extends ReqRes {
    private String id;
    private LocalDateTime start;
    private LocalDateTime sub;
    private Integer[] anslist;
    private String testcode;
    private String username;
    private String usercode;
    private String userfulname;
}
