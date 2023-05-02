package com.assigment.Holabus.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parkingId")
    private int id;
    @Column(name = "parkingName")
    @NotNull
    private String name;
    @Column(name = "parkingFee")
    @NotNull
    private int fee;
}
