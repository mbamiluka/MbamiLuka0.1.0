package com.mbami.portfolio.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;
import com.mbami.portfolio.model.User;
import com.mbami.portfolio.security.jwt.JwtUtil;
import com.mbami.portfolio.service.SkillCategoryService;
import com.mbami.portfolio.service.SkillService;
import com.mbami.portfolio.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@CrossOrigin(origins = "https://mbamiluka-65b99.web.app/")
public class SkillController {

    private final SkillService skillService;
    private final UserService userService;
    private final SkillCategoryService skillCategoryService;
    private final JwtUtil jwtUtil;

    @Autowired
    public SkillController(SkillService skillService,
            UserService userService, SkillCategoryService skillCategory, 
            JwtUtil jwtUtil) {
        this.skillService = skillService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.skillCategoryService = skillCategory;
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        return new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.getSkillById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Skill> addSkill(@RequestHeader("Authorization") String tokn, 
            @RequestBody Skill skill) {
        return new ResponseEntity<>(skillService.addSkill(tokn, skill), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
        return new ResponseEntity<>(skillService.updateSkill(skill), HttpStatus.OK);
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<Set<SkillCategory>> getSkillCategories(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.getSkillCategories(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<Skill> addSkillCategory(@RequestHeader("Authorization") String token, Long id, Long skillCategoryId) {
        String username = jwtUtil.extractUsername(token);
        return new ResponseEntity<Skill>(skillService.addSkillCategory(username, id, skillCategoryId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/categories")
    public ResponseEntity<Skill> setSkillCategories(@PathVariable Long id, @RequestBody Set<SkillCategory> skillCategories) {
        return new ResponseEntity<>(skillService.setSkillCategories(id, skillCategories), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/categories/{categoryId}")
    public ResponseEntity<?> deleteSkillCategory(@PathVariable Long id, @PathVariable Long categoryId) {
        skillService.deleteSkillCategory(id, categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test", HttpStatus.OK);
    }
}