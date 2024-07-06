package com.example.demo.dto.user;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    private String userPassword;

    private String userPhoneNum;

}
