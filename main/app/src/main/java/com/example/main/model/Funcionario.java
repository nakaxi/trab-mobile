package com.example.main.model;

public class Funcionario {

    private int idf;

    private String nome;

    private double salario;

    private String telefone;

    private int cargo;

    public Funcionario(){
        super();
    }

    public int getidf() {
        return idf;
    }

    public void setidf(int idf) {
        this.idf = idf;
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

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
}
