package com.example.common;

import lombok.Data;

@Data
public class UpdatePwdData {
    private Integer id;
    private String username;
    private String old_password;
    private String password;
}
