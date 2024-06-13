package com.mbami.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

    Optional<SkillCategory> findByIdAndName(Long id, String name);

    Optional<SkillCategory> findByName(String name);

    Optional<SkillCategory> findById(Long id);

    Optional<SkillCategory> findByNameAndCategorySkills(String name, Skill skill);}
