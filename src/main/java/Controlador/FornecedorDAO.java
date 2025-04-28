package Controlador;

import Modelo.ConexaoBD;
import Modelo.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    private final Connection connection;

    public FornecedorDAO() throws Exception {
        this.connection = ConexaoBD.getConnection();  // Método para obter a conexão com o BD
    }

    // Método para listar todos os fornecedores
    public List<Fornecedor> listarFornecedores() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        resultSet.getInt("ID_Fornecedor"),
                        resultSet.getString("Nome"),
                        resultSet.getString("Endereco"),
                        resultSet.getString("Telefone"),
                        resultSet.getString("Email")
                );
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedores;
    }

    public void adicionarFornecedor(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO Fornecedor (Nome, Endereco, Telefone, Email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fornecedor.getNome());
            preparedStatement.setString(2, fornecedor.getEndereco());
            preparedStatement.setString(3, fornecedor.getTelefone());
            preparedStatement.setString(4, fornecedor.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um cliente
    public void excluirFornecedor(int idFornecedor) {
        String excluirProdutos = "DELETE FROM Produto WHERE ID_Fornecedor = ?";
        String excluirFornecedor = "DELETE FROM Fornecedor WHERE ID_Fornecedor = ?";

        try (Connection conn = ConexaoBD.getConnection()) {
            // Excluir os produtos associados ao fornecedor
            try (PreparedStatement stmtProdutos = conn.prepareStatement(excluirProdutos)) {
                stmtProdutos.setInt(1, idFornecedor);
                stmtProdutos.executeUpdate();
            }

            // Excluir o fornecedor
            try (PreparedStatement stmtFornecedor = conn.prepareStatement(excluirFornecedor)) {
                stmtFornecedor.setInt(1, idFornecedor);
                int rowsAffected = stmtFornecedor.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Fornecedor excluído com sucesso.");
                } else {
                    System.out.println("Fornecedor não encontrado.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir fornecedor: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
