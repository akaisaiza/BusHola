package com.assigment.Holabus.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "secretary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Secretary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "secId")
    private int id;
    @Column(name = "secName")
    @NotNull
    private String name;
    @Column(name = "secPhoneNumber")
    @NotNull
    private String phoneNumber;
}
