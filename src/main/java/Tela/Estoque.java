package Tela;

import Modelo.Produto;
import Controlador.ProdutoDAO;
import Tela.AbasEstoque.AbaEditar;
import Tela.AbasEstoque.AbaEntrada;
import Tela.AbasEstoque.AbaSaida;
import Tela.AbasEstoque.ProdutoCadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class Estoque extends JFrame {

    private JTable tabelaProdutos;
    private ProdutoDAO produtoDAO;

    public Estoque() {
        super("DevShop360 - Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        produtoDAO = new ProdutoDAO();  // Inicializando o ProdutoDAO para acessar o banco de dados

        // Painel do fundo
        JPanel fundo = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Painel branco centralizado
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        centerPanel.setBounds(30, 100, 1470, 560);
        centerPanel.setOpaque(false);

        // Tabela para exibir os produtos
        String[] colunas = {"ID", "Nome", "Descrição", "Preço", "Quantidade", "Fornecedor"};
        Object[][] dados = {};

        tabelaProdutos = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(30, 100, 1470, 560);
        fundo.add(scrollPane);

        // Botão "Voltar" no canto superior esquerdo
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 10, 100, 30);
        voltarButton.setFocusable(false);
        voltarButton.addActionListener((ActionEvent e) -> {
            dispose();
            new TelaInicial(); // Certifique-se de que a classe TelaInicial existe
        });
        fundo.add(voltarButton, JLayeredPane.MODAL_LAYER);

        // Botões inferiores
        JButton cadastrarButton = new JButton("CADASTRAR");
        cadastrarButton.setBounds(250, 700, 200, 40);
        cadastrarButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        fundo.add(cadastrarButton);
        cadastrarButton.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(ProdutoCadastro::new);
        });

        JButton editarButton = new JButton("EDITAR");
        editarButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        editarButton.setBounds(500, 700, 200, 40);
        editarButton.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();  // Pega a linha selecionada
            if (linhaSelecionada != -1) {  // Verifica se uma linha foi selecionada
                Produto produto = produtoDAO.buscarProdutoPorId((Integer) tabelaProdutos.getValueAt(linhaSelecionada, 0));  // Buscar o produto pelo ID
                if (produto != null) {
                    new AbaEditar(produto);  // Passa o produto para a tela de edição
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para editar", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        fundo.add(editarButton);

        JButton entradaButton = new JButton("ENTRADA");
        entradaButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        entradaButton.setBounds(750, 700, 200, 40);
        entradaButton.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();  // Pega a linha selecionada
            if (linhaSelecionada != -1) {  // Verifica se uma linha foi selecionada
                Produto produto = produtoDAO.buscarProdutoPorId((Integer) tabelaProdutos.getValueAt(linhaSelecionada, 0));  // Buscar o produto pelo ID
                if (produto != null) {
                    new AbaEntrada(produto);  // Passa o produto para a tela de entrada
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para registrar entrada", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        fundo.add(entradaButton);

        JButton saidaButton = new JButton("SAÍDA");
        saidaButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        saidaButton.setBounds(1000, 700, 200, 40);
        saidaButton.addActionListener((ActionEvent e) -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();  // Pega a linha selecionada
            if (linhaSelecionada != -1) {  // Verifica se uma linha foi selecionada
                Produto produto = produtoDAO.buscarProdutoPorId((Integer) tabelaProdutos.getValueAt(linhaSelecionada, 0));  // Buscar o produto pelo ID
                if (produto != null) {
                    new AbaSaida(produto);  // Passa o produto para a tela de saída
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para registrar saída", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        fundo.add(saidaButton);

        // Campo de busca
        JTextField campoBusca = new JTextField();
        campoBusca.setFont(new Font("Tahoma", Font.BOLD, 15));
        campoBusca.setBounds(450, 50, 300, 33);
        campoBusca.addActionListener((ActionEvent e) -> {
            buscarProduto(campoBusca.getText());
        });
        fundo.add(campoBusca);

        // Adicionar o JLayeredPane ao JFrame
        fundo.setLayout(null);
        add(fundo);
        preencherTabela();
        setVisible(true); // Tornar a janela visível

        // Preencher a tabela ao carregar
    }

    // Metodo para preencher a tabela com os dados dos produtos
    private void preencherTabela() {
        List<Produto> produtos = produtoDAO.listarProdutos();
        Object[][] dados = new Object[produtos.size()][6];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getIdProduto();
            dados[i][1] = produto.getNome();
            dados[i][2] = produto.getDescricao();
            dados[i][3] = produto.getPreco();
            dados[i][4] = produto.getQuantidade();
            dados[i][5] = produto.getIdFornecedor(); // Exibindo o ID do fornecedor, não o nome
        }

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(dados, new String[] {
                "ID", "Nome", "Descrição", "Preço", "Quantidade", "ID Fornecedor" // Alterado para ID Fornecedor
        }));
    }

    // Método de busca
    private void buscarProduto(String nomeProduto) {
        List<Produto> produtos = produtoDAO.buscarProdutoPorNome(nomeProduto);
        Object[][] dados = new Object[produtos.size()][6];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getIdProduto();
            dados[i][1] = produto.getNome();
            dados[i][2] = produto.getDescricao();
            dados[i][3] = produto.getPreco();
            dados[i][4] = produto.getQuantidade();
            dados[i][5] = produto.getIdFornecedor();  // Agora exibindo o ID do fornecedor
        }

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(dados, new String[] {
                "ID", "Nome", "Descrição", "Preço", "Quantidade", "ID Fornecedor"  // Alterado para ID Fornecedor
        }));
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(Estoque::new);
    }
}
