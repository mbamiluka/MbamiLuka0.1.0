package com.mbami.portfolio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ExpRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date startDate; // mmyyyy
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "experience_id") // This column is in the exp_role table.
    private Experience roleExperience;

    @OneToMany(mappedBy = "achievementExpRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Achievement> expRoleAchievements = new ArrayList<>();

    @ManyToMany
    List<Project> expRoleProjects = new ArrayList<>();

    public ExpRole() {
    }

    public ExpRole(String name, Date startDate, Date endDate) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Experience getRoleExperience() {
        return roleExperience;
    }

    public void setRoleExperience(Experience roleExperience) {
        this.roleExperience = roleExperience;
    }

    public List<Achievement> getExpRoleAchievements() {
        return expRoleAchievements;
    }

    public void setExpRoleAchievements(List<Achievement> expRoleAchievements) {
        this.expRoleAchievements = expRoleAchievements;
    }
}
