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
public class UserSQL implements UserDAO {
    private Connection connection;
    public UserSQL() {
        this.connection = new Connectionn().getConnect();
    }

    public ResponseEntity insert(User usuario) {
        if (this.connection != null) {
            try {
                String st = "insert into user (usid, name) values (?, ?)";
                PreparedStatement statement = connection.prepareStatement(st);

                statement.setInt(1, usuario.getUsid());
                statement.setString(2, usuario.getNome());
                statement.execute();
                statement.close();

                return ResponseEntity.ok("user created");
            } catch (Exception e) {
                e.getMessage();
            }

            return ResponseEntity.badRequest().body("failed on creating user");
        }

        return null;
    }

    public ResponseEntity<List<User>> findAll() {
        if (this.connection != null) {
            try {
                List<User> dados = new ArrayList<>();

                String st = "select * from user";
                PreparedStatement state = connection.prepareStatement(st);
                ResultSet result = state.executeQuery();

                while (result.next()) {
                    User usuarioModel = new User();
                    usuarioModel.setUsid(result.getInt("usid"));
                    usuarioModel.setNome(result.getString("name"));
                    dados.add(usuarioModel);
                }

                return ResponseEntity.ok(dados);
            } catch (SQLException e) {

                e.getMessage();
            }
        }

        return null;
    }

    @Override
    public User findById(int id) {
        if (this.connection != null) {
            try {
                User usuarioModel = new User();
                PreparedStatement statement = connection.prepareStatement("select * from user where usid = " + id);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    usuarioModel.setUsid(result.getInt("usid"));
                    usuarioModel.setNome(result.getString("name"));

                    return usuarioModel;
                }
            } catch (SQLException e) {

                e.getMessage();
            }
        }

        return null;
    }

    public ResponseEntity<User> update(User usuario, int id) {
        if (this.connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement("update user set name = ? where usid = " + id);
                statement.setString(1, usuario.getNome());
                statement.execute();

                return ResponseEntity.ok(usuario);
            } catch (SQLException e) {

                e.getMessage();
            }
        }

        return null;
    }

    public void remove(int id) {
        if (this.connection != null) {
            try {
                String st = "delete from user where usid = ?";
                PreparedStatement statement = connection.prepareStatement(st);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException u) {

                throw new RuntimeException(u);
            }
        }
    }
}
