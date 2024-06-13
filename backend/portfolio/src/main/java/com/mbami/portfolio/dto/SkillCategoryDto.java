package com.mbami.portfolio.dto;

public class SkillCategoryDto {
    private Long id;
    private String name;

    public SkillCategoryDto() {
    }

    public SkillCategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SkillCategoryDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
