package com.example.UserManagement_portal.repo;

import com.example.UserManagement_portal.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<CountryEntity,Integer> {
}
