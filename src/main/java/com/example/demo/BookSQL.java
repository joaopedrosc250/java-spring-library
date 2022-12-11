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
public class BookSQL implements BookDAO {
    private Connection connection;

    public BookSQL() {
        this.connection = new Connectionn().getConnect();
    }

    public ResponseEntity<List<Book>> findAll() {
        if (this.connection != null) {
            try {
                List<Book> dados = new ArrayList<>();
                String st = "select * from books";
                PreparedStatement state = connection.prepareStatement(st);
                ResultSet result = state.executeQuery();

                while (result.next()) {
                    Book livroModel = new Book();
                    livroModel.setBoid(result.getInt("boid"));
                    livroModel.setTitulo(result.getString("titulo"));
                    livroModel.setAuthor(result.getString("author"));
                    livroModel.setEmail(result.getString("email"));
                    dados.add(livroModel);
                }

                return ResponseEntity.ok(dados);
            } catch (SQLException e) {

                e.getMessage();
            }
        }

        return null;
    }

    @Override
    public Book findById(int id) {
        if (this.connection != null) {
            try {
                Book livroModel = new Book();
                PreparedStatement state = connection.prepareStatement("select * from books where boid = " + id);
                ResultSet result = state.executeQuery();

                while (result.next()) {
                    livroModel.setBoid(result.getInt("boid"));
                    livroModel.setTitulo(result.getString("titulo"));
                    livroModel.setAuthor(result.getString("author"));
                    livroModel.setEmail(result.getString("email"));

                    return livroModel;
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return null;
    }

    public ResponseEntity insert(Book livro) {
        if (this.connection != null) {
            try {
                String st = "insert into books (boid, titulo, author, email, fkpuid, fkauid) values (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(st);

                statement.setInt(1, livro.getBoid());
                statement.setString(2, livro.getTitulo());
                statement.setString(3, livro.getAuthor());
                statement.setString(4, livro.getEmail());
                statement.setInt(5, livro.getFkpuid());
                statement.setInt(6, livro.getFkauid());
                statement.execute();
                statement.close();

                return ResponseEntity.ok("book created");
            }catch (Exception e) {
                e.getMessage();
            }

            return ResponseEntity.badRequest().body("failed");
        }

        return null;
    }

    public ResponseEntity<Book> update(Book livro, int id) {
        if (this.connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement("update books set titulo = ?, author = ?, email = ? where boid = " + id);

                statement.setString(1, livro.getTitulo());
                statement.setString(2, livro.getAuthor());
                statement.setString(3, livro.getEmail());

                statement.execute();

                return ResponseEntity.ok(livro);
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return null;
    }

    public void remove(int id) {
        if (this.connection != null) {
            try {
                String st1 = "delete from books where boid = ?";
                PreparedStatement statement = connection.prepareStatement(st1);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException u) {

                throw new RuntimeException(u);
            }
        }
    }
}
