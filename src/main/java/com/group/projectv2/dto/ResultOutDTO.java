package com.group.projectv2.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class ResultOutDTO {
    private LocalDateTime start;
    private LocalDateTime used_time;
    private Boolean is_completed;
    private Integer[] ans_list;
    private Double mark;
    private String test_id;
    private String user_id;
}
