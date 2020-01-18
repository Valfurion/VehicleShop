package org.VehicleShop.repository;

import org.VehicleShop.entity.Engine;
import org.VehicleShop.entity.EngineType;
import org.VehicleShop.entity.Vehicle;
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
public class VehicleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Engine engine;
    private VehicleType vehicleType;


    public List<Vehicle> findAll() {
        String sql = "select v.vehicle_id, v.title , vt.vehicle_type_id, vt.vehicle_type_title, e.engine_id, e.title as engine_title, e.volume, et.engine_type_id, et.engine_type_title\n" +
                "from vehicle.vehicle v\n" +
                "left join vehicle.vehicle_type vt on v.vehicle_type_id = vt.vehicle_type_id\n" +
                "left join vehicle.engine e on v.engine_id = e.engine_id\n" +
                "left join vehicle.engine_type et on e.engine_type_id = et.engine_type_id";
        return jdbcTemplate.query(sql, new VehicleMapper());

    }

    public Vehicle findById(Long id) {
        String sql = "select v.vehicle_id, v.title , vt.vehicle_type_id, vt.vehicle_type_title, e.engine_id, e.title as engine_title, e.volume, et.engine_type_id, et.engine_type_title\n" +
                "from vehicle.vehicle v\n" +
                "left join vehicle.vehicle_type vt on v.vehicle_type_id=vt.vehicle_type_id\n" +
                "left join vehicle.engine e on v.engine_id=e.engine_id\n" +
                "left join vehicle.engine_type et on e.engine_type_id=et.engine_type_id\n" +
                "where v.vehicle_id = " + id;
        return jdbcTemplate.queryForObject(sql, new VehicleMapper());
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        String sql = "insert into vehicle.vehicle (title, engine_id, vehicle_type_id) values (?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehicle.getTitle());
            ps.setLong(2, vehicle.getEngine().getEngineId());
            ps.setLong(3, vehicle.getVehicleType().getVehicleTypeId());
            return ps;
        }, holder);
        long vehicleId = (long) holder.getKeys().get("vehicle_id");
        vehicle.setVehicleId(vehicleId);
        return vehicle;
    }

    public Vehicle changeVehicle(Vehicle vehicle) {
        String sql = "update vehicle.vehicle set title=?, engine_id=?, vehicle_type_id=? where vehicle_id=?";
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql);
            ps.setString(1, vehicle.getTitle());
            ps.setLong(2, vehicle.getEngine().getEngineId());
            ps.setLong(3, vehicle.getVehicleType().getVehicleTypeId());
            ps.setLong(4, vehicle.getVehicleId());
            return ps;
        });
        return findById(vehicle.getVehicleId());
    }


    private class VehicleMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(resultSet.getLong("vehicle_Id"));
            vehicle.setTitle(resultSet.getString("title"));
            Engine engine = new Engine();
            engine.setEngineId(resultSet.getLong("engine_id"));
            engine.setTitle(resultSet.getString("engine_title"));
            engine.setVolume(resultSet.getString("volume"));
            EngineType engineType = new EngineType();
            engineType.setEngineTypeId(resultSet.getLong("engine_type_id"));
            engineType.setEngineTypeTitle(resultSet.getString("engine_type_title"));
            engine.setEngineType(engineType);
            vehicle.setEngine(engine);

            VehicleType vehicleType = new VehicleType();
            vehicleType.setVehicleTypeId(resultSet.getLong("vehicle_type_id"));
            vehicleType.setVehicleTypeTitle(resultSet.getString("vehicle_type_title"));
            vehicle.setVehicleType(vehicleType);
            return vehicle;
        }
    }


}

