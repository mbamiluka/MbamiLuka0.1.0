package com.mbami.portfolio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.repository.ExpRoleRepository;
import com.mbami.portfolio.repository.ExperienceRepository;

@Service
public class ExpRoleService {
    private final ExpRoleRepository expRoleRepository;
    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExpRoleService(ExpRoleRepository expRoleRepository, ExperienceRepository experienceRepository) {
        this.expRoleRepository = expRoleRepository;
        this.experienceRepository = experienceRepository;
    }

    public ExpRoleRepository getExpRoleRepository() {
        return expRoleRepository;
    }

    public void deleteExpRole(long id) {
        expRoleRepository.deleteById(id);
    }

    public boolean isExpRoleExist(long id) {
        return expRoleRepository.existsById(id);
    }
    
    public ExpRole addExpRole(ExpRole expRole) {
        List<ExpRole> existingExpRoles = expRoleRepository.findByName(expRole.getName().toLowerCase());

        if (existingExpRoles.size() > 0) {
            throw new IllegalArgumentException("Role already exists");
        }
        for(ExpRole existingExpRole : existingExpRoles) {
            if (existingExpRole.getRoleExperience().getId() == expRole.getRoleExperience().getId()) {
                throw new IllegalArgumentException("Role already exists");
            }
        }
        expRole.setName(expRole.getName().trim());
        return expRoleRepository.save(expRole);
    }

    public ExpRole getExpRoleById(long id) {
        return expRoleRepository.findById(id).orElse(null);
    }

    public List<ExpRole> getAllExpRoles() {
        return expRoleRepository.findAll();
    }

    public ExpRole updateExpRole(ExpRole expRole) {
        return expRoleRepository.save(expRole);
    }

    public List<ExpRole> getExpRoleByName(String name) {
        return expRoleRepository.findByName(name);
    }

}
