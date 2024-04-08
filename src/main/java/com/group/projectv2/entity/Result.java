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
    private LocalDateTime usedtime;
    private Boolean iscompleted;
    private Integer[] anslist;
    private Double mark;
    private String testid;
    private String userid;
}
