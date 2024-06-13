package com.mbami.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbami.portfolio.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}

