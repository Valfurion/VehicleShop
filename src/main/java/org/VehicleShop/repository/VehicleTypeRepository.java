package org.VehicleShop.repository;

import org.VehicleShop.entity.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String sql = "select * from vehicle.vehicle_type where vehicle_type_id =" + id;
        return jdbcTemplate.queryForObject(sql, new VehicleTypeMapper());
    }

    public VehicleType createVehicleType(VehicleType vehicleType) {
        String sql = "insert into vehicle.vehicle_type (vehicle_type_title) values (?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehicleType.getVehicleTypeTitle());
            return ps;
        }, holder);
        long vehicleTypeId = (long) holder.getKeys().get("vehicle_type_id");
        vehicleType.setVehicleTypeId(vehicleTypeId);
        return vehicleType;

    }

    public VehicleType changeVehicleType(VehicleType vehicleType) {
        String sql = "update vehicle.vehicle_type set vehicle_type_title=? where vehicle_type_id=?";
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql);
            ps.setString(1, vehicleType.getVehicleTypeTitle());
            ps.setLong(2, vehicleType.getVehicleTypeId());
            return ps;
        });
        return findById(vehicleType.getVehicleTypeId());
    }

    public Boolean deleteVehicleType(Long id) {
        String sql = "delete from vehicle.vehicle_type where vehicle_type_id = ?";
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        });
        try {
            findById(id);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        return false;
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
