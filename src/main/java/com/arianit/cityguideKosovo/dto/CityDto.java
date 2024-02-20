package com.arianit.cityguideKosovo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CityDto {
    private Long cityId;
    private String nameOfCity;
    private String culturalHeritage;
    private List<GastronomeDto> gastronomeDtos;

}
