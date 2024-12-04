package com.mbami.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbami.portfolio.dto.CreateProjectDto;
import com.mbami.portfolio.model.Project;
import com.mbami.portfolio.model.Content;
import com.mbami.portfolio.service.ProjectService;

@RestController
@CrossOrigin(origins = {"https://mbamiluka-65b99.web.app/", "https://mbamiluka-65b99.firebaseapp.com/", "http://localhost:3000"})
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public Project addProject(@RequestHeader("Authorization") String token, @RequestBody CreateProjectDto project) {
        return projectService.addProject(token, project);
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable long id) {
        return projectService.getProject(id);
    }

    @PutMapping("/{id}")
    public void updateProject(@PathVariable long id, @RequestBody Project project){
        projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
    }

    // In ProjectController class
    @PostMapping("/{projectId}/content")
    public ResponseEntity<Content> addProjectContent(@PathVariable Long projectId, @RequestBody Content content) {
        return projectService.addContent(projectId, content);
    }
}
