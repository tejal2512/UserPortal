package com.example.UserManagement_portal.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="CITY_MASTER")
public class CityEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="city_id")
    private Integer cityId;
    @Column(name="city_name")
    private String cityName;
    @Column(name="state_id")
    private Integer stateId;
}
