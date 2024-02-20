package com.arianit.cityguideKosovo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gastronomies")
public class Gastronome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gastronome_id")
    private Long gastronomeId;

    @Column(nullable = false)
    private String nameOfGastronome;

    @Column(length = 255)
    private String schedule;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Enumerated(EnumType.STRING)
    private TypeOfGastronome typeOfGastronome;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @OneToMany(mappedBy = "gastronome", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;
}