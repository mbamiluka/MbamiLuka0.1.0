package com.mbami.portfolio.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    private String name;
    private Date mmStartDate;
    private Date mmEndDate;

    @ManyToMany
    @JoinTable(name = "skill_category_skill",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<SkillCategory> skillCategories;

    @ManyToMany(mappedBy = "projectSkills")
    private Set<Project> skillProjects;

    public Skill() {
    }

    public Skill(String name, Date mmStartDate, Date mmEndDate) {
        this.name = name;
        this.mmStartDate = mmStartDate;
        this.mmEndDate = mmEndDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getMmStartDate() {
        return mmStartDate;
    }

    public Date getMmEndDate() {
        return mmEndDate;
    }

    public Set<SkillCategory> getSkillCategories() {
        return skillCategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMmStartDate(Date mmStartDate) {
        this.mmStartDate = mmStartDate;
    }

    public void setMmEndDate(Date mmEndDate) {
        this.mmEndDate = mmEndDate;
    }

    public void setSkillCategories(Set<SkillCategory> skillCategories) {
        this.skillCategories = skillCategories;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mmStartDate=" + mmStartDate +
                ", mmEndDate=" + mmEndDate +
                ", skillCategories=" + skillCategories +
                '}';
    }
}
