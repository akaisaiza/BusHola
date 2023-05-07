package com.assigment.Holabus.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookingId")
    private int id;
    @Column(name = "startDate")
    @NotNull
    private LocalDate startDate;
    @Column(name = "endDate")
    @NotNull
    private LocalDate endDate;
    @Column(name = "createDate")
    @NotNull
    private LocalDate createDate;
    @Column(name = "project")
    @NotNull
    private String project;
    @Column(name = "status")
    @NotNull
    private String status;
    @Column(name = "requestStatus")
    @NotNull
    private String reqStatus;
    @Column(name = "purpose")
    @NotNull
    private String purpose;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "requester", referencedColumnName = "userId")
    private User requester;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "busNo", referencedColumnName = "busNo")
    private Bus bus;
}
