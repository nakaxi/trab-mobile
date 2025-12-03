package com.example.main.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.main.model.Comanda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComdDao implements IComdDao, ICRUDDao<Comanda> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public ComdDao(Context context) {
        this.context = context;
    }

    @Override
    public ComdDao open() throws SQLException{
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Comanda comanda) throws SQLException {
        ContentValues cv = getContentValues(comanda);
        db.insert("comanda", null, cv);
    }

    @Override
    public int update(Comanda comanda) throws SQLException {
        ContentValues cv = getContentValues(comanda);
        return db.update("comanda", cv, "id = " + comanda.getId(), null);
    }

    @Override
    public void delete(Comanda comanda) throws SQLException {
        db.delete("comanda", "id = " + comanda.getId(), null);
    }

    @Override
    public Comanda findOne(Comanda comanda) throws SQLException {
        String sql = "SELECT id FROM comanda WHERE id = " + comanda.getId();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            comanda.setId(cursor.getInt(cursor.getColumnIndex("id")));
        }

        cursor.close();
        return comanda;
    }

    @Override
    public List<Comanda> listar() throws SQLException {
        List<Comanda> comandas = new ArrayList<>();
        String sql = "SELECT id FROM comanda";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Comanda c = new Comanda();
                c.setId(cursor.getInt(cursor.getColumnIndex("id")));
                comandas.add(c);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return comandas;
    }

    private static ContentValues getContentValues(Comanda comanda) {
        ContentValues cv = new ContentValues();
        cv.put("id", comanda.getId());
        return cv;
    }

    public boolean existeId(int id) {
        String sql = "SELECT id FROM comanda WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }
}
