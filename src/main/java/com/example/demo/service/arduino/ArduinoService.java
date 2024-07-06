package com.example.demo.service.arduino;

import com.example.demo.dto.arduino.ArduinoAddDto;
import com.example.demo.dto.arduino.ArduinoResponseDto;

public interface ArduinoService {

    void addArduino(ArduinoAddDto arduinoAddDto);
    void updateStatus(String arduinoName, int status);
    ArduinoResponseDto getArduinoStatus(String arduinoName);
    void setOnTime(String arduinoName, String onTime);
    void setOffTime(String arduinoName, String offTime);

}
