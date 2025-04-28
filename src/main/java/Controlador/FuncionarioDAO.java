package Controlador;

import Modelo.ConexaoBD;
import Modelo.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private final Connection connection;

    public FuncionarioDAO() throws Exception {
        this.connection = ConexaoBD.getConnection();  // Método para obter a conexão com o BD
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario(
                        resultSet.getInt("ID_Funcionario"),
                        resultSet.getString("Nome"),
                        resultSet.getString("Cargo"),
                        resultSet.getString("Email"),
                        resultSet.getDate("Data_Admissao")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    // Método para adicionar um novo funcionário
    public void adicionarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (Nome, Cargo, Email, Data_Admissao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setString(3, funcionario.getEmail());
            preparedStatement.setDate(4, new java.sql.Date(funcionario.getAdmissao().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void excluirFuncionario(int idFuncionario) {
        String excluirFuncionario = "DELETE FROM Funcionario WHERE ID_Funcionario = ?";

        try (Connection conn = ConexaoBD.getConnection()) {

            try (PreparedStatement stmtFuncionario = conn.prepareStatement(excluirFuncionario)) {
                stmtFuncionario.setInt(1, idFuncionario);
                int rowsAffected = stmtFuncionario.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Funcionário excluído com sucesso.");
                } else {
                    System.out.println("Funcionário não encontrado.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
