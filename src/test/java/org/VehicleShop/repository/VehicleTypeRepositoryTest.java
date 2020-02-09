package org.VehicleShop.repository;

import org.VehicleShop.entity.VehicleType;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class VehicleTypeRepositoryTest {

    @InjectMocks
    private VehicleTypeRepository target;

    private VehicleType vehicleType;

    @Test
    public void createVehicleType() {
        vehicleType = new VehicleType();
        vehicleType.setVehicleTypeId(1l);
        vehicleType.setVehicleTypeTitle("SUV");
        target.createVehicleType(vehicleType);
        assertEquals(1, target.findAll().size());
    }


    @Test
    void findAll() {
        List<VehicleType> vehicleTypes = target.findAll();
        assertEquals(1, vehicleTypes.size());
        assertEquals(vehicleType, vehicleTypes.get(0));
    }

    @Test
    void findById() {
    }


    @Test
    void changeVehicleType() {
    }

    @Test
    void deleteVehicleType() {
    }
}