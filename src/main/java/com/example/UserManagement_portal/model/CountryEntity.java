package com.example.UserManagement_portal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY_MASTER")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="country_id")
    Integer countryId;
    @Column(name="country_name")
    String countryName;

}
