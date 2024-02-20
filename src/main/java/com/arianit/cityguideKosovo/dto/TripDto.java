package com.arianit.cityguideKosovo.dto;

import com.arianit.cityguideKosovo.entity.TypeOfGastronome;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private int numOfDays;
    private List<Long> cityIds;
    private List<TypeOfGastronome> typesOfGastronome;
    private List<CityDto> cityDtos;
}
