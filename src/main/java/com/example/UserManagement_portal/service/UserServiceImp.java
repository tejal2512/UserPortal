package com.example.UserManagement_portal.service;

import com.example.UserManagement_portal.dto.LoginFormDTO;
import com.example.UserManagement_portal.dto.RegisterFormDTO;
import com.example.UserManagement_portal.dto.ResetPasswordDTO;
import com.example.UserManagement_portal.dto.UserDTO;
import com.example.UserManagement_portal.model.CityEntity;
import com.example.UserManagement_portal.model.CountryEntity;
import com.example.UserManagement_portal.model.StateEntity;
import com.example.UserManagement_portal.model.UserEntity;
import com.example.UserManagement_portal.repo.CityRepo;
import com.example.UserManagement_portal.repo.CountryRepo;
import com.example.UserManagement_portal.repo.StateRepo;
import com.example.UserManagement_portal.repo.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    private UserRepo userRepo;
    private EmailService emailService;

    private CountryRepo countryRepo;
    private StateRepo stateRepo;
    private CityRepo cityRepo;

    public UserServiceImp(UserRepo userRepo, EmailService emailService, CountryRepo countryRepo, StateRepo stateRepo, CityRepo cityRepo){
        this.userRepo=userRepo;
        this.emailService=emailService;
        this.countryRepo=countryRepo;
        this.stateRepo=stateRepo;
        this.cityRepo=cityRepo;
    }
    @Override
    public String findByEmail(String email) {
        UserEntity user= userRepo.findByEmail(email);
        return (user==null)?"Unique":"Duplicate";
    }

    @Override
    public UserDTO login(LoginFormDTO loginFormDTO) {
        UserEntity userEntity= userRepo.findByEmailAndPwd(loginFormDTO.getEmail(),loginFormDTO.getPwd());
        if(userEntity!=null){
            UserDTO userDTO=new UserDTO();
            BeanUtils.copyProperties(userEntity,userDTO);
            return userDTO;
        }
        return null;
    }

    @Override
    public boolean updatePassword(ResetPasswordDTO resetPasswordDTO) {
        String email=resetPasswordDTO.getEmail();
        UserEntity entity=userRepo.findByEmail(email);
        entity.setPwd(resetPasswordDTO.getNewpwd());
        entity.setPwd_reset(true);
        userRepo.save(entity);
        return true;
    }

    @Override
    public boolean register(RegisterFormDTO registerFormDTO){
        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(registerFormDTO,userEntity);

        String randomPwd=generateRandomPwd();
        userEntity.setPwd(randomPwd);

        UserEntity savedUser=userRepo.save(userEntity);
        System.out.println("Data saved in DB");
        if(savedUser.getUser_id() != null){
            String subject="Thank You for registering to SpringBootDemo";
            String body="Your password to reset "+randomPwd;
            String email=registerFormDTO.getEmail();

            emailService.sendEmail(email,subject,body);
            return true;
        }

        return false;

    }

    @Override
    public String generateRandomPwd() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String pwd = RandomStringUtils.random( 6, characters );
        System.out.println( pwd );
        return pwd;
    }


    public Map<Integer,String> getCountriesMap(){
        Map<Integer,String> countryMap=new HashMap<>();
        List<CountryEntity> countryEntityList=countryRepo.findAll();

        countryEntityList.forEach(c->{
            countryMap.put(c.getCountryId(),c.getCountryName());
        });

        return countryMap;
    }
    public Map<Integer,String> getStatesMap(Integer countryId){
        Map<Integer,String> stateMap=new HashMap<>();
        List<StateEntity> stateEntityList=stateRepo.findByCountryId(countryId);

        stateEntityList.forEach(s->{
            stateMap.put(s.getStateId(),s.getStateName());
        });

        return stateMap;
    }
    public Map<Integer,String> getCitiesMap(Integer stateId){
        Map<Integer,String> cityMap=new HashMap<>();
        List<CityEntity> cityEntityList=cityRepo.findByStateId(stateId);

        cityEntityList.forEach(c->{
            cityMap.put(c.getCityId(),c.getCityName());
        });

        return cityMap;
    }


}
