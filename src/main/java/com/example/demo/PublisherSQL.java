package com.example.demo;

import com.example.demo.PublisherDAO;
import com.example.demo.Connectionn;
import com.example.demo.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherSQL {
    private Connection connection;

    public PublisherSQL() {
        this.connection = new Connectionn().getConnect();
    }

    public ResponseEntity insert(Publisher publisher) {
        if (this.connection != null) {
            try {
                String st = "insert into publisher (puid, name, endereco) values (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(st);

                statement.setInt(1, publisher.getPuid());
                statement.setString(2, publisher.getNome());
                statement.setString(3, publisher.getEndereco());
                statement.execute();
                statement.close();

                return ResponseEntity.ok("publisher created");
            } catch (Exception e) {
                e.getMessage();
            }

            return ResponseEntity.badRequest().body("failed on creating publisher");
        }

        return null;
    }

    public ResponseEntity<List<Publisher>> findAll() {
        if (this.connection != null) {
            try {
                List<Publisher> dados = new ArrayList<>();

                String st1 = "select * from publisher";
                PreparedStatement state = connection.prepareStatement(st1);
                ResultSet result = state.executeQuery();

                while (result.next()) {
                    Publisher publisherModel = new Publisher();
                    publisherModel.setPuid(result.getInt("puid"));
                    publisherModel.setNome(result.getString("name"));
                    publisherModel.setEndereco(result.getString("endereco"));
                    dados.add(publisherModel);
                }
                return ResponseEntity.ok(dados);
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return null;
    }

    public ResponseEntity<Publisher> findById(int id) {
        if (this.connection != null) {
            try {
                Publisher publisherModel = new Publisher();
                PreparedStatement statement = connection.prepareStatement("select * from publisher where puid = " + id);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    publisherModel.setPuid(result.getInt("puid"));
                    publisherModel.setNome(result.getString("name"));
                    publisherModel.setEndereco(result.getString("endereco"));

                    return ResponseEntity.ok(publisherModel);
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }

        return null;
    }

    public ResponseEntity<Publisher> update(Publisher publisher, int id) {
        if (this.connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement("update publisher set endereco = ? where puid = " + id);
                statement.setString(1, publisher.getEndereco());
                statement.execute();

                return ResponseEntity.ok(publisher);
            } catch (SQLException e) {

                e.getMessage();
            }
        }

        return null;
    }

    public void remove(int id) {
        if (this.connection != null) {
            try {
                String stdelete = "delete from publisher where puid = ?";
                PreparedStatement statement = connection.prepareStatement(stdelete);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException u) {

                throw new RuntimeException(u);
            }
        }
    }
}
