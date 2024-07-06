package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArduinoStatusTable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int arduinoId;

    @Column
    private String arduinoName;

    @Column
    private int arduinoStatus;

    @Column
    private float arduinoLatitude;

    @Column
    private float arduinoLongitude;

    @Column
    private LocalDateTime arduinoOnTime;

    @Column
    private LocalDateTime arduinoOffTime;

    @ManyToOne
    @JoinTable(name = "userId")
    private UserTable userId;
}
