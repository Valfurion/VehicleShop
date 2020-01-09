package org.VehicleShop.repository;

import org.VehicleShop.entity.Engine;
import org.VehicleShop.entity.EngineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EngineRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private EngineType EngineType;

    public List<Engine> getAll() {
        String sql = "select e.engine_id, e.title as engine_title, e.volume as engine_volume, et.engine_type_id, et.engine_type_title\n" +
                "from vehicle.engine e\n" +
                "left join vehicle.engine_type et on e.engine_type_id=et.engine_type_id";
        return jdbcTemplate.query(sql, new EngineMapper());

    }

    public Engine getById(Long id) {
        String sql = "select e.engine_id, e.title as engine_title, e.volume as engine_volume, et.engine_type_id, et.engine_type_title\n" +
                "from vehicle.engine e\n" +
                "left join vehicle.engine_type et on e.engine_type_id=et.engine_type_id\n" +
                "where e.engine_id = " + id;

        return jdbcTemplate.queryForObject(sql, new EngineMapper());

    }

    /*public Engine changeEngine(Engine engine) {
        String sql = "update vehicle.engine set title='?', volume=?, engine_type_id =? where engine_id=?";
        System.out.println(sql);
        engine.setEngineType(EngineType);
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql);
            ps.setString(1, engine.getTitle());
            ps.setString(2, engine.getVolume());
            ps.setLong(3, engine.getEngineType().getEngineTypeId());
            ps.setLong(4, engine.getEngineId());
            return ps;
        });
        return getById(engine.getEngineId());

    }

     */

    private class EngineMapper implements RowMapper<Engine> {

        @Override
        public Engine mapRow(ResultSet resultSet, int i) throws SQLException {
            Engine engine = new Engine();
            engine.setEngineId(resultSet.getLong("engine_id"));
            engine.setTitle(resultSet.getString("engine_title"));
            engine.setVolume(resultSet.getString("engine_volume"));
            EngineType engineType = new EngineType();
            engineType.setEngineTypeId(resultSet.getLong("engine_type_id"));
            engineType.setEngineTypeTitle(resultSet.getString("engine_type_title"));
            engine.setEngineType(engineType);
            return engine;
        }

    }

}

