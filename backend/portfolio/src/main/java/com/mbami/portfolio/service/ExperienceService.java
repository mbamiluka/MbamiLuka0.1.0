package com.mbami.portfolio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbami.portfolio.repository.ExperienceRepository;

import jakarta.persistence.EntityNotFoundException;

import com.mbami.portfolio.model.Achievement;
import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.model.ExpType;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.repository.ExpRoleRepository;
import com.mbami.portfolio.repository.ExpTypeRepository;
import com.mbami.portfolio.repository.AchievementRepository;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExpRoleRepository expRoleRepository;
    private final AchievementRepository achievementRepository;
    private final ExpTypeRepository expTypeRepository;

    public ExperienceService(ExperienceRepository experienceRepository, ExpRoleRepository expRoleRepository,
            AchievementRepository achievementRepository, ExpTypeRepository expTypeRepository) {
        this.experienceRepository = experienceRepository;
        this.expRoleRepository = expRoleRepository;
        this.achievementRepository = achievementRepository;
        this.expTypeRepository = expTypeRepository;
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
                experience.getExpRoles().remove(expRole);
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

    public List<Experience> getExperiencesByExpType(String type) {
        ExpType expType = expTypeRepository.findByName(type);
        if (expType != null) {
            return experienceRepository.findByExpType(expType);
        }

        System.out.println("ExpType not found: " + type);
        return new ArrayList<>();
    }

    @Transactional
    public List<ExpRole> getAllExpRoles() {
        return expRoleRepository.findAll();
    }

    //get experience by id
    @Transactional
    public Experience getExperience(long id) {
        return experienceRepository.findById(id).orElse(null);
    }

    //get expRole by id
    @Transactional
    public ExpRole getExpRole(long id) {
        return expRoleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveExperience(Experience experience) {
        if(experience.getInstitution() == null) {
            throw new IllegalArgumentException("Experience institution cannot be null");
        }
        
        if(experienceRepository.findByInstitution(experience.getInstitution()) != null) {
            throw new IllegalArgumentException("Experience with institution " + experience.getInstitution() + " already exists");
        }

        if(experience.getExpType() == null) {
            throw new IllegalArgumentException("Experience must have a type. E.g. education, work, etc.");
        }

        experienceRepository.save(experience);
    }

    @Transactional
    public void saveExpTypeWithExperiences(ExpType expType) {
        for (Experience experience : expType.getExperiences()) {
            experience.setExpType(expType);
        }
        expTypeRepository.save(expType);
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
        ExpType expType = expTypeRepository.findById(experience.getExpType().getId())
                .orElseThrow(() -> new EntityNotFoundException("ExpType not found with id: " + experience.getExpType().getId()));
        experience.setExpType(expType);

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
