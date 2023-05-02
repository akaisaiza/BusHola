package com.assigment.Holabus.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "busNo")
    private int id;
    @Column(name = "busType")
    @NotNull
    private int busType;
    @Column(name = "departureTime")
    @NotNull
    private LocalTime departureTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secId", referencedColumnName = "secId")
    private Secretary secretary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeId", referencedColumnName = "routeId")
    private Route route;

    @ManyToMany
    @JoinTable(name = "pickuplocation",
            joinColumns = {@JoinColumn(name = "busNo", referencedColumnName = "busNo")},
            inverseJoinColumns = {@JoinColumn(name = "locId", referencedColumnName = "locId")})
    private List<Location> pickupLocation;
}
