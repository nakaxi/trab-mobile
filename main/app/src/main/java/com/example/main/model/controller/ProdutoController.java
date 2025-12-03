package com.example.main.model.controller;

import com.example.main.model.Produto;
import com.example.main.persistence.ProdDao;

import java.sql.SQLException;
import java.util.List;

public class ProdutoController implements IController <Produto>{

    private  ProdDao pDao;

    public ProdutoController(ProdDao pDao){
        this.pDao= pDao;
    }

    @Override
    public void inserir(Produto produto) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pDao.insert(produto);
        pDao.close();
    }

    @Override
    public void modificar(Produto produto) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pDao.update(produto);
        pDao.close();
    }

    @Override
    public void excluir(Produto produto) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pDao.delete(produto);
        pDao.close();
    }

    @Override
    public Produto buscar(Produto produto) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        return pDao.findOne(produto);
    }

    @Override
    public List<Produto> listar() throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        return pDao.listar();
    }

    public boolean existeId(int id) throws SQLException {
        if (pDao.open() == null) pDao.open();
        boolean existe = pDao.existeId(id);
        pDao.close();
        return existe;
    }
}

