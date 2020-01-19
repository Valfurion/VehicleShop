package org.VehicleShop.controller;

import org.VehicleShop.entity.Vehicle;
import org.VehicleShop.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")

public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return vehicleRepository.findById(id);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.createVehicle(vehicle);
    }

    @PutMapping("/change/{id}")
    public Vehicle changeVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicle.setVehicleId(id);
        return vehicleRepository.changeVehicle(vehicle);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteVehicle(@PathVariable Long id){
        return vehicleRepository.deleteVehicle(id);
    }
}
