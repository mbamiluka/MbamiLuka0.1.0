package com.mbami.portfolio.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate start;
    private LocalDate end;
    private String sourceCode;
    private String demo;
    private String image;
    private String type; // personal, group, university, company
    private String category; // web, mobile, desktop, game
    private String status; // ongoing, completed

    @ManyToOne
    private Experience projectExperience;

    @ManyToMany
    private List<Skill> projectSkills;
    
    @OneToMany(mappedBy = "contentProject", cascade = CascadeType.ALL)
    private List<ProjectContent> projectContents;

    public Project() {
    }

    public Project(Long id, String name, String description, LocalDate start, LocalDate end, String sourceCode, String demo,
            String image, String type, String category, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.sourceCode = sourceCode;
        this.demo = demo;
        this.image = image;
        this.type = type;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Experience getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(Experience projectExperience) {
        this.projectExperience = projectExperience;
    }

    public List<Skill> getProjectSkills() {
        return projectSkills;
    }

    public void setProjectSkills(List<Skill> projectSkills) {
        this.projectSkills = projectSkills;
    }

    public List<ProjectContent> getProjectContents() {
        return projectContents;
    }

    public void setProjectContents(List<ProjectContent> projectContents) {
        this.projectContents = projectContents;
    }
}