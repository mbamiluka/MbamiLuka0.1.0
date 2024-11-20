package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbami.portfolio.model.ExpRole;

public interface ExpRoleRepository extends JpaRepository<ExpRole, Long> {

    List<ExpRole> findByName(String name);
}