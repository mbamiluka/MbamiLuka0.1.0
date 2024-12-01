package com.mbami.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mbami.portfolio.model.SkillCategory;
import com.mbami.portfolio.security.jwt.JwtUtil;
import com.mbami.portfolio.service.SkillCategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/skillCategory")
@CrossOrigin(origins = "https://mbamiluka-65b99.web.app/")
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    @Autowired
    public SkillCategoryController(SkillCategoryService skillCategoryService) {
        this.skillCategoryService = skillCategoryService;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<HashMap<String, SkillCategory>> getAllSkillCategories() {
        return new ResponseEntity<>(skillCategoryService.getAllSkillCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillCategory> getSkillCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(skillCategoryService.getSkillCategoryById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SkillCategory> addSkillCategory(@RequestBody SkillCategory skillCategory) {
        return new ResponseEntity<>(skillCategoryService.addSkillCategory(skillCategory), HttpStatus.CREATED);
    }

    // add a list of SkillCategories
    @PostMapping("/batch")
    public ResponseEntity<List<SkillCategory>> addSkillCategories(@RequestBody List<SkillCategory> skillCategories) {
        return new ResponseEntity<>(skillCategoryService.addSkillCategories(skillCategories), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkillCategory(@PathVariable Long id) {
        skillCategoryService.deleteSkillCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SkillCategory> updateSkillCategory(@RequestBody SkillCategory skillCategory) {
        return new ResponseEntity<>(skillCategoryService.updateSkillCategory(skillCategory), HttpStatus.OK);
    }

    /* public ResponseEntity<?> addCategoryToSkill(@RequestHeader("Authorization") String token, Long skillId, Long categoryId) {
        String username = jwtUtil.extractUsername(token);
        return new ResponseEntity<>(skillCategoryService.addSkillCategory(username, skillId, categoryId), HttpStatus.OK);
    } */

}