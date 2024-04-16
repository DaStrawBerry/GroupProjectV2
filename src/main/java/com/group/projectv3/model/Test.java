package com.group.projectv3.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Document(collection = "tests")
public class Test {
    @Id
    private String id;
    private String name;
    private String code;
    private Boolean islimit;
    private Duration duration;
    private LocalDateTime start;
    private LocalDateTime end;
}
