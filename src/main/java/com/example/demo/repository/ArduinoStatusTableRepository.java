package com.example.demo.repository;

import com.example.demo.model.ArduinoStatusTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArduinoStatusTableRepository extends JpaRepository<ArduinoStatusTable, String> {
    List<ArduinoStatusTable> findAllByArduinoName(String arduinoName);
}
