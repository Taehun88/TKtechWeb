package com.example.demo.repository;

import com.example.demo.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTableRepository extends JpaRepository<UserTable, String> {
}
