package com.karazin.Laba4.dao;

import java.util.List;

import com.karazin.Laba4.model.Firm;

public interface FirmDao {

    List<Firm> getAll();

    void create(Firm firm);

    void delete(int firmID);

    void update(Firm firm);

    Firm get(int contactId);

    List<Firm> getAllBYLetter(char letter);

}
