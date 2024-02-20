package com.arianit.cityguideKosovo.mapper;

import com.arianit.cityguideKosovo.dto.ReservationDto;
import com.arianit.cityguideKosovo.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ReservationDto mapEntityToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }
    public Reservation mapDtoToEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }

}