package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class Book {
    @JsonIgnoreProperties
    private int boid;
    private String titulo;
    private String author;
    private String email;
    private int fkpuid;
    private int fkauid;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBoid() {
        return this.boid;
    }

    public void setBoid(int boid) {
        this.boid = boid;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFkpuid() {
        return this.fkpuid;
    }

    public void setFkauid(int fkauid) {
        this.fkauid = fkauid;
    }

    public int getFkauid() {
        return fkauid;
    }

    public void setFkpuid(int fkpuid) {
        this.fkpuid = fkpuid;
    }
}
