package com.example.main.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "TRABALHO.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_FUNC =
            "CREATE TABLE funcionario ( "+
                    "id INT NOT NULL PRIMARY KEY, "+
                    "nome VARCHAR(100) NOT NULL, "+
                    "salario DOUBLE NOT NULL, "+
                    "telefone VARCHAR(11) NOT NULL, "+
                    "cargo VARCHAR(9) NOT NULL);";
    private static final String CREATE_TABLE_PROD =
            "CREATE TABLE produto ( "+
                    "id INT NOT NULL PRIMARY KEY, "+
                    "valor DOUBLE NOT NULL, "+
                    "nome VARCHAR(100) NOT NULL);";
    private static final String CREATE_TABLE_COMD =
            "CREATE TABLE comanda ( "+
                    "id INT NOT NULL PRIMARY KEY);";


    public GenericDao(Context context) {
        super(context,DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FUNC);
        db.execSQL(CREATE_TABLE_PROD);
        db.execSQL(CREATE_TABLE_COMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS comanda");
            db.execSQL("DROP TABLE IF EXISTS produto");
            db.execSQL("DROP TABLE IF EXISTS funcionario");
            onCreate(db);
        }
    }
}