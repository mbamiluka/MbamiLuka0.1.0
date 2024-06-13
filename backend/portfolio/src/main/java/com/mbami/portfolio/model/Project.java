package com.mbami.portfolio.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
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

       /*  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
        @ManyToMany
        @JoinTable(name = "project_skill",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
        private List<Skill> projectSkills; */

    public Project() {
    }

    public Project(String name, String description, LocalDate start, LocalDate end) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }
}