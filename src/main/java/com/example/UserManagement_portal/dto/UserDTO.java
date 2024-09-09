package com.example.UserManagement_portal.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {
    private Integer user_id;
    private String email;
    private String pwd;
    private Boolean pwd_reset;

}
