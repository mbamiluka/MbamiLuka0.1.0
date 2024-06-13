package com.mbami.portfolio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbami.portfolio.repository.ExperienceRepository;
import com.mbami.portfolio.model.Achievement;
import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.repository.ExpRoleRepository;
import com.mbami.portfolio.model.Achievement;
import com.mbami.portfolio.repository.AchievementRepository;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExpRoleRepository expRoleRepository;
    private final AchievementRepository achievementRepository;

    public ExperienceService(ExperienceRepository experienceRepository, ExpRoleRepository expRoleRepository,
            AchievementRepository achievementRepository) {
        this.experienceRepository = experienceRepository;
        this.expRoleRepository = expRoleRepository;
        this.achievementRepository = achievementRepository;
    }

    public ExperienceRepository getExperienceRepository() {
        return experienceRepository;
    }

    public ExpRoleRepository getExpRoleRepository() {
        return expRoleRepository;
    }

    public void deleteExperience(long id) {
        experienceRepository.deleteById(id);
    }

    public void deleteExpRole(long experienceId, long expRoleId) {
        Experience experience = experienceRepository.findById(experienceId).orElse(null);
        if (experience != null) {
            ExpRole expRole = expRoleRepository.findById(expRoleId).orElse(null);
            if (expRole != null) {
                //experience.getExpRoles().remove(expRole);
                experienceRepository.save(experience);
            }
        }
    }

    public boolean isExperienceExist(long id) {
        return experienceRepository.existsById(id);
    }

    public boolean isExpRoleExist(long id) {
        return expRoleRepository.existsById(id);
    }

    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    public List<ExpRole> getAllExpRoles() {
        return expRoleRepository.findAll();
    }

    //get experience by id
    public Experience getExperience(long id) {
        return experienceRepository.findById(id).orElse(null);
    }

    //get expRole by id
    public ExpRole getExpRole(long id) {
        return expRoleRepository.findById(id).orElse(null);
    }

    //save experience
    public void saveExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    //save expRole
    public void saveExpRole(ExpRole expRole) {
        expRoleRepository.save(expRole);
    }

    //update experience
    public void updateExperience(long id, Experience experience) {
        Experience existingExperience = experienceRepository.findById(id).orElse(null);
        if (existingExperience != null) {
            existingExperience.setInstitution(experience.getInstitution());
            existingExperience.setStartDate(experience.getStartDate());
            existingExperience.setEndDate(experience.getEndDate());
            existingExperience.setLogo(experience.getLogo());
            //existingExperience.setStatus(experience.getStatus());
            experienceRepository.save(existingExperience);
        }
    }

    // create experience
    public Experience addExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    // create expRole
    public ExpRole addExpRole(ExpRole expRole) {
        return expRoleRepository.save(expRole);
    }

    @Transactional
    public Experience addAchievement(long experienceId, Achievement achievement) {
        Experience experience = experienceRepository.findById(experienceId).orElse(null);
        if (experience != null) {
            achievement.setAchievementExperience(experience);
            achievementRepository.save(achievement);
            List<Achievement> achievements = experience.getExpAchievements();
            if (achievements == null) {
                achievements = new ArrayList<>();
            }
            achievements.add(achievement);
            experience.setExpAchievements(achievements);
            experienceRepository.save(experience);
        }
        return experience;
    }

    public void deleteAchievement(long experienceId, long achievementId) {
        Experience experience = experienceRepository.findById(experienceId).orElse(null);
        if (experience != null) {
            List<Achievement> achievements = experience.getExpAchievements();
            if (achievements != null) {
                achievements.removeIf(achievement -> achievement.getId() == achievementId);
                experience.setExpAchievements(achievements);
                experienceRepository.save(experience);
            }
        }
    }

    public Experience updateAchievement(long experienceId, long achievementId, Achievement achievement) {
        /*
         * This method updates the achievement of a given experience
         */
        Experience experience = experienceRepository.findById(experienceId).orElse(null);
        if (experience != null) {
            List<Achievement> achievements = experience.getExpAchievements();
            if (achievements != null) {
                for (Achievement a : achievements) {
                    if (a.getId() == achievementId) {
                        a.setAchievementDescr(achievement.getAchievementDescr());
                        a.setAchievementFullDescr(achievement.getAchievementFullDescr());
                        a.setAchievementLogo(achievement.getAchievementLogo());
                        a.setAchievementTitle(achievement.getAchievementTitle());
                        a.setAchievementUrl(achievement.getAchievementUrl());
                    }
                }
                experience.setExpAchievements(achievements);
                experienceRepository.save(experience);
            }
        }
        return experience;
    }
    
    public List<Achievement> getAllAchievements(long experienceId) {
        // return all achievements of a given experience
        Experience experience = experienceRepository.findById(experienceId).orElse(null);
        if (experience != null) {
            return experience.getExpAchievements();
        }
        return null;
    }
}
