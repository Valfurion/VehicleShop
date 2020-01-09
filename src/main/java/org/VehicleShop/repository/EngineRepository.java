package org.VehicleShop.repository;

import org.VehicleShop.entity.Engine;
import org.VehicleShop.entity.EngineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EngineRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Engine> getAll() {
        String sql = "select * from vehicle.engine";
        return jdbcTemplate.query(sql, new EngineMapper());

    }

    public Engine getById(Long id) {
        String sql = "select * from vehicle.engine where engine_id =" + id;

        return jdbcTemplate.queryForObject(sql, new EngineMapper());

    }

    private class EngineMapper implements RowMapper<Engine> {

        @Override
        public Engine mapRow(ResultSet resultSet, int i) throws SQLException {
            Engine engine = new Engine();
            engine.setEngineId(resultSet.getLong("engine_id"));
            engine.setTitle(resultSet.getString("title"));
            engine.setVolume(resultSet.getString("volume"));
            EngineType engineType = new EngineType();
            engineType.setEngineTypeId(resultSet.getLong("engine_type_id"));
            engine.setEngineType(engineType);

            return engine;
        }

    }

}

