package com.mbami.portfolio.service;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.Project;
import com.mbami.portfolio.model.ProjectContent;
import com.mbami.portfolio.repository.ProjectContentRepository;
import com.mbami.portfolio.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectContentRepository projectContentRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectContentRepository projectContentRepository) {
        this.projectRepository = projectRepository;
        this.projectContentRepository = projectContentRepository;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    public Project getProject(long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void updateProject(long id, Project project) {
        Project existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStart(project.getStart());
            existingProject.setEnd(project.getEnd());
            existingProject.setSourceCode(project.getSourceCode());
            existingProject.setDemo(project.getDemo());
            existingProject.setImage(project.getImage());
            existingProject.setType(project.getType());
            existingProject.setCategory(project.getCategory());
            existingProject.setStatus(project.getStatus());
            projectRepository.save(existingProject);
        }
    }

    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }

    public ResponseEntity<ProjectContent> addContent(long projectId, ProjectContent projectContent) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            projectContent.setContentProject(project);
            projectContentRepository.save(projectContent);
            return ResponseEntity.ok(projectContent);
        }
        return ResponseEntity.notFound().build();
    }

    public ProjectContent getContent(long projectId, long contentId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            ProjectContent projectContent = projectContentRepository.findById(contentId).orElse(null);
            if (projectContent != null) {
                return projectContent;
            }
        }
        return null;
    }

    public List<ProjectContent> getAllContentsByProjectId(Long projectId) {
        return projectContentRepository.findByContentProjectId(projectId);
    }
}
