package Tela.abaCadastro;

import Modelo.Fornecedor;
import Controlador.FornecedorDAO;
import Tela.Cadastro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class abaFornecedor extends JFrame {

    private Cadastro cadastroTela;

    public abaFornecedor(Cadastro cadastro) {
        super("Cadastro de Fornecedor");
        this.cadastroTela = cadastro; // Referência à tela principal
        setResizable(false);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Painel com fundo personalizado
        JPanel panel = new CustomPanel();
        panel.setLayout(null);

        // Campos de entrada
        JTextField nome = criarCampoTexto("NOME", 100, 80);
        JTextField endereco = criarCampoTexto("ENDEREÇO", 100, 160);
        JTextField telefone = criarCampoTexto("TELEFONE", 100, 240);
        JTextField email = criarCampoTexto("EMAIL", 100, 320);

        // Botão de cadastrar
        JButton adicionar = new JButton("CADASTRAR");
        adicionar.setBounds(190, 480, 150, 50);
        adicionar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionar.setForeground(Color.GRAY);
        adicionar.addActionListener(e -> cadastrarFornecedor(nome, endereco, telefone, email));

        // Botão limpar
        JButton limpar = new JButton("LIMPAR");
        limpar.setBounds(370, 480, 150, 50);
        limpar.setFont(new Font("Arial", Font.BOLD, 15));
        limpar.setForeground(Color.GRAY);
        limpar.addActionListener(e -> limparCampos(nome, endereco, telefone, email));

        // Adicionar botões e campos ao painel
        panel.add(adicionar);
        panel.add(limpar);
        panel.add(nome);
        panel.add(endereco);
        panel.add(telefone);
        panel.add(email);

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
    private void limparCampos(JTextField nome, JTextField endereco, JTextField telefone, JTextField email) {
        nome.setText("NOME");
        endereco.setText("ENDEREÇO");
        telefone.setText("TELEFONE");
        email.setText("EMAIL");
    }

    // Método para cadastrar o fornecedor
    private void cadastrarFornecedor(JTextField nome, JTextField endereco, JTextField telefone, JTextField email) {
        try {
            // Validação: Certificar-se de que os campos não estão vazios
            if (nome.getText().trim().isEmpty() || endereco.getText().trim().isEmpty() ||
                    telefone.getText().trim().isEmpty() || email.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criar um objeto Fornecedor
            Fornecedor novoFornecedor = new Fornecedor(nome.getText(), endereco.getText(), telefone.getText(), email.getText());

            // Inserir no banco de dados
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.adicionarFornecedor(novoFornecedor);

            // Atualizar a tabela de fornecedores na tela principal
            cadastroTela.carregarFornecedores();

            // Mostrar mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!");

            // Fechar a janela de cadastro
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar fornecedor: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
