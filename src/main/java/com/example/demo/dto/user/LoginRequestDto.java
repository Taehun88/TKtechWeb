package com.example.demo.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    private String userId;
    private String userPassword;

}
