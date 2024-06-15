package com.mbami.portfolio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.Achievement;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.repository.ExpRoleRepository;
import com.mbami.portfolio.repository.AchievementRepository;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final ExpRoleRepository expRoleRepository;
    
    @Autowired
    public AchievementService(AchievementRepository achievementRepository,
            ExpRoleRepository expRoleRepository) {
        this.achievementRepository = achievementRepository;
        this.expRoleRepository = expRoleRepository;
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement addAchievement(Achievement achievement) {
        //if the achievement has an experience, add the achievement to the experience
        if (achievement.getAchievementExperience() != null) {
            Experience experience = achievement.getAchievementExperience();
            if (experience.getExpAchievements() == null) {
                experience.setExpAchievements(new ArrayList<>());
            }
            experience.getExpAchievements().add(achievement);
        }
    
        // Save the achievement
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(long id) {
        achievementRepository.deleteById(id);
    }

    // given a path variable, this method updates the achievement with the given id
    public Achievement updateAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public Achievement getAchievementById(long id) {
        return achievementRepository.findById(id).orElse(null);
    }

    public boolean isAchievementExist(long id) {
        return achievementRepository.existsById(id);
    }

    /* public List<Achievement> getAchievementsByCategoryId(long categoryId) {
        return achievementRepository.findByCategoryId(categoryId);
    }

    public List<Achievement> getAchievementsBySkillId(long skillId) {
        return achievementRepository.findBySkillId(skillId);
    }

    public List<Achievement> getAchievementsBySkillCategoryId(long skillCategoryId) {
        return achievementRepository.findBySkillCategoryId(skillCategoryId);
    }

    public List<Achievement> getAchievementsBySkillCategoryName(String skillCategoryName) {
        return achievementRepository.findBySkillCategoryName(skillCategoryName);
    }

    public List<Achievement> getAchievementsBySkillName(String skillName) {
        return achievementRepository.findBySkillName(skillName);
    } */
   
    public List<Achievement> getAchievementsByExpRole(long expRoleId) {
        return expRoleRepository.findById(expRoleId).map(ExpRole::getExpRoleAchievements).orElse(null);
    }

    public List<Achievement> getAchievementsByExpRoleName(String expRoleName) {
        return expRoleRepository.findByName(expRoleName).getExpRoleAchievements();
    }

    public List<Achievement> addAchievementsToExpRole(long expRoleId, List<Achievement> achievements) {
        ExpRole expRole = expRoleRepository.findById(expRoleId).orElse(null);
        if (expRole == null) {
            return null;
        }
        for (Achievement achievement : achievements) {
            achievement.setAchievementExpRole(expRole);
        }

        return achievementRepository.saveAll(achievements);
    }

}
