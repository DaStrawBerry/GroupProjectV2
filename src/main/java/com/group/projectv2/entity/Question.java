package com.group.projectv2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Question {
    @Id
    private String id;
    private String test_id;
    private Integer n_th;
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer answer;
}
