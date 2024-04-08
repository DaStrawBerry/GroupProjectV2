package com.group.projectv2.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class ResultInDTO {
    private LocalDateTime start;
    private LocalDateTime usedtime;
    private Boolean iscompleted;
    private Integer[] anslist;
    private String testid;
    private String userid;
}
