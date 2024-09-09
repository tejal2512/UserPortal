package com.example.UserManagement_portal.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegisterFormDTO {
    private Integer user_id;
    private String name;
    private String email;
    private String pwd;
    private String phno;
    private Boolean pwd_reset;
    private Integer countryId;
    private Integer stateId;
    private Integer cityId;
}
