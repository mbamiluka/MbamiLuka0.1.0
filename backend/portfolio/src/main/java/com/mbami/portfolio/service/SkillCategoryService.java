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
}