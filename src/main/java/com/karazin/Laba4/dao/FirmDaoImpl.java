package com.karazin.Laba4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.karazin.Laba4.model.Firm;

public class FirmDaoImpl implements FirmDao {

    private JdbcTemplate jdbcTemplate;

    private static final String MYSQL_FIND_ALL_FIRMS = "SELECT * FROM FIRM";
    private static final String MYSQL_FIND_FIRM_BYID = "SELECT * FROM firm WHERE id_firm=";
    private static final String MYSQL_INSERT_FIRM = "insert into firm values (default, ?, ?, ?)";
    private static final String MYSQL_DELETE_FIRM = "delete from firm where id_firm = ?";
    private static final String MYSQL_UPDATE_FIRM = "update firm set name = ? , shef = ?, address= ? where id_firm = ?";
    private static final String MYSQL_FIND_FIRM_BY_LETTER = "SELECT * FROM firm where address like ?";

    private static final String ID_FIRM = "id_firm";
    private static final String SHEF = "shef";
    private static final String ADDRESS = "address";
    private static final String NAME = "name";

    public FirmDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Firm> getAll() {
        return jdbcTemplate.query(MYSQL_FIND_ALL_FIRMS, new FirmMapper());
    }

    @Override
    public List<Firm> getAllBYLetter(char letter) {
        return jdbcTemplate.query(MYSQL_FIND_FIRM_BY_LETTER,
                new Object[] { letter + "%" }, new FirmMapper());
    }

    @Override
    public void create(Firm firm) {
        jdbcTemplate.update(MYSQL_INSERT_FIRM, firm.getName(), firm.getShef(),
                firm.getAddress());
    }

    @Override
    public void delete(int firmID) {
        jdbcTemplate.update(MYSQL_DELETE_FIRM, firmID);

    }

    @Override
    public void update(Firm firm) {
        jdbcTemplate.update(MYSQL_UPDATE_FIRM, firm.getName(), firm.getShef(),
                firm.getAddress(), firm.getId());
    }

    @Override
    public Firm get(int firmID) {
        return jdbcTemplate.query(MYSQL_FIND_FIRM_BYID + firmID,
                new ResultSetExtractor<Firm>() {
                    @Override
                    public Firm extractData(ResultSet rs) throws SQLException {
                        if (rs.next()) {
                            Firm firm = new Firm();
                            firm.setId(rs.getInt(ID_FIRM));
                            firm.setName(rs.getString(NAME));
                            firm.setShef(rs.getString(SHEF));
                            firm.setAddress(rs.getString(ADDRESS));
                            return firm;
                        }

                        return null;
                    }

                });
    }

    private static class FirmMapper implements RowMapper<Firm> {

        @Override
        public Firm mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt(ID_FIRM);
            String name = resultSet.getString(NAME);
            String shef = resultSet.getString(SHEF);
            String address = resultSet.getString(ADDRESS);
            return new Firm(id, name, shef, address);
        }
    }

}
