package com.example.joao_bovoloni.aps2;

public class Cliente {
    int id;
    int quantidades;
String nome;
    public Cliente(String nome,int id, int quantidades) {
        this.id = id;
        this.nome = nome;
        this.quantidades = quantidades;
    }

    public int getId() {
        return id;
    }

    public int getQuantidades() {
        return quantidades;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantidades(int quantidades) {
        this.quantidades = quantidades;
    }
}
