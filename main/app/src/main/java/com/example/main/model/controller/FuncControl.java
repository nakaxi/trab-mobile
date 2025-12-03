package com.example.main.model.controller;

import com.example.main.model.Funcionario;
import com.example.main.persistence.FuncDao;

import java.sql.SQLException;
import java.util.List;

public class FuncControl implements IController<Funcionario> {

    private final FuncDao fDao;

    public FuncControl(FuncDao fDao) throws SQLException {
        this.fDao = fDao;
    }

    @Override
    public void inserir(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null) {
            fDao.open();
        }
        fDao.insert(funcionario);
        fDao.close();
    }

    @Override
    public void modificar(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null) {
            fDao.open();
        }
        fDao.update(funcionario);
        fDao.close();
    }

    @Override
    public void excluir(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null) {
            fDao.open();
        }
        fDao.delete(funcionario);
        fDao.close();
    }

    @Override
    public Funcionario buscar(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null) {
            fDao.open();
        }
        return fDao.findOne(funcionario);
    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        if(fDao.open() == null) {
            fDao.open();
        }
        return fDao.listar();
    }

    public boolean existeId(int id) throws SQLException {
        if (fDao.open() == null) fDao.open();
        boolean existe = fDao.existeId(id);
        fDao.close();
        return existe;
    }

}
