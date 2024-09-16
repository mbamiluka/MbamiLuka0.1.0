package com.mbami.portfolio.service;

import com.mbami.portfolio.model.ProjectContent;
import com.mbami.portfolio.repository.ProjectContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectContentService {

    private final ProjectContentRepository repository;

    @Autowired
    public ProjectContentService(ProjectContentRepository repository) {
        this.repository = repository;
    }

    public ProjectContent save(ProjectContent content) {
        return repository.save(content);
    }

    public List<ProjectContent> findAll() {
        return repository.findAll();
    }

    public List<ProjectContent> findByProjectId(Long projectId) {
        return repository.findByContentProjectId(projectId);
    }

    public ProjectContent findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    /* public void deleteByProjectId(Long projectId) {
        repository.deleteByContentProjectId(projectId);
    } */

    public ProjectContent update(Long id, ProjectContent content) {
        ProjectContent existingContent = repository.findById(id).orElse(null);
        if (existingContent == null) {
            return null;
        }
        existingContent.setContentType(content.getContentType());
        existingContent.setContent(content.getContent());
        existingContent.setContentOrder(content.getContentOrder());
        return repository.save(existingContent);
    }

    public ProjectContent updateOrder(Long id, int order) {
        ProjectContent existingContent = repository.findById(id).orElse(null);
        if (existingContent == null) {
            return null;
        }
        existingContent.setContentOrder(order);
        return repository.save(existingContent);
    }

    public ProjectContent updateContent(Long id, String content) {
        ProjectContent existingContent = repository.findById(id).orElse(null);
        if (existingContent == null) {
            return null;
        }
        existingContent.setContent(content);
        return repository.save(existingContent);
    }
}