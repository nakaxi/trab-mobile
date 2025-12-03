package com.example.main.model;

import androidx.annotation.NonNull;

public class Produto {

    private int id;

    private String nome;

    private double valor;

    public Produto(){
        nome = "";
        valor = 0;
    }

    public Produto(int id, double valor, String nome) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int idp) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Produto{" +
                "id = " + id +
                ", nome = " + nome + "\'" +
                "}";
    }
}
