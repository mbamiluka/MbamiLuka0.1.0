package com.mbami.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mbami.portfolio.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}