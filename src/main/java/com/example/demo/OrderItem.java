package com.example.demo;

public class OrderItem {
    private int id;
    private Book livro;
    private int quantidade;

    public Book getLivro() {
        return livro;
    }
    public void setLivro(Book livro) {
        this.livro = livro;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
