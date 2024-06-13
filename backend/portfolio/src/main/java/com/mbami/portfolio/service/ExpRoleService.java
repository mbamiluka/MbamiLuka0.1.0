package com.mbami.portfolio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.repository.ExpRoleRepository;

@Service
public class ExpRoleService {
    private final ExpRoleRepository expRoleRepository;

    @Autowired
    public ExpRoleService(ExpRoleRepository expRoleRepository) {
        this.expRoleRepository = expRoleRepository;
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

    public ExpRole getExpRoleByName(String name) {
        return expRoleRepository.findByName(name);
    }

}
