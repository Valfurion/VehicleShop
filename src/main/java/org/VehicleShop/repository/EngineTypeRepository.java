package org.VehicleShop.repository;

import org.VehicleShop.entity.EngineType;
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

    public EngineType createEngineType(EngineType engineType) {
        String sql = "insert into vehicle.engine_type (engine_type_title) values (?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, engineType.getEngineTypeTitle());
            return ps;
        }, holder);
        long engineTypeId = (long) holder.getKeys().get("engine_type_id");
        engineType.setEngineTypeId(engineTypeId);
        return engineType;
    }

    public EngineType findByTitle(String engineTypeTitle) {
        String sql = "select * from vehicle.engine_type where engine_type_title =" + engineTypeTitle;
        return jdbcTemplate.queryForObject(sql, new EngineTypeMapper());
    }

    public EngineType changeEngineType(EngineType engineType) {
        String sql = "update vehicle.engine_type set engine_type_title=? where engine_type_id =?";
        jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(sql);
            ps.setString(1, engineType.getEngineTypeTitle());
            ps.setLong(2, engineType.getEngineTypeId());
            return ps;
        });
        return engineType;
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
