package com.example.demo;

import java.util.List;

public class OrderTable {
    private int id;
    private String date;
    private User usuario;
    private String endereco;
    private String pagamento;
    private List<OrderItem> itemPedido;

    public User getUsuario() {
        return usuario;
    }

    public List<OrderItem> getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(List<OrderItem> itemPedido) {
        this.itemPedido = itemPedido;
    }

    public OrderTable(){}
    public OrderTable(int id, String date, User usuario, String endereco, String pagamento){
        this.id = id;
        this.date = date;
        this.usuario = usuario;
        this.endereco = endereco;
        this.pagamento = pagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsuario(User cliente) {
        this.usuario = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
