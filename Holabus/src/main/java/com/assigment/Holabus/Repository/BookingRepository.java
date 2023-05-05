package com.assigment.Holabus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assigment.Holabus.Model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
 
}