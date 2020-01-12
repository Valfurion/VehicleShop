package org.VehicleShop.repository;

import org.VehicleShop.entity.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public VehicleType createVehicleType(VehicleType vehicleType) {
        String sql = "insert into vehicle.vehicle_type (vehicle_type_title) values (?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc->{
            PreparedStatement ps = psc.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,vehicleType.getVehicleTypeTitle());
            return ps;
        }, holder);
        long vehicleTypeId = (long) holder.getKeys().get("vehicle_type_id");
        vehicleType.setVehicleTypeId(vehicleTypeId);
        return vehicleType;

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
