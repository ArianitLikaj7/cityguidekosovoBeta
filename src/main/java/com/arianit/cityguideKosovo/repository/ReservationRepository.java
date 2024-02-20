package com.arianit.cityguideKosovo.repository;

import com.arianit.cityguideKosovo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGastronomeGastronomeId(Long gastronomeId);
}