package com.example.main.model.controller;

import com.example.main.model.Comanda;
import com.example.main.persistence.ComdDao;

import java.sql.SQLException;
import java.util.List;

public class ComandaController  implements IController <Comanda>{

    private ComdDao cDao;

    public ComandaController(ComdDao cDao){
        this.cDao = cDao;
    }

    @Override
    public void inserir(Comanda comanda) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.insert(comanda);
        cDao.close();
    }

    @Override
    public void modificar(Comanda comanda) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.update(comanda);
        cDao.close();
    }

    @Override
    public void excluir(Comanda comanda) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.delete(comanda);
        cDao.close();
    }

    @Override
    public Comanda buscar(Comanda comanda) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        return cDao.findOne(comanda);
    }

    @Override
    public List<Comanda> listar() throws SQLException {
        if(cDao.open() == null) {
            cDao.open();
        }
        return cDao.listar();
    }

    public boolean existeId(int id) throws SQLException {
        if (cDao.open() == null) cDao.open();
        boolean existe = cDao.existeId(id);
        cDao.close();
        return existe;
    }
}
