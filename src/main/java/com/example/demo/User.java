package com.example.demo;

public class User {
    private int usid;
    private String nome;

    public User(){}

    public User(int id, String nome){
        this.usid = id;
        this.nome = nome;
    }

    public int getUsid() {
        return this.usid;
    }

    public void setUsid(int usid) {
        this.usid = usid;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
