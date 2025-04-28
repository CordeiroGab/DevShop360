package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import Controlador.LoginControlador;

public class Login extends JFrame {

    public Login() {
        setTitle("DevShop360 - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Tamanho fixo da janela
        setLocationRelativeTo(null);  // Centraliza a janela
        setResizable(true);  // Permite redimensionar a janela

        JPanel fundo = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/FundoLogin.jpg")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this); // Preenche o painel com a imagem
            }
        };

        fundo.setLayout(null);  // Usando layout absoluto para controle total de posição

        // Campos de entrada de usuário
        JTextField usuario = new JTextField("Digite seu usuário");
        usuario.setBounds(650, 350, 270, 45);
        usuario.setFont(new Font("Arial", Font.BOLD, 15));
        usuario.setForeground(Color.GRAY);  // Cor da dica

        // Campo de senha
        JPasswordField senha = new JPasswordField("Senha");
        senha.setBounds(650, 420, 270, 45);
        senha.setFont(new Font("Arial", Font.BOLD, 15));
        senha.setForeground(Color.GRAY);  // Cor da dica

        // Botão de Login
        JButton botaoLogin = new JButton("Login");
        botaoLogin.setBounds(650, 550, 270, 45);
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 20));
        botaoLogin.setForeground(Color.BLUE);

        // Adiciona foco para remover a dica (placeholder)
        usuario.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (usuario.getText().equals("Digite seu usuário")) {
                    usuario.setText("");
                    usuario.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (usuario.getText().isEmpty()) {
                    usuario.setText("Digite seu usuário");
                    usuario.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });

        senha.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(senha.getPassword()).equals("Senha")) {
                    senha.setText("");
                    senha.setEchoChar('*');  // Máscara de senha
                    senha.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(senha.getPassword()).isEmpty()) {
                    senha.setText("Senha");
                    senha.setEchoChar((char) 0);  // Remove a máscara
                    senha.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });

        // Evento do botão
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeUsuario = usuario.getText();
                String senhaTexto = String.valueOf(senha.getPassword());

                if (LoginControlador.validarCredenciais(nomeUsuario, senhaTexto)) {
                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
                    TelaInicial telaInicial = new TelaInicial();
                    telaInicial.setVisible(true);
                    dispose();  // Fecha a tela de login
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                }
            }
        });

        // Adiciona os componentes ao fundo
        fundo.add(usuario);
        fundo.add(senha);
        fundo.add(botaoLogin);

        // Atualiza a interface gráfica
        fundo.revalidate();
        fundo.repaint();

        // Adiciona o fundo ao JFrame
        add(fundo);
        setVisible(true);

        // Garantir que o foco inicial esteja no botão de login
        // Foco no botão de login ao iniciar
        SwingUtilities.invokeLater(botaoLogin::requestFocusInWindow);
    }

    public static void main(String[] args) {
        // Cria a janela de login
        SwingUtilities.invokeLater(Login::new);
    }
}