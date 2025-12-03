package com.example.main.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.main.model.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncDao implements IFuncDao, ICRUDDao<Funcionario> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public FuncDao(Context context) {
        this.context = context;
    }

    @Override
    public FuncDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Funcionario funcionario) throws SQLException {
        ContentValues cv = getcontentValues(funcionario);
        db.insert("funcionario",null, cv);
    }

    @Override
    public int update(Funcionario funcionario) throws SQLException {
        ContentValues cv = getcontentValues(funcionario);
        return db.update("funcionario", cv, "id = " + funcionario.getid(), null);
    }

    @Override
    public void delete(Funcionario funcionario) throws SQLException {
        db.delete("funcionario","id = " + funcionario.getid(), null);
    }

    @Override
    public Funcionario findOne(@NonNull Funcionario funcionario) throws SQLException {
        String sql = "SELECT id, nome, salario, telefone, cargo FROM funcionario WHERE id = " + funcionario.getid();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            funcionario.setid(cursor.getInt(cursor.getColumnIndex("id")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setSalario(cursor.getDouble(cursor.getColumnIndex("salario")));
            funcionario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            funcionario.setCargo(cursor.getString(cursor.getColumnIndex("cargo")));
        }
        cursor.close();
        return funcionario;
    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> fs = new ArrayList<>();
        String sql = "SELECT id, nome, salario, telefone, cargo FROM funcionario";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setid(cursor.getInt(cursor.getColumnIndex("id")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setSalario(cursor.getDouble(cursor.getColumnIndex("salario")));
            funcionario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            funcionario.setCargo(cursor.getString(cursor.getColumnIndex("cargo")));

            cursor.moveToNext();
            fs.add(funcionario);
        }
        cursor.close();
        return fs;
    }


    private static ContentValues getcontentValues(Funcionario funcionario) {
        ContentValues cv = new ContentValues();
        cv.put("id", funcionario.getid());
        cv.put("nome", funcionario.getNome());
        cv.put("salario", funcionario.getSalario());
        cv.put("telefone", funcionario.getTelefone());
        cv.put("cargo", funcionario.getCargo());

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
