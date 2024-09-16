package com.mbami.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbami.portfolio.model.ExpType;
import com.mbami.portfolio.repository.ExpTypeRepository;

@Service
public class ExpTypeService{
    @Autowired
    private ExpTypeRepository expTypeRepository;

    public ExpTypeService(ExpTypeRepository expTypeRepository) {
        this.expTypeRepository = expTypeRepository;
    }

    public List<ExpType> getAllExpTypes() {
        return expTypeRepository.findAll();
    }

    public Optional<ExpType> getExpTypeById(long id) {
        return expTypeRepository.findById(id);
    }

    public ExpType addExpType(ExpType expType) {
        return expTypeRepository.save(expType);
    }

    public void deleteExpType(long id) {
        expTypeRepository.deleteById(id);
    }

    public boolean isExpTypeExist(long id) {
        return expTypeRepository.existsById(id);
    }

    public ExpType updateExpType(long id, ExpType expType) {
        if (expTypeRepository.existsById(id)) {
            return expTypeRepository.save(expType);
        }
        return null;
    }
}