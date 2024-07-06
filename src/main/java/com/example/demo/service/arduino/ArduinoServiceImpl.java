package com.example.demo.service.arduino;

import com.example.demo.dto.arduino.ArduinoAddDto;
import com.example.demo.dto.arduino.ArduinoResponseDto;
import com.example.demo.model.ArduinoStatusTable;
import com.example.demo.repository.ArduinoStatusTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArduinoServiceImpl implements ArduinoService {

    private final ArduinoStatusTableRepository arduinoStatusTableRepository;

    @Override
    public void addArduino(ArduinoAddDto arduinoAddDto) {

        List<ArduinoStatusTable> findAll = arduinoStatusTableRepository.findAllByArduinoName(arduinoAddDto.getArduinoName());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime StartTime;
        LocalDateTime EndTime;

        if(now.isBefore(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 7, 0, 0))) {
            StartTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 7, 0, 0);
            EndTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 19, 0, 0);
        } else {
            StartTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 7, 0, 0).plusDays(1);
            EndTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 19, 0, 0).plusDays(1);
        }

        if(!findAll.isEmpty()){
            log.info("[addArduino] 아두이노 이미 존재");
            return;
        }

        ArduinoStatusTable arduinoStatusTable = ArduinoStatusTable.builder()
                .arduinoName(arduinoAddDto.getArduinoName())
                .arduinoStatus(2)
                .arduinoOnTime(StartTime)
                .arduinoOffTime(EndTime)
                .build();

        try{
            arduinoStatusTableRepository.save(arduinoStatusTable);
        }catch (Exception e){
            log.info("[addArduino] 데이터 베이스 접근 오류");
            throw new RuntimeException("데이터베이스 접근 중 오류가 발생했습니다.", e);

        }

    }

    @Override
    public void updateStatus(String arduinoName, int status) {

        List<ArduinoStatusTable> findAll = arduinoStatusTableRepository.findAllByArduinoName(arduinoName);

        if(findAll.isEmpty()){
            log.info("[updateStatus] 아두이노 존재하지 않음");
            throw new RuntimeException("아두이노가 존재하지 않습니다.");
        }

        ArduinoStatusTable arduinoStatusTable = findAll.get(0);
        arduinoStatusTable.setArduinoStatus(status);

        try{
            arduinoStatusTableRepository.save(arduinoStatusTable);
        }catch (Exception e){
            log.info("[updateStatus] 데이터 베이스 접근 오류");
            throw new RuntimeException("데이터베이스 접근 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public ArduinoResponseDto getArduinoStatus(String arduinoName) {
        List<ArduinoStatusTable> findAll = arduinoStatusTableRepository.findAllByArduinoName(arduinoName);

        if(findAll.isEmpty()){
            log.info("[getStatus] 아두이노 존재하지 않음");
            throw new RuntimeException("아두이노가 존재하지 않습니다.");
        }

        ArduinoStatusTable arduinoStatusTable = findAll.get(0);

        return ArduinoResponseDto.builder()
                .arduinoName(arduinoStatusTable.getArduinoName())
                .arduinoStatus(arduinoStatusTable.getArduinoStatus())
                .arduinoOnTime(arduinoStatusTable.getArduinoOnTime())
                .arduinoOffTime(arduinoStatusTable.getArduinoOffTime())
                .build();
    }

    @Override
    public void setOnTime(String arduinoName, String onTime) {
        List<ArduinoStatusTable> findAll = arduinoStatusTableRepository.findAllByArduinoName(arduinoName);

        if(findAll.isEmpty()){
            log.info("[setOnTime] 아두이노 존재하지 않음");
            throw new RuntimeException("아두이노가 존재하지 않습니다.");
        }

        ArduinoStatusTable arduinoStatusTable = findAll.get(0);
        arduinoStatusTable.setArduinoOnTime(LocalDateTime.parse(onTime));
        arduinoStatusTable.setArduinoOffTime(LocalDateTime.parse(onTime).plusHours(12));

        try{
            arduinoStatusTableRepository.save(arduinoStatusTable);
        }catch (Exception e){
            log.info("[setOnTime] 데이터 베이스 접근 오류");
            throw new RuntimeException("데이터베이스 접근 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void setOffTime(String arduinoName, String offTime) {
        List<ArduinoStatusTable> findAll = arduinoStatusTableRepository.findAllByArduinoName(arduinoName);

        if(findAll.isEmpty()){
            log.info("[setOffTime] 아두이노 존재하지 않음");
            throw new RuntimeException("아두이노가 존재하지 않습니다.");
        }

        ArduinoStatusTable arduinoStatusTable = findAll.get(0);
        arduinoStatusTable.setArduinoOffTime(LocalDateTime.parse(offTime));

        try{
            arduinoStatusTableRepository.save(arduinoStatusTable);
        }catch (Exception e){
            log.info("[setOffTime] 데이터 베이스 접근 오류");
            throw new RuntimeException("데이터베이스 접근 중 오류가 발생했습니다.", e);
        }
    }
}
