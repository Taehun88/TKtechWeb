package com.example.demo.service.user;

import com.example.demo.dto.user.UserDto;
import com.example.demo.exception.CustomException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDto register(UserDto userDto) throws CustomException;

    ResponseEntity<Object> login(UserDto userDto) throws CustomException;

    

}
