package com.example.demo;

import java.util.List;

public class OrderTable {
    private int orid;
    private String date;
    private User user;
    private String endereco;
    private String pagamento;
    private List<OrderItem> itemPedido;

    public OrderTable(int id, String date, User usuario, String endereco, String pagamento){
        this.orid = id;
        this.date = date;
        this.user = usuario;
        this.endereco = endereco;
        this.pagamento = pagamento;
    }

    public OrderTable() {

    }

    public int getOrid() {
        return this.orid;
    }

    public void setOrid(int orid) {
        this.orid = orid;
    }

    public String getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User cliente) {
        this.user = cliente;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<OrderItem> getItemPedido() {
        return this.itemPedido;
    }

    public void setItemPedido(List<OrderItem> itemPedido) {
        this.itemPedido = itemPedido;
    }
}
