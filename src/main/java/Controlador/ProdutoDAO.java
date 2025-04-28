package Controlador;

import Modelo.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/devshop360";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO Produto (Nome, Descricao, Preco, Quantidade, ID_Fornecedor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getIdFornecedor());

            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar o produto: " + e.getMessage());
        }
    }

    public void editarProduto(Produto produto) {
        String sql = "UPDATE Produto SET Nome = ?, Descricao = ?, Preco = ?, Quantidade = ?, ID_Fornecedor = ? WHERE ID_Produto = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());  // Setando os parâmetros do produto
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getIdFornecedor());  // Definindo o ID do fornecedor
            stmt.setInt(6, produto.getIdProduto());  // O ID do produto, usado para buscar qual produto será editado

            int rowsAffected = stmt.executeUpdate();  // Executa o comando de atualização no banco de dados

            if (rowsAffected == 0) {
                System.err.println("Erro: Produto não encontrado.");
                throw new RuntimeException("Produto não encontrado.");
            }

            System.out.println("Produto editado com sucesso!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: Fornecedor especificado não existe.");
            throw new RuntimeException("Fornecedor inválido.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao editar o produto: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.ID_Produto, p.Nome, p.Descricao, p.Preco, p.Quantidade, f.Nome AS Fornecedor, p.ID_Fornecedor " +
                "FROM Produto p " +
                "JOIN Fornecedor f ON p.ID_Fornecedor = f.ID_Fornecedor";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("ID_Produto"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getDouble("Preco"),
                        rs.getInt("Quantidade"),
                        rs.getInt("ID_Fornecedor"), // Aqui pega o ID do fornecedor
                        rs.getString("Fornecedor") // Aqui pega o nome do fornecedor
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método de busca pelo ID
    public Produto buscarProdutoPorId(int idProduto) {
        String sql = "SELECT p.ID_Produto, p.Nome, p.Descricao, p.Preco, p.Quantidade, p.ID_Fornecedor, f.Nome AS Nome_Fornecedor " +
                "FROM Produto p " +
                "JOIN Fornecedor f ON p.ID_Fornecedor = f.ID_Fornecedor " +
                "WHERE p.ID_Produto = ?";
        Produto produto = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(
                            rs.getInt("ID_Produto"),
                            rs.getString("Nome"),
                            rs.getString("Descricao"),
                            rs.getDouble("Preco"),
                            rs.getInt("Quantidade"),
                            rs.getInt("ID_Fornecedor") // Apenas o ID do fornecedor
                    );
                    // Agora, setamos o nome do fornecedor
                    produto.setNomeFornecedor(rs.getString("Nome_Fornecedor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o produto: " + e.getMessage());
        }

        return produto;
    }

    public List<Produto> buscarProdutoPorNome(String nome) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.ID_Produto, p.Nome, p.Descricao, p.Preco, p.Quantidade, p.ID_Fornecedor, f.Nome AS Nome_Fornecedor " +
                "FROM Produto p " +
                "JOIN Fornecedor f ON p.ID_Fornecedor = f.ID_Fornecedor " +
                "WHERE p.Nome LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("ID_Produto"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getDouble("Preco"),
                        rs.getInt("Quantidade"),
                        rs.getInt("ID_Fornecedor") // Apenas o ID do fornecedor
                );
                // Setando o nome do fornecedor após a criação do produto
                produto.setNomeFornecedor(rs.getString("Nome_Fornecedor"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public String obterNomeFornecedorPorId(int idFornecedor) {
        String sql = "SELECT Nome FROM Fornecedor WHERE ID_Fornecedor = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);  // Passa o ID do fornecedor como parâmetro
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Nome");  // Retorna o nome do fornecedor
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";  // Retorna uma string vazia se não encontrar o fornecedor
    }

    public boolean fornecedorExiste(int idFornecedor) {
        String sql = "SELECT COUNT(*) FROM Fornecedor WHERE ID_Fornecedor = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna verdadeiro se o fornecedor existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna falso se ocorreu algum erro ou fornecedor não encontrado
    }

    public void atualizarEstoque(int idProduto, int quantidade, boolean isEntrada) {
        String sql = "UPDATE Produto SET Quantidade = Quantidade " + (isEntrada ? "+" : "-") + " ? WHERE ID_Produto = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Produto não encontrado.");
            }
            System.out.println("Estoque atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar o estoque: " + e.getMessage());
        }
    }

    public void registrarMovimentacao(int idProduto, String tipo, int quantidade, int idFuncionario, String justificativa) {
        String sql = "INSERT INTO Movimentacao_Estoque (ID_Produto, Tipo, Quantidade, Data, ID_Funcionario, Justificativa) " +
                "VALUES (?, ?, ?, NOW(), ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            stmt.setString(2, tipo);  // Tipo será "Entrada" ou "Saída"
            stmt.setInt(3, quantidade);
            stmt.setInt(4, idFuncionario);
            stmt.setString(5, justificativa);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao registrar movimentação: " + e.getMessage());
        }
    }
}
