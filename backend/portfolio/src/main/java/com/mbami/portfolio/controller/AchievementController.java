package com.mbami.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbami.portfolio.model.Achievement;
import com.mbami.portfolio.service.AchievementService;
import com.mbami.portfolio.service.SkillService;

@RestController
@RequestMapping("api/v1/achievement")
public class AchievementController {
    private final AchievementService achievementService;
    private final SkillService skillService;

    public AchievementController(AchievementService achievementService, SkillService skillService) {
        this.achievementService = achievementService;
        this.skillService = skillService;
    }

    @GetMapping
    public List<Achievement> getAllAchievements() {
        return achievementService.getAllAchievements();
    }

    @PostMapping
    public ResponseEntity<Achievement> addAchievement(@RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.addAchievement(achievement));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable long id, @RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.updateAchievement(achievement));
    }

    @DeleteMapping("/{id}")
    public void deleteAchievement(@PathVariable long id) {
        achievementService.deleteAchievement(id);
    }

    @GetMapping("/{id}")
    public Achievement getAchievementById(@PathVariable long id) {
        return achievementService.getAchievementById(id);
    }

    /* @GetMapping("/category/{id}")
    public List<Achievement> getAchievementsByCategoryId(@PathVariable long id) {
        return achievementService.getAchievementsByCategoryId(id);
    }

    @GetMapping("/skill/{id}")
    public List<Achievement> getAchievementsBySkillId(@PathVariable long id) {
        return achievementService.getAchievementsBySkillId(id);
    }

    @GetMapping("/skillCategory/{id}")
    public List<Achievement> getAchievementsBySkillCategoryId(@PathVariable long id) {
        return achievementService.getAchievementsBySkillCategoryId(id);
    }

    @GetMapping("/skillCategoryName/{name}")
    public List<Achievement> getAchievementsBySkillCategoryName(@PathVariable String name) {
        return achievementService.getAchievementsBySkillCategoryName(name);
    }

    @GetMapping("/skillName/{name}")
    public List<Achievement> getAchievementsBySkillName(@PathVariable String name) {
        return achievementService.getAchievementsBySkillName(name);
    } */

}
