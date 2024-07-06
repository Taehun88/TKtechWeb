package com.example.demo.dto.arduino;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArduinoAddDto {

    private String arduinoName;

    private boolean arduinoStatus;

    private String arduinoLatitude;

    private String arduinoLongitude;

    private String userId;

}
