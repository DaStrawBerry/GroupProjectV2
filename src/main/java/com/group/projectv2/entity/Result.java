package com.group.projectv2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Document(collection = "results")
public class Result {
    @Id
    private String id;
    private LocalDateTime start;
    private LocalDateTime used_time;
    private Boolean is_completed;
    private Integer[] ans_list;
    private Double mark;
    private String test_id;
    private String user_id;
}
