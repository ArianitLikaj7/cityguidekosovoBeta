package com.arianit.cityguideKosovo.service;
import com.arianit.cityguideKosovo.dto.GastronomeDto;
import com.arianit.cityguideKosovo.entity.City;
import com.arianit.cityguideKosovo.entity.Gastronome;
import com.arianit.cityguideKosovo.exception.ResourceNotFoundException;
import com.arianit.cityguideKosovo.mapper.GastronomeMapper;
import com.arianit.cityguideKosovo.repository.CityRepository;
import com.arianit.cityguideKosovo.repository.GastronomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class GastronomeService {

    private final GastronomeRepository gastronomeRepository;
    private final CityRepository cityRepository;
    private final GastronomeMapper gastronomeMapper;

    @Autowired
    public GastronomeService(GastronomeRepository gastronomeRepository,CityRepository cityRepository, GastronomeMapper gastronomeMapper) {
        this.gastronomeRepository = gastronomeRepository;
        this.cityRepository = cityRepository;
        this.gastronomeMapper = gastronomeMapper;
    }
    public GastronomeDto createGastronome(Long cityId, Gastronome gastronome) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + cityId));
        gastronome.setCity(city);

        Gastronome savedGastronome =  gastronomeRepository.save(gastronome);
        return gastronomeMapper.mapEntityToDto(savedGastronome);
    }
    public GastronomeDto getGastronomeById(Long cityId, Long gastronomeId){
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + cityId));

        Gastronome gastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new ResourceNotFoundException("Gastronome with this id dosent exist "+gastronomeId));

        if(!gastronome.getCity().getCityId().equals(city.getCityId())){
            throw new IllegalArgumentException("Gastronome doesn't belong to location");
        }
        return gastronomeMapper.mapEntityToDto(gastronome);
    }

    public List<GastronomeDto> getGastronomesByLocationId(Long cityId){
        List<Gastronome> gastronomes = gastronomeRepository.findByCityCityId(cityId);
        return gastronomes.stream()
                .map(gastronomeMapper::mapEntityToDto)
                .collect(toList());
    }


    public GastronomeDto updateGastronome(Long cityId, Long gastronomeId, Gastronome gastronome){
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + cityId));

        Gastronome existingGastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new ResourceNotFoundException("Gastronome with this id dosent exist "+gastronomeId));

        if(!existingGastronome.getCity().getCityId().equals(city.getCityId())){
            throw new IllegalArgumentException("Gastronome doesn't belong to location");
        }

        if (gastronome.getSchedule() != null){
            existingGastronome.setSchedule(gastronome.getSchedule());
        }
        if (gastronome.getNameOfGastronome() != null) {
            existingGastronome.setNameOfGastronome(gastronome.getNameOfGastronome());
        }

        if (gastronome.getLongitude() != null) {
            existingGastronome.setLongitude(gastronome.getLongitude());
        }

        if (gastronome.getLatitude() != null) {
            existingGastronome.setLatitude(gastronome.getLatitude());
        }


        existingGastronome = gastronomeRepository.save(existingGastronome);
        return gastronomeMapper.mapEntityToDto(existingGastronome);
    }

    public void deleteGastronomeById(Long cityId,Long gastronomeId){
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + cityId));

        Gastronome existingGastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new ResourceNotFoundException("Gastronome with this id dosent exist "+gastronomeId));

        if(!existingGastronome.getCity().getCityId().equals(city.getCityId())){
            throw new IllegalArgumentException("Gastronome doesn't belong to city");
        }
        gastronomeRepository.deleteById(gastronomeId);

    }


}