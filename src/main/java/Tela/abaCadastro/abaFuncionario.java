package Tela.abaCadastro;

import Modelo.Funcionario;
import Controlador.FuncionarioDAO;
import Tela.Cadastro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.util.Objects;

public class abaFuncionario extends JFrame {
    private Cadastro cadastroTela;

    public abaFuncionario(Cadastro cadastro) {
        super("CADASTRO DE FUNCIONARIO");
        this.cadastroTela = cadastro; // Referência à tela principal
        setResizable(false);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Painel com fundo personalizado
        JPanel panel = new CustomPanel();
        panel.setLayout(null);

        // Campos de entrada
        JTextField nome = criarCampoTexto("NOME", 100, 80);
        JTextField cargo = criarCampoTexto("CARGO", 100, 160);
        JTextField email = criarCampoTexto("EMAIL", 100, 240);
        JTextField admissao = criarCampoTexto("ADMISSÃO (AAAA-MM-DD)", 100, 320);

        // Botão de cadastrar
        JButton adicionar = new JButton("CADASTRAR");
        adicionar.setBounds(190, 480, 150, 50);
        adicionar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionar.setForeground(Color.GRAY);
        adicionar.addActionListener(e -> cadastrarFuncionario(nome, cargo, email, admissao));

        // Botão limpar
        JButton limpar = new JButton("LIMPAR");
        limpar.setBounds(370, 480, 150, 50);
        limpar.setFont(new Font("Arial", Font.BOLD, 15));
        limpar.setForeground(Color.GRAY);
        limpar.addActionListener(e -> limparCampos(nome, cargo, email, admissao));

        // Adicionar botões e campos ao painel
        panel.add(adicionar);
        panel.add(limpar);
        panel.add(nome);
        panel.add(cargo);
        panel.add(email);
        panel.add(admissao);

        setResizable(false);
        add(panel);
        setVisible(true);
    }

    // Custom JPanel para fundo
    class CustomPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Método para criar um campo de texto com foco nas funcionalidades de limpar e preencher o campo
    private JTextField criarCampoTexto(String placeholder, int x, int y) {
        JTextField textField = new JTextField(placeholder);
        textField.setBounds(x, y, 500, 50);
        textField.setFont(new Font("Tahoma", Font.BOLD, 12));
        textField.setForeground(Color.BLACK);
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.BLACK);
                }
            }
        });
        return textField;
    }

    // Método para limpar os campos de entrada
    private void limparCampos(JTextField nome, JTextField cargo, JTextField email, JTextField admissao) {
        nome.setText("NOME");
        cargo.setText("CARGO");
        email.setText("EMAIL");
        admissao.setText("ADMISSÃO (AAAA-MM-DD)");
    }

    // Método para cadastrar o funcionário
    private void cadastrarFuncionario(JTextField nome, JTextField cargo, JTextField email, JTextField admissao) {
        try {
            // Validação: Certificar-se de que os campos não estão vazios
            if (nome.getText().trim().isEmpty() || cargo.getText().trim().isEmpty() ||
                    email.getText().trim().isEmpty() || admissao.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertendo a data de admissão
            Date dataAdmissao = Date.valueOf(admissao.getText()); // Formato de data "AAAA-MM-DD"

            // Criar um objeto Funcionario
            Funcionario novoFuncionario = new Funcionario(nome.getText(), cargo.getText(), email.getText(), dataAdmissao);

            // Inserir no banco de dados
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.adicionarFuncionario(novoFuncionario);

            // Atualizar a tabela de funcionários na tela principal
            cadastroTela.carregarFuncionarios();

            // Mostrar mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");

            // Limpar os campos após o cadastro
            limparCampos(nome, cargo, email, admissao);

            // Fechar a janela de cadastro
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
