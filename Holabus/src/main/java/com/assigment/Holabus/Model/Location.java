package com.assigment.Holabus.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locId")
    private int id;
    @Column(name = "locName")
    @NotNull
    private String locationName;
    @ManyToMany(mappedBy = "pickupLocation")
    private List<Bus> buses;
}
