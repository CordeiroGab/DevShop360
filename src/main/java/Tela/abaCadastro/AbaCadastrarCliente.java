package Tela.abaCadastro;

import Modelo.Cliente;
import Controlador.ClienteDAO;
import Tela.Cadastro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class AbaCadastrarCliente extends JFrame {
    private Cadastro cadastroTela;

    public AbaCadastrarCliente(Cadastro cadastro) {
        super("CADASTRO DE CLIENTE");
        this.cadastroTela = cadastro; // Referência à tela principal
        setResizable(false);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Painel com fundo personalizado
        JPanel panel = new CustomPanel();
        panel.setLayout(null);

        // Campos de entrada
        JTextField nome = criarCampoTexto("NOME", 100, 80);
        JTextField endereco = criarCampoTexto("ENDEREÇO", 100, 240);
        JTextField telefone = criarCampoTexto("TELEFONE", 100, 320);
        JTextField email = criarCampoTexto("EMAIL", 100, 400);
        JTextField cpf = criarCampoTexto("CPF", 100, 160);

        // Botão de cadastrar
        JButton adicionar = new JButton("CADASTRAR");
        adicionar.setBounds(190, 480, 150, 50);
        adicionar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionar.setForeground(Color.GRAY);
        adicionar.addActionListener(e -> cadastrarCliente(nome, endereco, telefone, email, cpf));

        // Botão limpar
        JButton limpar = new JButton("LIMPAR");
        limpar.setBounds(370, 480, 150, 50);
        limpar.setFont(new Font("Arial", Font.BOLD, 15));
        limpar.setForeground(Color.GRAY);
        limpar.addActionListener(e -> limparCampos(nome, endereco, telefone, email, cpf));

        // Adicionar botões e campos ao painel
        panel.add(adicionar);
        panel.add(limpar);
        panel.add(nome);
        panel.add(endereco);
        panel.add(telefone);
        panel.add(email);
        panel.add(cpf);

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
    private void limparCampos(JTextField nome, JTextField endereco, JTextField telefone, JTextField email, JTextField cpf) {
        nome.setText("NOME");
        endereco.setText("ENDEREÇO");
        telefone.setText("TELEFONE");
        email.setText("EMAIL");
        cpf.setText("CPF");
    }

    // Método para cadastrar o cliente
    private void cadastrarCliente(JTextField nome, JTextField endereco, JTextField telefone, JTextField email, JTextField cpf) {
        try {
            // Validação: Certificar-se de que os campos não estão vazios
            if (nome.getText().trim().isEmpty() || cpf.getText().trim().isEmpty() || endereco.getText().trim().isEmpty() ||
                    telefone.getText().trim().isEmpty() || email.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação de CPF (exemplo simples)
            if (!validarCPF(cpf.getText().trim())) {
                JOptionPane.showMessageDialog(this, "CPF inválido. Por favor, insira um CPF válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação de email
            if (!validarEmail(email.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Email inválido. Por favor, insira um email válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar se o CPF e o Email já estão cadastrados
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.verificarCPFExistente(cpf.getText())) {
                JOptionPane.showMessageDialog(this, "CPF já cadastrado. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (clienteDAO.verificarEmailExistente(email.getText())) {
                JOptionPane.showMessageDialog(this, "Email já cadastrado. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criar um objeto Cliente
            Cliente novoCliente = new Cliente(nome.getText(), endereco.getText(), telefone.getText(), email.getText(), cpf.getText());

            // Inserir no banco de dados
            clienteDAO.adicionarCliente(novoCliente);

            // Atualizar a tabela de clientes na tela principal
            cadastroTela.carregarClientes();

            // Mostrar mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

            // Limpar os campos após o cadastro
            limparCampos(nome, endereco,telefone, email, cpf);

            // Fechar a janela de cadastro
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para validar o CPF
    private boolean validarCPF(String cpf) {
        String regex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";
        return cpf.matches(regex);
    }

    // Método para validar o email
    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    public static void main(String[] args) throws Exception {
        new AbaCadastrarCliente(new Cadastro());
    }
}
