package com.example.main.model;

import androidx.annotation.NonNull;

public class Comanda {

    private int id;

    public Comanda(){

    }

    public Comanda(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                '}';
    }
}
