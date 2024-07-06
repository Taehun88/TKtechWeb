package com.example.demo.service.user;

import com.example.demo.dto.user.UserDto;
import com.example.demo.exception.CustomException;
import com.example.demo.model.UserTable;
import com.example.demo.repository.UserTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserTableRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto register(UserDto userDto) throws CustomException {

        log.info("[addUser] 유저 저장 로직 시작");

        Optional<UserTable> findUser = userRepository.findById(userDto.getUserId());

        if(findUser.isPresent()){
            log.info("[addUser] 유저 이미 존재");
            throw new CustomException("이미 존재하는 유저입니다.");
        }

        UserTable user = UserTable.builder()
                .userId(userDto.getUserId())
                .password(userDto.getUserPassword())
                .userPhoneNum(userDto.getUserPhoneNum())
                .build();

        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.info("[addUser] 데이터 베이스 접근 오류");
            throw new RuntimeException("데이터베이스 접근 중 오류가 발생했습니다.", e);
        }

        return userDto;
    }

    public ResponseEntity<Object> login(UserDto userDto) throws CustomException {

        log.info("[login] 로그인 로직 시작");

        Optional<UserTable> findUser = userRepository.findById(userDto.getUserId());

        if(findUser.isEmpty()){
            log.info("[login] 유저 없음");
            throw new CustomException("존재하지 않는 유저입니다.");
        }

        UserTable user = findUser.get();

        if(!passwordEncoder.matches(userDto.getUserPassword(), user.getPassword())){
            log.info("[login] 비밀번호 불일치");
            throw new CustomException("비밀번호가 일치하지 않습니다.");
        }

        return ResponseEntity.ok().body(userDto);
    }
}
