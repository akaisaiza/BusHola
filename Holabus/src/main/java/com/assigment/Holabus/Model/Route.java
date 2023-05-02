package com.assigment.Holabus.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "route")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeId")
    private int id;
    @Column(name = "routeName")
    @NotNull
    private String routeName;
    @ManyToOne
    @JoinColumn(name = "startLocId", referencedColumnName = "locId")
    private Location startLocation;
    @ManyToOne
    @JoinColumn(name = "desLocId", referencedColumnName = "locId")
    private Location destinationLocation;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "routedetail",
            joinColumns = {@JoinColumn(name = "routeId", referencedColumnName = "routeId")},
            inverseJoinColumns = {@JoinColumn(name = "locId", referencedColumnName = "locId")})
    private List<Location> locations;
}
