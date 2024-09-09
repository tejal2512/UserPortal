package com.example.UserManagement_portal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="STATE_MST")
public class StateEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="state_id")
    private Integer stateId;
    @Column(name="state_name")
    private String stateName;
    @Column(name="country_id")
    private Integer countryId;
}
