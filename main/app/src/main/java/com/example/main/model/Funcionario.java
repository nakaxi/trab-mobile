package com.example.main.model;

import androidx.annotation.NonNull;

public class Funcionario {

    private int id;

    private String nome;

    private double salario;

    private String telefone;

    private String cargo;

    public Funcionario() {
        nome = "";
        salario = 0.0;
        telefone = "";
        cargo = "";
    }

    public Funcionario(String cargo, String telefone, double salario, String nome, int id) {
        this.cargo = cargo;
        this.telefone = telefone;
        this.salario = salario;
        this.nome = nome;
        this.id = id;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Funcionario{" +
                "id='" + id + '\'' +
                ", nome=" + nome +
                '}';
    }
}
