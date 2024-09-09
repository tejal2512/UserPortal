package com.example.UserManagement_portal.service;

import com.example.UserManagement_portal.dto.LoginFormDTO;
import com.example.UserManagement_portal.dto.RegisterFormDTO;
import com.example.UserManagement_portal.dto.ResetPasswordDTO;
import com.example.UserManagement_portal.dto.UserDTO;
import com.example.UserManagement_portal.model.UserEntity;

import java.util.Map;

public interface UserService {
    public String findByEmail(String email);

    public UserDTO login(LoginFormDTO loginFormDTO);

    public boolean updatePassword(ResetPasswordDTO resetPasswordDTO);

    public boolean register(RegisterFormDTO registerFormDTO);

    public String generateRandomPwd();

    public Map<Integer,String> getCountriesMap();
    public Map<Integer,String> getStatesMap(Integer countryId);
    public Map<Integer,String> getCitiesMap(Integer stateId);


}
