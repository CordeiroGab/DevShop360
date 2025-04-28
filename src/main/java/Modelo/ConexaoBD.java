package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoBD {

    private static final String URL = "jdbc:mysql://localhost:3306/devshop360";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static Usuario autenticar(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM Usuario WHERE Nome_Usuario = ? AND Senha = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Cria um objeto Usuario com os dados recuperados
                return new Usuario(
                        rs.getInt("ID_Usuario"),
                        rs.getString("Nome_Usuario"),
                        rs.getString("Senha"),
                        rs.getInt("ID_Perfil")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testarConexao() {
        try (Connection conn = getConnection()) {
            System.out.println("Conexão bem-sucedida com o banco de dados.");
            ResultSet rs = conn.getMetaData().getTables(null, null, "%", null);
            System.out.println("Tabelas disponíveis no banco:");
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
