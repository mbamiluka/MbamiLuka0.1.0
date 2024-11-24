package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByContentProjectId(Long projectId);
}
