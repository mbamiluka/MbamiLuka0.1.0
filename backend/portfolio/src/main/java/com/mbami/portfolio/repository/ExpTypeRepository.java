package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.ExpType;
import com.mbami.portfolio.model.Experience; // Add this import statement

public interface ExpTypeRepository extends JpaRepository<ExpType, Long> {

    ExpType findByName(String name);
} 