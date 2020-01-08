package org.VehicleShop.controller;

import org.VehicleShop.entity.EngineType;
import org.VehicleShop.repository.EngineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engineType")
public class EngineTypeController {
    @Autowired
    private EngineTypeRepository engineTypeRepository;

    @GetMapping("/{id}")
    public EngineType engineTypes(@PathVariable Long id) {
        return engineTypeRepository.getById(id);
    }
}
