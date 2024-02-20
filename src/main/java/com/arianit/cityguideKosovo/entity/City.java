package com.arianit.cityguideKosovo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cityId;

    @Column(nullable = false)
    private String nameOfCity;

    @Column(nullable = false)
    private String culturalHeritage;

    @OneToMany(mappedBy = "city")
    private List<Gastronome> gastronomes;

}