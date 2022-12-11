package com.example.demo;

public class OrderItem {
    private int id;
    private Book book;
    private int qtde;

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public int getQtde() {
        return this.qtde;
    }
    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
