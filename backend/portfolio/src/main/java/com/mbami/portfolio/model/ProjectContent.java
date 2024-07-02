package com.mbami.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentType;
    private String content;
    private int contentOrder;
    
    @ManyToOne
    private Project contentProject;

    public ProjectContent() {
    }
    
    public ProjectContent(Long id, String contentType, String content) {
        this.id = id;
        this.contentType = contentType;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(int order) {
        this.contentOrder = order;
    }

    public Project getContentProject() {
        return contentProject;
    }

    public void setContentProject(Project contentProject) {
        this.contentProject = contentProject;
    }
}
