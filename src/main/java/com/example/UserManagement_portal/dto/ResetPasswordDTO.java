package com.example.UserManagement_portal.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String email;
    private String oldpwd;
    private String newpwd;
    private String confirmnewpwd;
}
