package com.example.demo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Connectionn {
    Connection conn = null;

    private Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("conn:" + conn);
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca?user=root&password=");
            if (conn != null) {
                System.out.println("foi");
                System.out.println("conexão:" + conn);
                return conn;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Connection getConnect() {
        if(conn==null) {
            conn= connect();
        }
        return conn;
    }

}
