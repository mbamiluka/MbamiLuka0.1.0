package com.mbami.portfolio.service;

import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;
import com.mbami.portfolio.repository.SkillCategoryRepository;
import com.mbami.portfolio.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillCategoryService {
    private final SkillCategoryRepository skillCategoryRepository;
    private SkillRepository skillRepository;

    @Autowired
    public SkillCategoryService(SkillCategoryRepository skillCategoryRepository, SkillRepository skillRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillRepository = skillRepository;
    }

    public List<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAll();
    }

    public Optional<SkillCategory> getSkillCategoryById(Long id) {
        return skillCategoryRepository.findById(id);
    }

    public SkillCategory addSkillCategory(SkillCategory skillCategory) {
        return skillCategoryRepository.save(skillCategory);
    }

    public List<SkillCategory> addSkillCategories(List<SkillCategory> skillCategories) {
        return skillCategoryRepository.saveAll(skillCategories);
    }

    public SkillCategory updateSkillCategory(SkillCategory skillCategory) {
        return skillCategoryRepository.save(skillCategory);
    }

    public void deleteSkillCategory(Long id) {
        SkillCategory skillCategory = skillCategoryRepository.findById(id)
            .orElseThrow();

        // Remove the association between the SkillCategory and its Skills
        for (Skill skill : skillCategory.getSkills()) {
            skill.getSkillCategories().remove(skillCategory);
            skillRepository.save(skill);  // Assuming you have a SkillRepository
        }

        skillCategoryRepository.deleteById(id);
    }

    public SkillCategory addSkillToCategory(Long categoryId, Long skillId) {
        SkillCategory skillCategory = skillCategoryRepository.findById(categoryId)
            .orElseThrow();

        Skill skill = skillRepository.findById(skillId)
            .orElseThrow();

        skillCategory.getSkills().add(skill);
        skill.getSkillCategories().add(skillCategory);

        skillCategoryRepository.save(skillCategory);
        skillRepository.save(skill);

        return skillCategory;
    }

    public SkillCategory removeSkillFromCategory(Long categoryId, Long skillId) {
        SkillCategory skillCategory = skillCategoryRepository.findById(categoryId)
            .orElseThrow();

        Skill skill = skillRepository.findById(skillId)
            .orElseThrow();

        skillCategory.getSkills().remove(skill);
        skill.getSkillCategories().remove(skillCategory);

        skillCategoryRepository.save(skillCategory);
        skillRepository.save(skill);

        return skillCategory;
    }

    public List<Skill> addSkillsToCategory(Long categoryId, List<Long> skillIds) {
        SkillCategory skillCategory = skillCategoryRepository.findById(categoryId)
            .orElseThrow();

        List<Skill> skills = skillRepository.findAllById(skillIds);

        for (Skill skill : skills) {
            skillCategory.getSkills().add(skill);
            skill.getSkillCategories().add(skillCategory);
        }

        skillCategoryRepository.save(skillCategory);
        skillRepository.saveAll(skills);

        return skills;
    }
}