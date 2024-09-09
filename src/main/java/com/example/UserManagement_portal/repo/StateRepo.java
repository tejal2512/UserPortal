package com.example.UserManagement_portal.repo;

import com.example.UserManagement_portal.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StateRepo extends JpaRepository<StateEntity,Integer> {
    public List<StateEntity> findByCountryId(Integer countryId);
}
