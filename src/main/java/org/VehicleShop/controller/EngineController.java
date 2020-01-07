package org.VehicleShop.controller;

import org.VehicleShop.entity.Engine;
import org.VehicleShop.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/engine")
public class EngineController {
    @Autowired
    private EngineRepository engineRepository;

    @GetMapping
    public List<Engine> engines() {
        return engineRepository.getAll();
    }
}
