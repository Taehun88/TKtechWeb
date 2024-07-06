package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Data
@Entity
@Builder
public class UserTable {

    @Id
    private String userId;

    @Column
    private String password;

    @Column
    private String userPhoneNum;
}
