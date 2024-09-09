package com.example.UserManagement_portal.repo;

import com.example.UserManagement_portal.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepo extends JpaRepository<CityEntity,Integer> {
    public List<CityEntity> findByStateId(Integer stateId);
}
