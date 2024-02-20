package com.arianit.cityguideKosovo.repository;
import com.arianit.cityguideKosovo.entity.City;
import com.arianit.cityguideKosovo.entity.TypeOfGastronome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c.cityId FROM City c WHERE c.cityId IN :cityIds")
    List<Long> findExistingCityIds(@Param("cityIds") List<Long> cityIds);
    City findByCityIdAndGastronomesTypeOfGastronome(Long cityId, TypeOfGastronome type);

}