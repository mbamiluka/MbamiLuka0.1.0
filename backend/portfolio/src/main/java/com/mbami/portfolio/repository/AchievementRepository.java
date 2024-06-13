package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    //List<Achievement> findByCategoryId(long categoryId);
    //List<Achievement> findBySkillId(long skillId);
    //List<Achievement> findBySkillCategoryId(long skillCategoryId);
    //List<Achievement> findBySkillCategoryName(String skillCategoryName);
    //List<Achievement> findBySkillName(String skillName);
}
