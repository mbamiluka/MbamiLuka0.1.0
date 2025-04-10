package com.mbami.portfolio.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String institution;
    private Date startDate; // mmyyyy
    private Date endDate;
    private String logo; // e.g. url

    @ManyToOne
    @JsonBackReference
    private ExpType expType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achievementExperience")
    private List<Achievement> expAchievements;

    @OneToMany(mappedBy = "roleExperience", cascade = CascadeType.ALL)
    private Set<ExpRole> experienceRoles = new HashSet<>();

    public Experience() {
    }

    public Experience(String institution, Date startDate, Date endDate, String logo) {
        this.institution = institution;
        this.startDate = startDate;
        this.endDate = endDate;
        this.logo = logo;
    }

    public long getId() {
        return id;
    }

    public String getInstitution() {
        return institution;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Achievement> getExpAchievements() {
        return expAchievements;
    }

    public void setExpAchievements(List<Achievement> expAchievements) {
        this.expAchievements = expAchievements;
    }

    public void addExpAchievement(Achievement achievement) {
        if (expAchievements == null) {
            expAchievements = new ArrayList<>();
        }
        expAchievements.add(achievement);
    }

    public Set<ExpRole> getExpRoles() {
        return experienceRoles;
    }

    public void setExpRoles(Set<ExpRole> expRoles) {
        this.experienceRoles = expRoles;
    }

    public void addExpRole(ExpRole expRole) {
        experienceRoles.add(expRole);
    }

    public void removeExpRole(ExpRole expRole) {
        experienceRoles.remove(expRole);
    }

    public ExpType getExpType() {
        return expType;
    }

    public void setExpType(ExpType expType) {
        this.expType = expType;
    }
}
