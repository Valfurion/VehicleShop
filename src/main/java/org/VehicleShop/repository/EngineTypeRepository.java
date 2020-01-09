package org.VehicleShop.repository;

import org.VehicleShop.entity.EngineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EngineTypeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public EngineType findById(Long id) {
        String sql = "select * from vehicle.engine_type where engine_type_id = " + id;
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, new EngineTypeMapper());
    }

    public List<EngineType> findAll() {
        String sql = "select * from vehicle.engine_type";
        return jdbcTemplate.query(sql, new EngineTypeMapper());
    }


    private class EngineTypeMapper implements RowMapper<EngineType> {

        @Override
        public EngineType mapRow(ResultSet resultSet, int i) throws SQLException {
            EngineType engineType = new EngineType();
            engineType.setEngineTypeId(resultSet.getLong("engine_type_id"));
            engineType.setEngineTypeTitle(resultSet.getString("engine_type_title"));
            return engineType;
        }
    }
}
