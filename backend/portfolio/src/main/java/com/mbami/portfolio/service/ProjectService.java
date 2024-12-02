package com.mbami.portfolio.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.checkerframework.checker.units.qual.s;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mbami.portfolio.dto.CreateProjectDto;
import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.model.Experience;
import com.mbami.portfolio.model.Project;
import com.mbami.portfolio.model.Content;
import com.mbami.portfolio.model.Skill;
import com.mbami.portfolio.model.SkillCategory;
import com.mbami.portfolio.model.User;
import com.mbami.portfolio.repository.ExpRoleRepository;
import com.mbami.portfolio.repository.ExperienceRepository;
import com.mbami.portfolio.repository.ContentRepository;
import com.mbami.portfolio.repository.ProjectRepository;
import com.mbami.portfolio.repository.SkillCategoryRepository;
import com.mbami.portfolio.repository.SkillRepository;
import com.mbami.portfolio.repository.UserRepository;
import com.mbami.portfolio.security.jwt.JwtUtil;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ContentRepository projectContentRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillRepository skillRepository;
    private final ExperienceRepository experienceRepository;
    private final ExpRoleRepository expRoleRepository;

    public ProjectService(ProjectRepository projectRepository,
            ContentRepository projectContentRepository,
            JwtUtil jwtUtil, UserRepository userRepository,
            SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository,
            ExperienceRepository experienceRepository, ExpRoleRepository expRoleRepository) {
        this.projectRepository = projectRepository;
        this.projectContentRepository = projectContentRepository;
        this.jwtUtil = new JwtUtil();
        this.userRepository = userRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillRepository = skillRepository;
        this.experienceRepository = experienceRepository;
        this.expRoleRepository = expRoleRepository;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public Project addProject(String token, CreateProjectDto project) {
        token = token.substring(7);
        String username = jwtUtil.extractUsername(token);
        User user = userRepository.findByUsername(username).orElse(null);

        List<SkillCategory> validCategories = new ArrayList<>();
        List<Skill> validSkills = new ArrayList<>();
        Experience validExperience = experienceRepository.findByInstitution(project.getProjectExperience().getInstitution());
        List<ExpRole> validExpRoles = new ArrayList<>();

        if (user == null) {
            throw new IllegalArgumentException("User not found/authorized");
        }

        // Check if the categories are already in the database
        for (Long categoryId : project.getCategoryIds()) {
            SkillCategory existingCategory = skillCategoryRepository.findById(categoryId).orElse(null);
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category not found");
            }
            validCategories.add(existingCategory);
        }

        // check if the skills are already in the database
        for (Long skillId : project.getSkillIds()) {
            Skill existingSkill = skillRepository.findById(skillId).orElse(null);
            if (existingSkill == null) {
                throw new IllegalArgumentException("Skill not found");
            }
            validSkills.add(existingSkill);
        }

        if (validExperience == null) {
            throw new IllegalArgumentException("Experience not found");
        }

        for (ExpRole expRole : project.getProjectExpRoles()) {
            ExpRole existingExpRole = expRoleRepository.findById(expRole.getId()).orElse(null);
            if (existingExpRole == null) {
                throw new IllegalArgumentException("Experience role not found");
            }
            validExpRoles.add(existingExpRole);
        }

        Project newProject = new Project();
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setStart(project.getStart());
        newProject.setEnd(project.getEnd());
        newProject.setSourceCode(project.getSourceCode());
        newProject.setDemo(project.getDemo());
        newProject.setImage(project.getImage());
        newProject.setType(project.getType());
        newProject.setProjectCategories(validCategories);
        newProject.setProjectSkills(validSkills);
        newProject.setStatus(project.getStatus());
        newProject.setProjectExperience(validExperience);
        newProject.setProjectExpRoles(validExpRoles);

        return projectRepository.save(newProject);
    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    public Project getProject(long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void updateProject(long id, Project project) {
        Project existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStart(project.getStart());
            existingProject.setEnd(project.getEnd());
            existingProject.setSourceCode(project.getSourceCode());
            existingProject.setDemo(project.getDemo());
            existingProject.setImage(project.getImage());
            existingProject.setType(project.getType());
            existingProject.setProjectCategories(project.getProjectCategories());
            existingProject.setStatus(project.getStatus());
            projectRepository.save(existingProject);
        }
    }

    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }

    public ResponseEntity<Content> addContent(long projectId, Content projectContent) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            projectContent.setContentProject(project);
            projectContentRepository.save(projectContent);
            return ResponseEntity.ok(projectContent);
        }
        return ResponseEntity.notFound().build();
    }

    public Content getContent(long projectId, long contentId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            Content projectContent = projectContentRepository.findById(contentId).orElse(null);
            if (projectContent != null) {
                return projectContent;
            }
        }
        return null;
    }

    public List<Content> getAllContentsByProjectId(Long projectId) {
        return projectContentRepository.findByContentProjectId(projectId);
    }
}
