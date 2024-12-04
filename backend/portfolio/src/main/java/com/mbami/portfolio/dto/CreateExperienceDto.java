package com.mbami.portfolio.dto;

import java.util.Date;
import java.util.List;

public class CreateExperienceDto {
    private Long id;
    private String institution;
    private Date startDate;
    private Date endDate;
    private String logo;
    private String expType;
    private List<Long> achievementIds;
    private List<Long> expRoleIds;


    public CreateExperienceDto() {
    }

    public CreateExperienceDto(String institution, Date startDate, Date endDate, String logo, String expType, List<Long> achievementIds, List<Long> expRoleIds) {
        this.institution = institution;
        this.startDate = startDate;
        this.endDate = endDate;
        this.logo = logo;
        this.expType = expType;
        this.achievementIds = achievementIds;
        this.expRoleIds = expRoleIds;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public List<Long> getAchievementIds() {
        return achievementIds;
    }

    public void setAchievementIds(List<Long> achievementIds) {
        this.achievementIds = achievementIds;
    }

    public List<Long> getExpRoleIds() {
        return expRoleIds;
    }

    public void setExpRoleIds(List<Long> expRoleIds) {
        this.expRoleIds = expRoleIds;
    }

    @Override
    public String toString() {
        return "CreateExperienceDto{" +
                "institution='" + institution + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", logo='" + logo + '\'' +
                ", expType='" + expType + '\'' +
                ", achievementIds=" + achievementIds +
                ", expRoleIds=" + expRoleIds +
                '}';
    }

}
