package com.karazin.Laba4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.karazin.Laba4.model.Contract;

public class ContractDaoImpl implements ContractDao {

    private JdbcTemplate jdbcTemplate;

    private static final String MYSQL_FIND_ALL_CONTRACTS = "SELECT * FROM dogovor";
    private static final String MYSQL_FIND_CONTRACTS_BY_FIRM_ID = "SELECT * FROM dogovor WHERE id_firm=";
    private static final String MYSQL_FIND_CONTRACT_BY_ID = "SELECT * FROM dogovor WHERE id_d=";
    private static final String MYSQL_FIND_CONTRACT_BY_DATE_RANGE = "SELECT * FROM dogovor where "
            + "id_firm = ? and (datastart between ? and ?)";
    private static final String MYSQL_INSERT_CONTRACT = "insert into dogovor values (default, ?, ?, ?, ? , ? ,?,?)";
    private static final String MYSQL_DELETE_CONTRACT = "delete from dogovor where id_d = ?";
    private static final String MYSQL_UPDATE_CONTRACT = "update dogovor set  numbered = ?,"
            + " named= ? , sumd = ? , datastart = ? , datafinish = ? , avans = ? where id_d = ?";
    private static final String ID = "id_d";
    private static final String ID_FIRM = "id_firm";
    private static final String NUMBERED = "numbered";
    private static final String NAMED = "named";
    private static final String SUMD = "sumd";
    private static final String DATA_START = "datastart";
    private static final String DATA_FINISH = "datafinish";
    private static final String AVANS = "avans";

    public ContractDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Contract> getAll() {
        return jdbcTemplate.query(MYSQL_FIND_ALL_CONTRACTS,
                new ContractMapper());
    }

    @Override
    public Contract getByLetter() {
        return null;
    }

    @Override
    public List<Contract> getAllByRangeDate(String start, String finish,
            int idFirm) {
        List<Contract> reulst = jdbcTemplate.query(
                MYSQL_FIND_CONTRACT_BY_DATE_RANGE,
                new Object[] { idFirm, start, finish }, new ContractMapper());
        return reulst;

    }

    @Override
    public void create(Contract contract) {
        jdbcTemplate.update(MYSQL_INSERT_CONTRACT, contract.getIdFirm(),
                contract.getNumbered(), contract.getNamed(), contract.getSumd(),
                contract.getDataStart(), contract.getDataFinish(),
                contract.getAvans());
    }

    @Override
    public void delete(int contractId) {
        jdbcTemplate.update(MYSQL_DELETE_CONTRACT, contractId);

    }

    @Override
    public void update(Contract contract) {
        jdbcTemplate.update(MYSQL_UPDATE_CONTRACT, contract.getNumbered(),
                contract.getNamed(), contract.getSumd(),
                contract.getDataStart(), contract.getDataFinish(),
                contract.getAvans(), contract.getId());
    }

    @Override
    public List<Contract> get(int contractID) {
        return jdbcTemplate.query(MYSQL_FIND_CONTRACTS_BY_FIRM_ID + contractID,
                new ContractMapper());
    }

    private static class ContractMapper implements RowMapper<Contract> {

        @Override
        public Contract mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt(ID);
            int idFirm = resultSet.getInt(ID_FIRM);
            String numbered = resultSet.getString(NUMBERED);
            String named = resultSet.getString(NAMED);
            double sumd = resultSet.getDouble(SUMD);
            LocalDate dataStart = resultSet.getDate(DATA_START).toLocalDate();
            LocalDate dataFinish = resultSet.getDate(DATA_FINISH).toLocalDate();
            double avans = resultSet.getDouble(AVANS);
            return new Contract(id, idFirm, numbered, named, sumd, dataStart,
                    dataFinish, avans);
        }
    }

    @Override
    public Contract getContractById(int id) {
        return jdbcTemplate.query(MYSQL_FIND_CONTRACT_BY_ID + id,
                new ResultSetExtractor<Contract>() {
                    @Override
                    public Contract extractData(ResultSet rs)
                            throws SQLException {
                        if (rs.next()) {
                            Contract contract = new Contract();
                            contract.setId(rs.getInt(ID));
                            contract.setIdFirm(rs.getInt(ID_FIRM));
                            contract.setNumbered(rs.getString(NUMBERED));
                            contract.setNamed(rs.getString(NAMED));
                            contract.setSumd(rs.getDouble(SUMD));
                            contract.setDataStart(
                                    rs.getDate(DATA_START).toLocalDate());
                            contract.setDataFinish(
                                    rs.getDate(DATA_FINISH).toLocalDate());
                            contract.setAvans(rs.getDouble(AVANS));
                            return contract;
                        }

                        return null;
                    }

                });
    }

}
