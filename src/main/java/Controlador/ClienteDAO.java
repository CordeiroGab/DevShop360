package Controlador;

import Modelo.Cliente;
import Modelo.ConexaoBD;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final Connection connection;

    public ClienteDAO() throws Exception {
        this.connection = ConexaoBD.getConnection();  // Método para obter a conexão com o BD
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("ID_Cliente"),
                        rs.getString("Nome"),
                        rs.getString("Endereco"),
                        rs.getString("Telefone"),
                        rs.getString("Email"),
                        rs.getString("CPF")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public void adicionarCliente(Cliente cliente) throws SQLException {
        // Verificar se o CPF ou o Email já existem
        if (verificarCPFExistente(cliente.getCpf())) {
            JOptionPane.showMessageDialog(null, "O CPF informado já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Não prosseguir com a inserção
        }

        if (verificarEmailExistente(cliente.getEmail())) {
            JOptionPane.showMessageDialog(null, "O Email informado já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Não prosseguir com a inserção
        }

        // Inserir no banco de dados
        String sql = "INSERT INTO Cliente (Nome, CPF, Endereco, Telefone, Email) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um cliente
    public void excluirCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE ID_Cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);  // Definir o ID do cliente a ser excluído
            stmt.executeUpdate();  // Executa a exclusão
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir cliente.");
        }
    }

    // Verificar se o CPF já está cadastrado
    public boolean verificarCPFExistente(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE CPF = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o CPF já existir
            }
        }
        return false;
    }

    // Verificar se o Email já está cadastrado
    public boolean verificarEmailExistente(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE Email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o Email já existir
            }
        }
        return false;
    }
}
