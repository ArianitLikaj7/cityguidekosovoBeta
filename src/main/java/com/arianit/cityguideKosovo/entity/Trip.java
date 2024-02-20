package com.arianit.cityguideKosovo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_of_days")
    private int numOfDays;

    @ElementCollection
    @CollectionTable(name = "trip_cities", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "city_id")
    private List<Long> cityIds;

    @ElementCollection
    @CollectionTable(name = "trip_gastronomes", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "type_of_gastronome")
    @Enumerated(EnumType.STRING)
    private List<TypeOfGastronome> typesOfGastronome;
}
