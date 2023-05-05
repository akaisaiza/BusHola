package com.assigment.Holabus.Controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.Holabus.Service.BusSecretaryService;

@RestController
@RequestMapping("/api/bus-routes")
public class BusSecretaryController {
    private final BusSecretaryService busService;

    public BusSecretaryController(BusSecretaryService busService) {
        this.busService = busService;
    }

   
}
