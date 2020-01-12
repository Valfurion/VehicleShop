package org.VehicleShop.controller;

import org.VehicleShop.entity.EngineType;
import org.VehicleShop.repository.EngineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engineType")
public class EngineTypeController {
    @Autowired
    private EngineTypeRepository engineTypeRepository;

    @GetMapping("/{id}")
    public EngineType getById(@PathVariable Long id) {
        return engineTypeRepository.findById(id);
    }


    @GetMapping
    public List<EngineType> getAll() {
        return engineTypeRepository.findAll();
    }

    @PostMapping
    public EngineType createEngineType(@RequestBody EngineType engineType) {
        return engineTypeRepository.createEngineType(engineType);
    }
}
