package com.mbami.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.model.Achievement;
import com.mbami.portfolio.service.ExperienceService;


@RestController
@RequestMapping("api/v1/experience")
@CrossOrigin(origins = "http://localhost:3000")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<Experience> getExperiences(@RequestParam(required = false) String expType) {
        if (expType != null && !expType.isEmpty()) {
            return experienceService.getExperiencesByExpType(expType);
        } else {
            return experienceService.getAllExperiences();
        }
    }

    @PostMapping
    public Experience addExperience(@RequestBody Experience experience) {
        return experienceService.addExperience(experience);
    }

    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable long id) {
        experienceService.deleteExperience(id);
    }

    @PutMapping("/{id}")
    public Experience updateExperience(@PathVariable int id, @RequestBody Experience experience) {
        return experienceService.addExperience(experience);
    }

    @GetMapping("/{id}")
    public boolean isExperienceExist(@RequestBody long id) {
        return experienceService.isExperienceExist(id);
    }
    // --------------- ExpRole -------------------

    @GetMapping("/role/{id}")
    public ExpRole getExpRole(@RequestBody long id) {
        return experienceService.getExpRole(id);
    }

    @GetMapping("/role")
    public List<ExpRole> getAllExpRoles() {
        return experienceService.getAllExpRoles();
    }

    @PostMapping("/role")
    public ExpRole addExpRole(@RequestBody ExpRole expRole) {
        return experienceService.addExpRole(expRole);
    }
    
    @DeleteMapping("/{experienceId}/role/{roleId}")
    public ResponseEntity<Void> deleteExpRole(@PathVariable long experienceId, @PathVariable long roleId) {
        experienceService.deleteExpRole(experienceId, roleId);
        return ResponseEntity.ok().build();
    }

    // ------------ Experience Achievement ---------------------

    @PostMapping("/{experienceId}/achievement")
    public ResponseEntity<Experience> addAchievement(@PathVariable long experienceId, @RequestBody Achievement achievement) {
        return ResponseEntity.ok(experienceService.addAchievement(experienceId, achievement));
    }

    @DeleteMapping("/{experienceId}/achievement/{achievementId}")
    public ResponseEntity<?> deleteAchievement(@PathVariable long experienceId, @PathVariable long achievementId) {
        experienceService.deleteAchievement(experienceId, achievementId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{experienceId}/achievement/{achievementId}")
    public ResponseEntity<Experience> updateAchievement(@PathVariable long experienceId, @PathVariable long achievementId, @RequestBody Achievement achievement) {
        return ResponseEntity.ok(experienceService.updateAchievement(experienceId, achievementId, achievement));
    }

    // get all achievements
    @GetMapping("/{experienceId}/achievement")
    public ResponseEntity<List<Achievement>> getAllAchievements(@PathVariable long experienceId) {
        return ResponseEntity.ok(experienceService.getAllAchievements(experienceId));
    }
}
