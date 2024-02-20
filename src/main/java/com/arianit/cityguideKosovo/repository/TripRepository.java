package com.arianit.cityguideKosovo.repository;

import com.arianit.cityguideKosovo.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}