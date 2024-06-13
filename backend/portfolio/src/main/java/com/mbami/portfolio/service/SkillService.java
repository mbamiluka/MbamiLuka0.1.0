package com.mbami.portfolio.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;
import com.mbami.portfolio.repository.SkillCategoryRepository;
import com.mbami.portfolio.repository.SkillRepository;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository) {
        this.skillRepository = skillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public Skill addSkill(Skill skill) {
        // Check if the skill has a name
        if (skill.getName() == null || skill.getName().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty");
        }
    
        // Check if the skill already exists
        if (skillRepository.findByName(skill.getName()).isPresent()) {
            throw new IllegalArgumentException("Skill with name " + skill.getName() + " already exists");
        }
    
        // Check if the SkillCategories exist or create new ones
        Set<SkillCategory> validatedCategories = new HashSet<>();
        for (SkillCategory category : skill.getSkillCategories()) {
            if (category.getName() == null || category.getName().isEmpty()) {
                throw new IllegalArgumentException("SkillCategory name cannot be null or empty");
            }
    
            SkillCategory existingCategory = skillCategoryRepository.findByIdAndName(category.getId(), category.getName())
                    .orElseGet(() -> skillCategoryRepository.save(category));
            validatedCategories.add(existingCategory);
        }
    
        // Set the validated categories and save the skill
        skill.setSkillCategories(validatedCategories);
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public Set<SkillCategory> getSkillCategories(Long id) {
        return skillRepository.findById(id).orElse(null).getSkillCategories();
    }

    public Skill addSkillCategory(Long id, SkillCategory skillCategory) {
        Skill skill = skillRepository.findById(id).orElse(null);
        skill.getSkillCategories().add(skillCategory);
        skillRepository.save(skill);
        return skill;
    }

    public Skill setSkillCategories(Long id, Set<SkillCategory> skillCategories) {
        Skill skill = skillRepository.findById(id).orElse(null);
        skill.setSkillCategories(skillCategories);
        skillRepository.save(skill);
        return skill;
    }

    public void deleteSkillCategory(Long id, Long categoryId) {
        Skill skill = skillRepository.findById(id).orElse(null);
        skill.getSkillCategories().remove(skillCategoryRepository.findById(categoryId).orElse(null));
        skillRepository.save(skill);
    }
}
