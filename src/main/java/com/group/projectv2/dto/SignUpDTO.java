package com.group.projectv2.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class SignUpDTO {
    private String username;
    private String code;
    private String password;
    private String repasswd;
    private String email;
    private String fullname;
    private LocalDateTime date_of_birth;
}
