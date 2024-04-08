package com.group.projectv2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Document(collection = "tests")
public class Test {
    @Id
    private String id;
    private String name;
    private Boolean is_limit;
    private LocalDateTime duration;
    private LocalDateTime start;
    private LocalDateTime end;
}
