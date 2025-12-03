package com.example.main.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.main.model.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdDao implements IProdDao, ICRUDDao<Produto> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public ProdDao(Context context) {
        this.context = context;
    }
    @Override
    public ProdDao open() throws SQLException{
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Produto produto) throws SQLException {
        ContentValues cv = getContentValues(produto);
        db.insert("produto", null, cv);
    }

    @Override
    public int update(Produto produto) throws SQLException {
        ContentValues cv = getContentValues(produto);
        return db.update("produto", cv, "id = " + produto.getId(), null);
    }

    @Override
    public void delete(Produto produto) throws SQLException {
        db.delete("produto", "id = " + produto.getId(), null);
    }

    @Override
    public Produto findOne(Produto produto) throws SQLException {
        String sql = "SELECT id, nome, valor FROM produto WHERE id = " + produto.getId();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            produto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            produto.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
        }
        cursor.close();
        return produto;
    }

    @Override
    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor FROM produto";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Produto p = new Produto();
                p.setId(cursor.getInt(cursor.getColumnIndex("id")));
                p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                p.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

                cursor.moveToNext();
                produtos.add(p);
            }

        cursor.close();
        return produtos;
    }

    private static ContentValues getContentValues(Produto produto) {
        ContentValues cv = new ContentValues();
        cv.put("id", produto.getId());
        cv.put("nome", produto.getNome());
        cv.put("valor", produto.getValor());
        return cv;
    }

    public boolean existeId(int id) {
        String sql = "SELECT id FROM funcionario WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }
}
