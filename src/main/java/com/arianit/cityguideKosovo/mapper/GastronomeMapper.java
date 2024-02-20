package com.arianit.cityguideKosovo.mapper;


import com.arianit.cityguideKosovo.dto.GastronomeDto;
import com.arianit.cityguideKosovo.entity.Gastronome;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GastronomeMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public GastronomeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GastronomeDto mapEntityToDto(Gastronome gastronome) {
        return modelMapper.map(gastronome, GastronomeDto.class);
    }
    public Gastronome mapDtoToEntity(GastronomeDto gastronomeDto) {
        return modelMapper.map(gastronomeDto, Gastronome.class);
    }
}