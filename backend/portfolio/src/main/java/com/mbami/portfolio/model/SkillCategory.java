package com.mbami.portfolio.model;

import java.util.Set;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class SkillCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "skillCategories", cascade = CascadeType.ALL)
    private Set<Skill> categorySkills;

    @ManyToMany(mappedBy = "projectCategories")
    private Set<Project> categoryProjects;

    public SkillCategory() {
    }

    public SkillCategory(String name) {
        this.name = name;
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

    public Set<Skill> getSkills() {
        return categorySkills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(Set<Skill> skills) {
        this.categorySkills = skills;
    }

    @Override
    public String toString() {
        return "SkillCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
