package com.group.projectv2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
    private String code;
    private String password;
    private String email;
    private String fullname;
    private LocalDateTime date_of_birth;
    private Boolean is_admin;
}
