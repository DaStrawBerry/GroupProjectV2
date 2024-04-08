package com.group.projectv2.entity;

import lombok.*;

@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
