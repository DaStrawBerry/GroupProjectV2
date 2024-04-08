package com.group.projectv2.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class TestDTO {
    private String name;
    private Boolean islimit;
    private LocalDateTime duration;
    private LocalDateTime start;
    private LocalDateTime end;
}
