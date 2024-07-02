package com.mbami.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
