package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.ExpType;
import com.mbami.portfolio.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByExpType(ExpType expType);
}