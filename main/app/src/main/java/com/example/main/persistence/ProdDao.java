package com.example.main.persistence;

import com.example.main.model.Produto;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProdDao implements IProdDao, ICRUDDao<Produto> {
    @Override
    public ProdDao open() throws SQLException{
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void insert(Produto produto) throws SQLException {

    }

    @Override
    public int update(Produto produto) throws SQLException {
        return 0;
    }

    @Override
    public void delete(Produto produto) throws SQLException {

    }

    @Override
    public Produto findOne(Produto produto) throws SQLException {
        return null;
    }

    @Override
    public List<Produto> listar() throws SQLException {
        return Collections.emptyList();
    }
}
