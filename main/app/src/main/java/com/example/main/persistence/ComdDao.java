package com.example.main.persistence;

import com.example.main.model.Comanda;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ComdDao implements IComdDao, ICRUDDao<Comanda> {
    @Override
    public ComdDao open() throws SQLException{
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void insert(Comanda comanda) throws SQLException {

    }

    @Override
    public int update(Comanda comanda) throws SQLException {
        return 0;
    }

    @Override
    public void delete(Comanda comanda) throws SQLException {

    }

    @Override
    public Comanda findOne(Comanda comanda) throws SQLException {
        return null;
    }

    @Override
    public List<Comanda> listar() throws SQLException {
        return Collections.emptyList();
    }
}
