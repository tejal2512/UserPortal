package com.example.UserManagement_portal.repo;

import com.example.UserManagement_portal.dto.RegisterFormDTO;
import com.example.UserManagement_portal.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    public UserEntity findByEmail(String email);

    public UserEntity findByEmailAndPwd(String email,String pwd);


}
