package com.arianit.cityguideKosovo.service;
import com.arianit.cityguideKosovo.dto.CityDto;
import com.arianit.cityguideKosovo.entity.City;
import com.arianit.cityguideKosovo.entity.Gastronome;
import com.arianit.cityguideKosovo.entity.TypeOfGastronome;
import com.arianit.cityguideKosovo.exception.ResourceNotFoundException;
import com.arianit.cityguideKosovo.mapper.CityMapper;
import com.arianit.cityguideKosovo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;


    @Autowired
    public CityService(CityRepository cityRepository, CityMapper cityMapperMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapperMapper;
    }

    public List<CityDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::mapEntityToDto)
                .collect(toList());
    }

    public CityDto getCityById(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + cityId));
        return cityMapper.mapEntityToDto(city);
    }

    public CityDto saveCity(City city) {
        if (city.getCityId() != null && cityRepository.existsById(city.getCityId())) {
            throw new IllegalArgumentException("The city with this Id exists: " + city.getCityId());
        }
        City savedCity = cityRepository.save(city);
        return cityMapper.mapEntityToDto(savedCity);
    }

    public void deleteCity(Long cityId) {
        if (!cityRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("The city with this id not found " + cityId);
        }
        cityRepository.deleteById(cityId);
    }

    public CityDto getCityWithGastronomesByType(long cityId, TypeOfGastronome type) {
        City city = cityRepository.findById(cityId).orElse(null);
        if (city == null) {
            // handle case when city is not found
            return null;
        }
        List<Gastronome> gastronomes = city.getGastronomes().stream()
                .filter(g -> g.getTypeOfGastronome() == type)
                .collect(Collectors.toList());
        city.setGastronomes(gastronomes);
        return cityMapper.mapEntityToDto(city);
    }
    public CityDto updateCity(Long cityId, City city) {
        City existingCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with ID: " + cityId));

        if (city.getNameOfCity() != null && !city.getNameOfCity().isEmpty()) {
            existingCity.setNameOfCity(city.getNameOfCity());
        }
        if (city.getCulturalHeritage() != null && !city.getCulturalHeritage().isEmpty()) {
            existingCity.setCulturalHeritage(city.getCulturalHeritage());
        }
        existingCity = cityRepository.save(existingCity);

        return cityMapper.mapEntityToDto(existingCity);
    }
    public List<Long> getExistingCityIds(List<Long> cityIds) {
        return cityRepository.findExistingCityIds(cityIds);
    }
}