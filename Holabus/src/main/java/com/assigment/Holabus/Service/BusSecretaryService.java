package com.assigment.Holabus.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.Holabus.Model.Bus;
import com.assigment.Holabus.Model.Route;
import com.assigment.Holabus.Model.Secretary;
import com.assigment.Holabus.Repository.BusRepository;
import com.assigment.Holabus.Repository.RouteRespository;
import com.assigment.Holabus.Repository.SecretaryRepository;

@Service
public class BusSecretaryService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private RouteRespository routeRepository;

    // public void getInfo() {
    //     Bus bus = busRepository.findByBusNo(123);
    //     int busNo = bus.getId();
    //     double busNum = RouteBus;
    //     Secretary secretary = bus.getSecretary();
    //     int secId = secretary.getId();
    //     String secName = secretary.getName();
    //     String secPhoneNumber = secretary.getPhoneNumber();
    //     Route route = bus.getRoute();
    //     String routeName = route.getRouteName();

    // }
}