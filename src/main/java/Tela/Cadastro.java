package Tela;

import Controlador.ClienteDAO;
import Controlador.FornecedorDAO;
import Controlador.FuncionarioDAO;
import Modelo.Cliente;
import Modelo.Fornecedor;
import Modelo.Funcionario;
import Tela.abaCadastro.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class Cadastro extends JFrame {

    private DefaultTableModel modelCliente;
    private DefaultTableModel modelFornecedor;
    private DefaultTableModel modelFuncionario;

    private JTable tabelaCliente;
    private JTable tabelaFornecedor;
    private JTable tabelaFuncionario;

    public Cadastro() throws Exception {
        setTitle("DevShop360 - Cadastro");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(true); // Permite redimensionar a janela

        modelCliente = new DefaultTableModel(new Object[]{"ID", "Nome", "Endereco", "Telefone", "Email", "CPF"}, 0);
        modelFornecedor = new DefaultTableModel(new String[]{"ID", "Nome", "Endereço", "Telefone", "Email"}, 0);
        modelFuncionario = new DefaultTableModel(new String[]{"ID", "Nome", "Cargo", "Email", "Data de admissão"}, 0);

        tabelaCliente = new JTable(modelCliente);
        tabelaFornecedor = new JTable(modelFornecedor);
        tabelaFuncionario = new JTable(modelFuncionario);

        // Painel principal
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Painel para tabela de clientes
        JPanel telaCliente = criarPainelTabela(tabelaCliente, 100, 130);
        JScrollPane scrollCliente = new JScrollPane(tabelaCliente);
        scrollCliente.setBounds(0, 0, 350, 450);
        telaCliente.add(scrollCliente);

        // Painel para tabela de fornecedores
        JPanel telaFornecedor = criarPainelTabela(tabelaFornecedor, 600, 130);
        JScrollPane scrollFornecedor = new JScrollPane(tabelaFornecedor);
        scrollFornecedor.setBounds(0, 0, 350, 450);
        telaFornecedor.add(scrollFornecedor);

        // Painel para tabela de funcionários
        JPanel telaFuncionario = criarPainelTabela(tabelaFuncionario, 1100, 130);
        JScrollPane scrollFuncionario = new JScrollPane(tabelaFuncionario);
        scrollFuncionario.setBounds(0, 0, 350, 450);
        telaFuncionario.add(scrollFuncionario);

        // Botão "Voltar" no canto superior esquerdo
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 10, 100, 30);
        voltarButton.setFocusable(false);
        voltarButton.addActionListener((ActionEvent e) -> {
            dispose();
            new TelaInicial(); // Certifique-se de que a classe TelaInicial existe
        });
        panel.add(voltarButton, JLayeredPane.MODAL_LAYER);

        // Botão "Cadastrar Cliente"
        JButton cadastrarClienteButton = criarBotao("CLIENTE", 120, 630, e -> new AbaCadastrarCliente(this));
        JButton editarClienteButton = criarBotao("EDITAR", 120, 700, e -> JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento!"));

        // Botão "Cadastrar Fornecedor"
        JButton cadastrarFornecedorButton = criarBotao("FORNECEDOR", 620, 630, e -> new abaFornecedor(this));
        JButton editarFornecedorButton = criarBotao("EDITAR", 620, 700, e -> JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento!"));

        // Botão "Cadastrar Funcionario"
        JButton cadastrarFuncionarioButton = criarBotao("FUNCIONÁRIO", 1105, 630, e -> new abaFuncionario(this));
        JButton editarFuncionarioButton = criarBotao("EDITAR", 1105, 700, e -> JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento!"));

        // Botão "Excluir"
        ImageIcon iconeExcluir = new ImageIcon(Objects.requireNonNull(getClass().getResource("/excluir.png")));
        JButton Botaoexcluir = new JButton();
        Botaoexcluir.setBounds(745, 5, 60, 60); // Tamanho inicial do botão
        Botaoexcluir.setBackground(new Color(39, 78, 197));
        Botaoexcluir.setBorderPainted(false);
        Botaoexcluir.setFocusable(false);
        Botaoexcluir.setContentAreaFilled(false);

        // Redimensionar ícone e aplicar ao botão
        Botaoexcluir.setIcon(redimensionarIcone(iconeExcluir, Botaoexcluir.getWidth(), Botaoexcluir.getHeight()));

        Botaoexcluir.addActionListener(e -> {
            excluirItem();
        });

        // Adicionar os botões e painéis
        panel.add(voltarButton);
        panel.add(cadastrarClienteButton);
        panel.add(cadastrarFornecedorButton);
        panel.add(cadastrarFuncionarioButton);
        panel.add(editarClienteButton);
        panel.add(editarFornecedorButton);
        panel.add(editarFuncionarioButton);
        panel.add(Botaoexcluir);
        panel.add(telaCliente);
        panel.add(telaFornecedor);
        panel.add(telaFuncionario);

        // Configurações do painel principal
        panel.setLayout(null);
        add(panel);

        // Carregar dados das tabelas
        carregarClientes();
        carregarFornecedores();
        carregarFuncionarios();

        setVisible(true);
    }

    // Método para redimensionar o ícone
    private static ImageIcon redimensionarIcone(ImageIcon icone, int largura, int altura) {
        Image imagem = icone.getImage();
        Image imagemRedimensionada = imagem.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }

    private JPanel criarPainelTabela(JTable tabela, int x, int y) {
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        panel.setBounds(x, y, 350, 450);
        return panel;
    }

    private JButton criarBotao(String texto, int x, int y, java.awt.event.ActionListener actionListener) {
        JButton botao = new JButton(texto);
        botao.setBounds(x, y, 305, 50);
        botao.setFont(new Font("Tahoma", Font.BOLD, 20));
        botao.setFocusable(false);
        botao.addActionListener(actionListener);
        return botao;
    }

    public void carregarClientes() throws Exception {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarClientes();
        modelCliente.setRowCount(0); // Limpar a tabela
        for (Cliente cliente : clientes) {
            modelCliente.addRow(new Object[]{cliente.getIdCliente(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf()});
        }
    }

    // Método para carregar fornecedores na tabela
    public void carregarFornecedores() throws Exception {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedores();
        modelFornecedor.setRowCount(0); // Limpa a tabela
        for (Fornecedor fornecedor : fornecedores) {
            modelFornecedor.addRow(new Object[]{
                    fornecedor.getIdFornecedor(),
                    fornecedor.getNome(),
                    fornecedor.getEndereco(),
                    fornecedor.getTelefone(),
                    fornecedor.getEmail()
            });
        }
    }

    public void carregarFuncionarios() throws Exception {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();

        modelFuncionario.setRowCount(0);  // Limpa a tabela de funcionários

        for (Funcionario funcionario: funcionarios) {
            modelFuncionario.addRow(new Object[]{
                    funcionario.getIdFuncionario(),
                    funcionario.getNome(),
                    funcionario.getCargo(),
                    funcionario.getEmail(),
                    funcionario.getAdmissao()});
        }
    }

    private void excluirItem() {
        try {
            int linhaSelecionada = -1;
            int idItem = -1;
            String tipoTabela = "";  // Variável para saber qual tabela está sendo manipulada

            // Verificar qual tabela está selecionada
            if (tabelaCliente.getSelectedRow() >= 0) {
                linhaSelecionada = tabelaCliente.getSelectedRow();
                idItem = (int) modelCliente.getValueAt(linhaSelecionada, 0); // Pegando o ID do cliente
                tipoTabela = "cliente";
                System.out.println("Tabela Cliente: ID selecionado - " + idItem);  // Depuração
            } else if (tabelaFornecedor.getSelectedRow() >= 0) {
                linhaSelecionada = tabelaFornecedor.getSelectedRow();
                idItem = (int) modelFornecedor.getValueAt(linhaSelecionada, 0); // Pegando o ID do fornecedor
                tipoTabela = "fornecedor";
                System.out.println("Tabela Fornecedor: ID selecionado - " + idItem);  // Depuração
            } else if (tabelaFuncionario.getSelectedRow() >= 0) {
                linhaSelecionada = tabelaFuncionario.getSelectedRow();
                idItem = (int) modelFuncionario.getValueAt(linhaSelecionada, 0); // Pegando o ID do funcionário
                tipoTabela = "funcionario";
                System.out.println("Tabela Funcionario: ID selecionado - " + idItem);  // Depuração
            }

            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um item para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Excluir o item da tabela e do banco de dados com base no tipo da tabela
            switch (tipoTabela) {
                case "cliente":
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.excluirCliente(idItem);
                    carregarClientes();  // Atualiza a tabela de clientes
                    break;
                case "fornecedor":
                    FornecedorDAO fornecedorDAO = new FornecedorDAO();
                    fornecedorDAO.excluirFornecedor(idItem);
                    carregarFornecedores();  // Atualiza a tabela de fornecedores
                    break;
                case "funcionario":
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    funcionarioDAO.excluirFuncionario(idItem);
                    carregarFuncionarios();  // Atualiza a tabela de funcionários
                    break;
            }

            JOptionPane.showMessageDialog(this, tipoTabela.substring(0, 1).toUpperCase() + tipoTabela.substring(1) + " excluído com sucesso!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Cadastro();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao iniciar a aplicação: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}
