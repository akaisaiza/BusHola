package com.assigment.Holabus.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "routebus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteBus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "busNo")
    private int id;
    @Column(name = "busNum")
    @NotNull
    private int busNum;
    @Column(name = "routeGroup")
    @NotNull
    private int group;
    @Column(name = "returnTime")
    @NotNull
    private LocalTime time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkingId", referencedColumnName = "parkingId")
    private ParkingLot parkingLot;
    @OneToOne
    @JoinColumn(name = "busNo", referencedColumnName = "busNo")
    private Bus bus;
}
