package com.assigment.Holabus.Repository;

import com.assigment.Holabus.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Iterable<Bus> findBusesByBusType(@NotNull int busType);
}
