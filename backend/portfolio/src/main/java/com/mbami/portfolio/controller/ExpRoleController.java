package com.mbami.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbami.portfolio.model.ExpRole;
import com.mbami.portfolio.service.ExpRoleService;

@RestController
@RequestMapping("api/v1/expRole")
public class ExpRoleController {
    private final ExpRoleService expRoleService;

    public ExpRoleController(ExpRoleService expRoleService) {
        this.expRoleService = expRoleService;
    }

    @GetMapping
    public List<ExpRole> getAllExpRoles() {
        return expRoleService.getAllExpRoles();
    }

    @GetMapping("/{id}")
    public ExpRole getExpRole(long id) {
        return expRoleService.getExpRoleById(id);
    }

    @GetMapping("/name/{name}")
    public ExpRole getExpRoleByName(String name) {
        return expRoleService.getExpRoleByName(name);
    }

    @GetMapping("/exist/{id}")
    public boolean isExpRoleExist(long id) {
        return expRoleService.isExpRoleExist(id);
    }

    @DeleteMapping("{id}")
    public void deleteExpRole(long id) {
        expRoleService.deleteExpRole(id);
    }

    @PostMapping
    public ExpRole addExpRole(@RequestBody ExpRole expRole) {
        return expRoleService.addExpRole(expRole);
    }

    @PutMapping("/{id}")
    public ExpRole updateExpRole(long id, @RequestBody ExpRole expRole) {
        return expRoleService.updateExpRole(expRole);
    }
}
