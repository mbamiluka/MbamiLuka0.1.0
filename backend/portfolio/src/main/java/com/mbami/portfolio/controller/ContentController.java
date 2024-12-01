package com.mbami.portfolio.controller;

import com.mbami.portfolio.dto.CreateContentDto;
import com.mbami.portfolio.model.Content;
import com.mbami.portfolio.model.Project;
import com.mbami.portfolio.service.ContentService;
import com.mbami.portfolio.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contents")
@CrossOrigin(origins = "https://mbamiluka-65b99.web.app/")
public class ContentController {

    private final ContentService service;
    private final ProjectService projectService;

    @Autowired
    public ContentController(ContentService service, ProjectService projectService) {
        this.service = service;
        this.projectService = projectService;
    }

    @PostMapping
    public Content create(@RequestBody CreateContentDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<Content> list() {
        return service.findAll();
    }

    // Additional endpoints for update, delete, etc.
}