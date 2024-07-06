package com.example.demo.controller;

import com.example.demo.dto.arduino.ArduinoAddDto;
import com.example.demo.dto.arduino.ArduinoResponseDto;
import com.example.demo.service.arduino.ArduinoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/arduino")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArduinoController {

    private final ArduinoService arduinoService;

    @PostMapping("/status")
    public ResponseEntity<Map<String, Integer>> changeArduinoStatus(@RequestBody ArduinoResponseDto arduinoResponseDto){
        log.info("[changeArduinoStatus] 아두이노 상태 변경 요청");

        arduinoService.updateStatus(arduinoResponseDto.getArduinoName(), arduinoResponseDto.getArduinoStatus());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/now")
    public ResponseEntity<Object> getArduinoStatus(@RequestParam String arduinoName){
        log.info("[getArduinoStatus] 아두이노 상태 조회 요청");

        try{
            ArduinoResponseDto arduinoStatus = arduinoService.getArduinoStatus(arduinoName);
            return ResponseEntity.ok(arduinoStatus);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Integer>> addArduinoStatus(@RequestBody ArduinoAddDto arduinoAddDto){
        log.info("[addArduinoStatus] 아두이노 추가 요청");

        arduinoService.addArduino(arduinoAddDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/set/ontime")
    public ResponseEntity<Map<String,Integer>> setOnTime(@RequestBody String arduinoName, String onTime){
        log.info("[setOnTime] 아두이노 켜는 시간 설정 요청");

        arduinoService.setOnTime(arduinoName, onTime);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/set/offtime")
    public ResponseEntity<Map<String,Integer>> setOffTime(@RequestBody String arduinoName, String offTime){
        log.info("[setOffTime] 아두이노 끄는 시간 설정 요청");

        arduinoService.setOffTime(arduinoName, offTime);

        return ResponseEntity.ok().build();
    }
}
