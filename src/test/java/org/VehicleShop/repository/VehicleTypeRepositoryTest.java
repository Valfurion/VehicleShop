package org.VehicleShop.repository;

import org.VehicleShop.entity.VehicleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleTypeRepositoryTest {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Test
    public void JDBCTest() {
        //Вставка и проверка, того что БД вернула id
        VehicleType vehicleType1 = new VehicleType();
        vehicleType1.setVehicleTypeTitle("SUV");
        vehicleType1 = vehicleTypeRepository.createVehicleType(vehicleType1);

        VehicleType vehicleType2 = new VehicleType();
        vehicleType2.setVehicleTypeTitle("HeavyTruck");
        vehicleType2 = vehicleTypeRepository.createVehicleType(vehicleType2);
        assertNotNull(vehicleType1.getVehicleTypeId());
        assertNotNull(vehicleType2.getVehicleTypeId());

        //Проверка что в базе 2 или более записей
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
        assertTrue(vehicleTypes.size()>=2);

        //Проверка поиска по id
        assertEquals(vehicleType2, vehicleTypeRepository.findById(vehicleType2.getVehicleTypeId()));

        //Проверка возможности изменения записи
        String title = "MonsterTruck";
        vehicleType2.setVehicleTypeTitle(title);
        vehicleTypeRepository.changeVehicleType(vehicleType2);
        VehicleType vehicleTypeFromDB = vehicleTypeRepository.findById(vehicleType2.getVehicleTypeId());
        assertEquals(title, vehicleTypeFromDB.getVehicleTypeTitle());

        //Проверка возможности удаления
        vehicleTypeRepository.deleteVehicleType(vehicleType2.getVehicleTypeId());
        boolean throwedException = false;
        try {
            vehicleTypeRepository.findById(vehicleType2.getVehicleTypeId());
        }
        catch(EmptyResultDataAccessException exception){
            throwedException=true;
        }
        assertTrue(throwedException);
    }
}