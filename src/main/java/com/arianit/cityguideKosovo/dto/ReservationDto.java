package com.arianit.cityguideKosovo.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private Long gastronomeId;
    private String reservationDate;
    private Integer numberOfPeople;
    private String specialRequests;
    private String phoneNumber;
    private String status;
}