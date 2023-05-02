package com.assigment.Holabus.Model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RouteDetailPK implements Serializable {
    private int routeId;
    private int locId;
}
