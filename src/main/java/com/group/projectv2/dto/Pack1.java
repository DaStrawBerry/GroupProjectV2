package com.group.projectv2.dto;

import com.group.projectv2.entity.Question;
import com.group.projectv2.entity.Test;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pack1 {
    private Test test;
    private Question question;
}
