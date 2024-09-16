package com.mbami.portfolio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbami.portfolio.model.ExpType;
import com.mbami.portfolio.service.ExpTypeService;

@RestController
@RequestMapping("api/v1/expType")
public class ExpTypeController {
    private final ExpTypeService expTypeService;

    public ExpTypeController(ExpTypeService expTypeService) {
        this.expTypeService = expTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ExpType>> getAllExpTypes() {
        List<ExpType> expTypes = expTypeService.getAllExpTypes();
        return new ResponseEntity<>(expTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpType> getExpTypeById(@PathVariable("id") Long id) {
        Optional<ExpType> expType = expTypeService.getExpTypeById(id);
        if (expType.isPresent()) {
            return new ResponseEntity<>(expType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ExpType> addExpType(@RequestBody ExpType expType) {
        ExpType newExpType = expTypeService.addExpType(expType);
        return new ResponseEntity<>(newExpType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpType> updateExpType(@PathVariable("id") Long id, @RequestBody ExpType expType) {
        ExpType updatedExpType = expTypeService.updateExpType(id, expType);
        if (updatedExpType != null) {
            return new ResponseEntity<>(updatedExpType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpType(@PathVariable("id") Long id) {
        if (expTypeService.isExpTypeExist(id)) {
            expTypeService.deleteExpType(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
