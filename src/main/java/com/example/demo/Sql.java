package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Sql {

    public static void main(String[] argv) throws SQLException {
        // Connection Configuration
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "");
        //connConfig.setProperty("useSsl", "true");
        //connConfig.setProperty("serverSslCert", "/path/to/ca-bundle.pem");

        // Create Connection to MariaDB Enterprise
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", connConfig)) {

            // Disable Auto-Commit
            conn.setAutoCommit(false);

            // user
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.user ("
                                + "usid INT PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(50))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // publisher
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.publisher ("
                                + "puid INT PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(50),"
                                + "endereco VARCHAR(50))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // author
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.author ("
                                + "auid INT PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(50))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // book
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.books ("
                                + "boid INT PRIMARY KEY AUTO_INCREMENT,"
                                + "titulo VARCHAR(50),"
                                + "author VARCHAR(50),"
                                + "email VARCHAR(100),"
                                + "fkpuid INT NOT NULL,"
                                + "fkauid INT NOT NULL,"
                                + "CONSTRAINT FK_puid FOREIGN KEY (fkpuid)"
                                + " REFERENCES biblioteca.publisher(puid),"
                                + "CONSTRAINT FK_auid FOREIGN KEY (fkauid)"
                                + " REFERENCES biblioteca.author(auid))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // account
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.account ("
                                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                                + "email VARCHAR(50),"
                                + "password VARCHAR(30))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // shippinginfo
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.shippinginfo ("
                                + "shipper VARCHAR(50),"
                                + "endereco VARCHAR(50))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // billinginfo
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.billinginfo ("
                                + "email VARCHAR(50),"
                                + "pagamento VARCHAR(50))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // order
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.ordertable ("
                                + "orid INT PRIMARY KEY AUTO_INCREMENT,"
                                + "date VARCHAR(20),"
                                + "fkusid INT NOT NULL,"
                                + "endereco VARCHAR(50),"
                                + "pagamento VARCHAR(50),"
                                + "CONSTRAINT FK_usid FOREIGN KEY (fkusid)"
                                + " REFERENCES biblioteca.user(usid))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // order itens
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS biblioteca.orderitens ("
                                + "fkorid INT NOT NULL," //id da venda
                                + "fkboid INT NOT NULL," //id do livro
                                + "qtde INT,"
                                + "CONSTRAINT FK_orid FOREIGN KEY (fkorid)"
                                + " REFERENCES biblioteca.ordertable(orid),"
                                + "CONSTRAINT FK_boid FOREIGN KEY (fkboid)"
                                + " REFERENCES biblioteca.books(boid))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}