package com.arianit.cityguideKosovo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GastronomeDto {
    private Long gastronomeId;
    private Long cityId;
    private String nameOfGastronome;
    private String schedule;
    private Double longitude;
    private Double latitude;
    private String typeOfGastronome;

}