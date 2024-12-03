package com.mbami.portfolio.dto;

import java.time.LocalDate;
import java.util.List;

import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;

public class CreateProjectDto {
    private String name;
    private String description; 
    private LocalDate start;
    private LocalDate end;
    private String sourceCode;
    private String demo;
    private String image;
    private String type;
    private String status;
    private List<Long> skillIds;
    private List<Long> categoryIds;
    private CreateExperienceDto projectExperience;
    private List<ExpRole> projectExpRoles;

    public CreateProjectDto() {
    }

    public CreateProjectDto(String name, String description, LocalDate start, LocalDate end, String sourceCode, String demo,
            String image, String type, String status, List<Long> skillIds, List<Long> categoryIds,
            CreateExperienceDto projectExperience, List<ExpRole> projectExpRoles) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.sourceCode = sourceCode;
        this.demo = demo;
        this.image = image;
        this.type = type;
        this.status = status;
        this.skillIds = skillIds;
        this.categoryIds = categoryIds;
        this.projectExperience = projectExperience;
        this.projectExpRoles = projectExpRoles;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public CreateExperienceDto getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(CreateExperienceDto projectExperience) {
        this.projectExperience = projectExperience;
    }

    public List<ExpRole> getProjectExpRoles() {
        return projectExpRoles;
    }

    public void setProjectExpRoles(List<ExpRole> projectExpRoles) {
        this.projectExpRoles = projectExpRoles;
    }
    
}
