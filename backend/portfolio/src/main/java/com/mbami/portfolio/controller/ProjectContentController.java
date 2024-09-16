package com.mbami.portfolio.controller;

import com.mbami.portfolio.model.ProjectContent;
import com.mbami.portfolio.service.ProjectContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectContents")
public class ProjectContentController {

    private final ProjectContentService service;

    @Autowired
    public ProjectContentController(ProjectContentService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectContent create(@RequestBody ProjectContent content) {
        return service.save(content);
    }

    @GetMapping
    public List<ProjectContent> list() {
        return service.findAll();
    }

    // Additional endpoints for update, delete, etc.
}