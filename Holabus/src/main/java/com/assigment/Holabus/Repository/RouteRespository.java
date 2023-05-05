package com.assigment.Holabus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assigment.Holabus.Model.Route;


public interface RouteRespository extends JpaRepository<Route, Integer> {
    RouteRespository findByRouteName(String routeName);
}
