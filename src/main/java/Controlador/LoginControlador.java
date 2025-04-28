package Controlador;

import Modelo.ConexaoBD;
import Modelo.Usuario;

public class LoginControlador {

    public static boolean validarCredenciais(String nomeUsuario, String senha) {
        Usuario usuario = ConexaoBD.autenticar(nomeUsuario, senha);

        if (usuario != null) {
            System.out.println("Login bem-sucedido para: " + usuario.getNomeUsuario());
            return true;
        } else {
            System.out.println("Usuário ou senha incorretos.");
            return false;
        }
    }
}
