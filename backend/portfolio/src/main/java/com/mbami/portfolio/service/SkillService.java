package com.mbami.portfolio.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;
import com.mbami.portfolio.model.User;
import com.mbami.portfolio.repository.SkillCategoryRepository;
import com.mbami.portfolio.repository.SkillRepository;
import com.mbami.portfolio.repository.UserRepository;
import com.mbami.portfolio.security.jwt.JwtUtil;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final JwtUtil jwtUtil;
    private UserRepository userRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.jwtUtil = new JwtUtil();
        this.userRepository = userRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public Skill addSkill(String token, Skill skill) {
        String username = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found/authorized");
        }

        // Check if the skill has a name
        if (skill.getName() == null || skill.getName().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty");
        }
    
        // Check if the skill already exists
        if (skillRepository.findByName(skill.getName()).isPresent()) {
            throw new IllegalArgumentException("Skill with name " + skill.getName() + " already exists");
        }
    
        Set<SkillCategory> validatedCategories = new HashSet<>();
        for (SkillCategory category : skill.getSkillCategories()) {
    
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

    public Skill addSkillCategory(String username, Long skillId, long categoryId) {
        /**
         * This method is used to add a SkillCategory to a Skill
         * 
         * @param username  The username of the user making the request
         * @param skillId   The ID of the Skill to add the SkillCategory to
         * @param categoryId The ID of the SkillCategory to add to the Skill
         * @return          A ResponseEntity with an OK status
         */
        SkillCategory skillCategory = skillCategoryRepository.findById(categoryId)
            .orElseThrow();
        
        Optional<Skill> skill = skillRepository.findById(skillId);

        skillCategory.getSkills().add(skill.get());
        skill.get().getSkillCategories().add(skillCategory);

        skillCategoryRepository.save(skillCategory);
        skillRepository.save(skill.get());

        return skill.get();
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

    public Object getSkillByName(String name) {
        return skillRepository.findByName(name);
    }
}
