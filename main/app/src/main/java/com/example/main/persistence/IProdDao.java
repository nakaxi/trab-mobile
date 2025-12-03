package com.example.main.persistence;

import java.sql.SQLException;

public interface IProdDao {

    public ProdDao open() throws SQLException;
    public void close();
}
