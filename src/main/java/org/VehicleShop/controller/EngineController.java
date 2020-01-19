package org.VehicleShop.controller;

import org.VehicleShop.entity.Engine;
import org.VehicleShop.repository.EngineRepository;
import org.VehicleShop.repository.EngineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engines")
public class EngineController {
    @Autowired
    private EngineRepository engineRepository;
    private EngineTypeRepository engineTypeRepository;

    @GetMapping
    public List<Engine> getAll() {
        return engineRepository.findAll();
    }

    @GetMapping("/{id}")
    public Engine getById(@PathVariable Long id) {
        return engineRepository.findById(id);
    }

    @PostMapping
    public Engine createEngine(@RequestBody Engine engine) {
        return engineRepository.createEngine(engine);

    }

    @PutMapping("/change/{id}")
    public Engine changeEngine(@PathVariable Long id, @RequestBody Engine engine) {
        engine.setEngineId(id);
        return engineRepository.changeEngine(engine);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteEngine( @PathVariable Long id){
        return engineRepository.deleteEngine(id);
    }

}
