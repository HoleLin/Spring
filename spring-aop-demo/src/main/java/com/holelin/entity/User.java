package com.holelin.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    private Long version;
}
