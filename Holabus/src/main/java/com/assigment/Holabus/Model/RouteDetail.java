package com.assigment.Holabus.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "routedetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteDetail implements Serializable {
    @EmbeddedId
    private RouteDetailPK id;
    @Column(name = "numOfMin")
    @NotNull
    private int numOfMin;
}
