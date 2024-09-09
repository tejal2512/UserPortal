package com.example.UserManagement_portal.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Data
@Entity
@Table(name="USER_MASTER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer user_id;
    private String name;
    @Column(unique = true)
    private String email;
    private String pwd;
    private String phno;
    private Boolean pwd_reset;

    private Integer countryId;

    private Integer stateId;

    private Integer cityId;

    @CreationTimestamp
    @Column(name="created_date",updatable = false)
    private LocalDate created_date;
    @UpdateTimestamp
    @Column(name="updated_date",insertable = false)
    private LocalDate updated_date;
    @PrePersist
    public void prePersist() {
        if (pwd_reset==null) {
            pwd_reset = false;
        }
    }
}
