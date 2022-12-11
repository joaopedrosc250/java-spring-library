package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorSQL implements AuthorDAO {
    private Connection connection;

    public AuthorSQL() {
        this.connection = new Connectionn().getConnect();
    }

    @Override
    public ResponseEntity<Author> findById(int id) {
        if (this.connection != null) {
            try {
                Author author = new Author();
                PreparedStatement statement = connection.prepareStatement("select * from author where auid = " + id);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    author.setAuid(result.getInt("auid"));
                    author.setName(result.getString("name"));

                    return ResponseEntity.ok(author);
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return null;
    }

    @Override
    public ResponseEntity<List<Author>> findAll() {
        if (this.connection != null) {
            try {
                List<Author> dados = new ArrayList<>();

                String st = "select * from author";
                PreparedStatement statement = connection.prepareStatement(st);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    Author authorModel = new Author();
                    authorModel.setAuid(result.getInt("auid"));
                    authorModel.setName(result.getString("name"));
                    dados.add(authorModel);
                }

                return ResponseEntity.ok(dados);
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return null;
    }

    @Override
    public ResponseEntity insert(Author author) {
        if (this.connection != null) {
            try {
                String st1 = "insert into author (auid, name) values (?, ?)";
                PreparedStatement statement = connection.prepareStatement(st1);

                statement.setInt(1, author.getAuid());
                statement.setString(2, author.getName());
                statement.execute();
                statement.close();
                return ResponseEntity.ok("author created");
            } catch (Exception e) {
                e.getMessage();
            }

            return ResponseEntity.badRequest().body("failed on creating author");
        }

        return null;
    }

    @Override
    public ResponseEntity<Author> update(Author author, int id) {
        if (this.connection != null) {
            try {
                PreparedStatement statement =
                        connection.prepareStatement("update author set name = ? where auid = " + id);

                statement.setString(1, author.getName());

                statement.execute();
                return ResponseEntity.ok(author);
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return null;
    }

    @Override
    public void remove(int id) {
        if (this.connection != null) {
            try {
                String st1 = "delete from author where auid = ?";
                PreparedStatement statement = connection.prepareStatement(st1);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException u) {

                throw new RuntimeException(u);
            }
        }
    }
}
