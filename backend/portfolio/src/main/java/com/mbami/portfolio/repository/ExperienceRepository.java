package com.mbami.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.ExpType;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.model.User;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByExpType(ExpType expType);
    Experience findByInstitution(String institution);
}