package com.arianit.cityguideKosovo.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "gastronome_id")
    @JsonManagedReference
    private Gastronome gastronome;

    private String reservationDate;

    private Integer numberOfPeople;

    private String specialRequests;

    @Column(nullable = false)
    private String phoneNumber;

    private String status;

}