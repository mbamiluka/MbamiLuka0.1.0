package com.mbami.portfolio.model;

import java.util.Date;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String achievementDescr;
    @Column(length = 500)
    private String achievementFullDescr;
    private String achievementLogo; // e.g. url
    private String achievementTitle;
    private String achievementUrl; // e.g. url
    private Date startDate;
    private Date endDate;
    private String achievementType; // e.g. certification, award, degree, etc.

    @ManyToOne
    private Experience achievementExperience;

    @ManyToOne
    private ExpRole achievementExpRole;

    public Achievement() {
    }

    public Achievement(Date startDate, Date endDate, String achievementDescr, String achievementFullDescr, String achievementLogo, String achievementTitle, String achievementUrl) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.achievementDescr = achievementDescr;
        this.achievementFullDescr = achievementFullDescr;
        this.achievementLogo = achievementLogo;
        this.achievementTitle = achievementTitle;
        this.achievementUrl = achievementUrl;
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getAchievementDescr() {
        return achievementDescr;
    }

    public String getAchievementFullDescr() {
        return achievementFullDescr;
    }

    public String getAchievementLogo() {
        return achievementLogo;
    }

    public String getAchievementTitle() {
        return achievementTitle;
    }

    public String getAchievementUrl() {
        return achievementUrl;
    }

    public Experience getAchievementExperience() {
        return achievementExperience;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAchievementDescr(String achievementDescr) {
        this.achievementDescr = achievementDescr;
    }

    public void setAchievementFullDescr(String achievementFullDescr) {
        this.achievementFullDescr = achievementFullDescr;
    }

    public void setAchievementLogo(String achievementLogo) {
        this.achievementLogo = achievementLogo;
    }

    public void setAchievementTitle(String achievementTitle) {
        this.achievementTitle = achievementTitle;
    }

    public void setAchievementUrl(String achievementUrl) {
        this.achievementUrl = achievementUrl;
    }

    public void setAchievementExperience(Experience achievementExperience) {
        this.achievementExperience = achievementExperience;
    }

    public ExpRole getAchievementExpRole() {
        return achievementExpRole;
    }

    public void setAchievementExpRole(ExpRole achievementExpRole) {
        this.achievementExpRole = achievementExpRole;
    }

}