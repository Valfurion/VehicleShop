package org.VehicleShop.repository;

import org.VehicleShop.entity.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VehicleTypeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VehicleType> findAll() {
        String sql = "select * from vehicle.vehicle_type";
        return jdbcTemplate.query(sql, new VehicleTypeMapper());
    }

    public VehicleType findById(Long id) {
        String sql = "select * from vehicle.vehicle_type where vehicle_type_id =" +id;
        return jdbcTemplate.queryForObject(sql, new VehicleTypeMapper());
    }


    private class VehicleTypeMapper implements RowMapper<VehicleType> {

        @Override
        public VehicleType mapRow(ResultSet resultSet, int i) throws SQLException {
            VehicleType vehicleType = new VehicleType();
            vehicleType.setVehicleTypeId(resultSet.getLong("vehicle_type_id"));
            vehicleType.setVehicleTypeTitle(resultSet.getString("vehicle_type_title"));
            return vehicleType;
        }
    }
}
