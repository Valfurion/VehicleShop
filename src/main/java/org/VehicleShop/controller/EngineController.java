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

    /*@PutMapping("/change/{id}")
    public Engine putEngine(@PathVariable Long id, @RequestBody Engine engine) {
        return engineRepository.changeEngine(engine);
    }
*/
}
