package com.example.demo;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTableSQL implements OrderTableDAO {
    private BookSQL livroDAO = new BookSQL();
    private UserSQL usuarioDAO = new UserSQL();
    private Connection connection;

    public OrderTableSQL() {
        this.connection = new Connectionn().getConnect();
    }

    @Override
    public ResponseEntity insert(OrderTable order) {
        if (this.connection != null) {
            try {
                String st = "insert into ordertable (orid, date, fkusid , endereco, pagamento) values (?, ?, ?, ?, ?)";
                PreparedStatement state = connection.prepareStatement(st, PreparedStatement.RETURN_GENERATED_KEYS);

                User usuario = order.getUsuario();

                state.setInt(1, order.getId());
                state.setString(2, order.getDate());
                state.setInt(3, usuario.getUsid());
                state.setString(4, order.getEndereco());
                state.setString(5, order.getPagamento());

                state.executeUpdate();
                ResultSet sell = state.getGeneratedKeys();

                sell.next();

                int orderid = sell.getInt(1);
                PreparedStatement statement = this.connection.prepareStatement("insert into orderitens (fkorid, fkboid, qtde) values (?, ?, ?)");

                for (OrderItem i : order.getItemPedido()) {
                    Book book = i.getLivro();
                    if (book != null) {
                        statement.setInt(1, orderid);
                        statement.setLong(2, book.getBoid());
                        statement.setInt(3, i.getQuantidade());
                        statement.executeUpdate();

                        Book book1;
                        book1 = livroDAO.findById(book.getBoid());
                        livroDAO.update(book1, book.getBoid());
                    }
                }

                return ResponseEntity.ok("created");

            } catch (Exception e) {
                e.getMessage();
            }
        }

        return null;
    }


    @Override
    public OrderTable findById(int id) {
        OrderTable order = new OrderTable();

        if (this.connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement("select * from ordertable where orid = " + id);
                PreparedStatement state2 = connection.prepareStatement("select * from orderitens where fkorid = " + id);

                ResultSet rsPedido = statement.executeQuery();

                if (rsPedido.next()) {
                    User user = usuarioDAO.findById(rsPedido.getInt("fkusid"));

                    order.getId();
                    order.setDate(rsPedido.getString("date"));
                    order.getUsuario();
                    order.setEndereco(rsPedido.getString("endereco"));
                    order.setPagamento(rsPedido.getString("pagamento"));

                    ArrayList<OrderItem> itens = new ArrayList<>();

                    ResultSet rsItens = state2.executeQuery();
                    while (rsItens.next()) {
                        Book livro = livroDAO.findById(rsItens.getInt("fkboid"));
                        if (livro != null) {
                            OrderItem item = new OrderItem();
                            item.setId(rsItens.getInt("fkorid"));
                            item.setLivro(livro);
                            item.setQuantidade(rsItens.getInt("qtde"));
                            itens.add(item);
                        }
                    }
                    order.setItemPedido(itens);

                }
            } catch (Exception e) {

                e.getMessage();
            }
        }

        return order;
    }

    @Override
    public List<OrderTable> findAll() {
        ArrayList<OrderTable> pedidos = new ArrayList<>();
        if (this.connection != null) {
            try {
                String st1 = "select * from ordertable";
                String st2 = "select * from orderitens";
                PreparedStatement statement = connection.prepareStatement(st1);
                PreparedStatement state2 = connection.prepareStatement(st2);

                ResultSet rsPedido = statement.executeQuery();

                while (rsPedido.next()) {
                    User usuario = usuarioDAO.findById(rsPedido.getInt("fkusid"));

                    OrderTable order = new OrderTable();
                    order.getId();
                    order.setDate(rsPedido.getString("date"));
                    order.getUsuario();
                    order.setEndereco(rsPedido.getString("endereco"));
                    order.setPagamento(rsPedido.getString("pagamento"));

                    ArrayList<OrderItem> itens = new ArrayList<>();

                    ResultSet result = state2.executeQuery();

                    while (result.next()) {
                        Book livro = livroDAO.findById(result.getInt("fkboid"));

                        if (livro != null) {
                            OrderItem item = new OrderItem();
                            item.setId(result.getInt("fkorid"));
                            item.setLivro(livro);
                            item.setQuantidade(result.getInt("qtde"));
                            itens.add(item);
                        }
                    }

                    order.setItemPedido(itens);
                    pedidos.add(order);

                }
            } catch (Exception e) {

                e.getMessage();
            }
        }

        return pedidos;
    }

    @Override
    public void update(OrderTable order, int id) {
        if (this.connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement("update ordertable set date = ?, fkusid = ?, endereco = ?, pagamento = ? where orid = " + id);
                User usuario = order.getUsuario();

                statement.setString(1, order.getDate());
                statement.setInt(2, usuario.getUsid());
                statement.setString(3, order.getEndereco());
                statement.setString(4, order.getPagamento());

                statement.executeUpdate();

                PreparedStatement stmt2 = this.connection.prepareStatement("delete from orderitens where fkorid = ?");
                stmt2.setInt(1, id);
                stmt2.executeUpdate();

                PreparedStatement statement5 = this.connection.prepareStatement("insert into orderitens (fkorid, fkboid, qtde) values (?, ?, ?)");

                for (OrderItem item : order.getItemPedido()) {
                    Book livro = item.getLivro();

                    if (livro != null) {
                        statement5.setInt(1, order.getId());
                        statement5.setLong(2, livro.getBoid());
                        statement5.setInt(3, item.getQuantidade());
                        statement5.executeUpdate();
                    }
                }

            } catch (Exception e) {
                e.getMessage();
            }
        }

    }

    @Override
    public void remove(int id) {
        if (this.connection != null) {
            try {
                PreparedStatement statement5 = connection.prepareStatement("delete from ordertable where orid = ?");
                statement5.setInt(1, id);
                statement5.executeUpdate();
            } catch (SQLException u) {
                throw new RuntimeException(u);
            }
        }

    }
}