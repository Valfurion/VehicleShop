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
    public VehicleType getById(@PathVariable Long id){
        return vehicleTypeRepository.findById(id);
    }

}
