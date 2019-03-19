package com.example.joao_bovoloni.aps2;

public class Cliente {
    int id;

    public Cliente(String nome,int id, int quantidades) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
