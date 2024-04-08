package com.group.projectv2.dto;

import lombok.*;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class QuestionDTO {
    private String testid;
    private Integer n_th;
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
