package com.example.main.persistence;

import java.sql.SQLException;

public interface IFuncDao {

    public FuncDao open() throws SQLException;
    public void close();
}
