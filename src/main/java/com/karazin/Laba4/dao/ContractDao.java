package com.karazin.Laba4.dao;

import java.util.List;

import com.karazin.Laba4.model.Contract;

public interface ContractDao {

    List<Contract> getAll();

    Contract getByLetter();

    List<Contract> getAllByRangeDate(String start, String finish, int idFirm);

    void create(Contract contract);

    void delete(int contractId);

    void update(Contract contract);

    List<Contract> get(int id);

    Contract getContractById(int id);

}
