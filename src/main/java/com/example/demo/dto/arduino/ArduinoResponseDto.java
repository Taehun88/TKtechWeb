package com.example.demo.dto.arduino;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArduinoResponseDto {
    private String arduinoName;
    private int arduinoStatus;
    private LocalDateTime arduinoOnTime;
    private LocalDateTime arduinoOffTime;
}
