package com.arianit.cityguideKosovo.repository;

import com.arianit.cityguideKosovo.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastronomeRepository extends JpaRepository<Gastronome, Long> {
    List<Gastronome> findByCityCityId(Long locationId);
}