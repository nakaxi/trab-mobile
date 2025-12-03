package com.example.main.persistence;

import java.sql.SQLException;

public interface IComdDao {

    public ComdDao open() throws SQLException;
    public void close();
}
