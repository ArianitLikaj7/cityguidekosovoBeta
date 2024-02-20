package com.arianit.cityguideKosovo.mapper;

import com.arianit.cityguideKosovo.dto.CityDto;
import com.arianit.cityguideKosovo.dto.GastronomeDto;
import com.arianit.cityguideKosovo.entity.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CityMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CityDto mapEntityToDto(City city) {
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        // Map the gastronomes manually
        if (city.getGastronomes() != null) {
            cityDto.setGastronomeDtos(city.getGastronomes()
                    .stream()
                    .map(gastronome -> modelMapper.map(gastronome, GastronomeDto.class))
                    .collect(Collectors.toList()));
        }
        return cityDto;
    }

    public City mapDtoToEntity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }
}
