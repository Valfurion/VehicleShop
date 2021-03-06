package org.VehicleShop.controller;

import org.VehicleShop.entity.VehicleType;
import org.VehicleShop.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicleTypes")

public class VehicleTypeController {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @GetMapping
    public List<VehicleType> getAll() {
        return vehicleTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public VehicleType getById(@PathVariable Long id) {
        return vehicleTypeRepository.findById(id);
    }

    @PostMapping
    public VehicleType createVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypeRepository.createVehicleType(vehicleType);
    }

    @PutMapping("/change/{id}")
    public VehicleType changeVehicleType(@PathVariable Long id, @RequestBody VehicleType vehicleType) {
        vehicleType.setVehicleTypeId(id);
        return vehicleTypeRepository.changeVehicleType(vehicleType);
    }

    @DeleteMapping("/{id}")
    public Boolean vehicleType(@PathVariable Long id) {
        return vehicleTypeRepository.deleteVehicleType(id);
    }
}
