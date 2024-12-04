package com.mbami.portfolio.dto;

public class CreateContentDto {
    private Long id;
    private String name;
    private String contentType;
    private String content;
    private Long projectId;

    public CreateContentDto() {
    }

    public CreateContentDto(String contentType, String content, 
            Long contentProjectId, String name) {
        this.contentType = contentType;
        this.content = content;
        this.projectId = contentProjectId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long contentProjectId) {
        this.projectId = contentProjectId;
    }
}
