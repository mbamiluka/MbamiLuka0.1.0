package com.mbami.portfolio.service;

import com.mbami.portfolio.dto.CreateContentDto;
import com.mbami.portfolio.model.Content;
import com.mbami.portfolio.model.Project;
import com.mbami.portfolio.repository.ContentRepository;
import com.mbami.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository repository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ContentService(ContentRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Content create(CreateContentDto content) {
        Project project = projectRepository.findById(content.getProjectId()).orElse(null);
        if (project == null) {
            return null;
        }
        Content newContent = new Content();
        newContent.setName(content.getName());
        newContent.setContentType(content.getContentType());
        newContent.setContent(content.getContent());
        newContent.setContentProject(project);

        return repository.save(newContent);
    }

    public List<Content> findAll() {
        return repository.findAll();
    }

    public List<Content> findByProjectId(Long projectId) {
        return repository.findByContentProjectId(projectId);
    }

    public Content findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    /* public void deleteByProjectId(Long projectId) {
        repository.deleteByContentProjectId(projectId);
    } */

    public Content update(Long id, Content content) {
        Content existingContent = repository.findById(id).orElse(null);
        if (existingContent == null) {
            return null;
        }
        existingContent.setContentType(content.getContentType());
        existingContent.setContent(content.getContent());
        existingContent.setContentOrder(content.getContentOrder());
        return repository.save(existingContent);
    }

    public Content updateOrder(Long id, int order) {
        Content existingContent = repository.findById(id).orElse(null);
        if (existingContent == null) {
            return null;
        }
        existingContent.setContentOrder(order);
        return repository.save(existingContent);
    }

    public Content updateContent(Long id, String content) {
        Content existingContent = repository.findById(id).orElse(null);
        if (existingContent == null) {
            return null;
        }
        existingContent.setContent(content);
        return repository.save(existingContent);
    }
}