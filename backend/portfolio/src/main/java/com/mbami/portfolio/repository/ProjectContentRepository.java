package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.ProjectContent;

public interface ProjectContentRepository extends JpaRepository<ProjectContent, Long> {
    List<ProjectContent> findByContentProjectId(Long projectId);
}
