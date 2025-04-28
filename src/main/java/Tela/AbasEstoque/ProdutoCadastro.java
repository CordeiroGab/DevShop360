package Tela.AbasEstoque;

import Modelo.Produto;
import Controlador.ProdutoDAO;
import Tela.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class ProdutoCadastro extends JFrame {


    public ProdutoCadastro() {

        super("PRODUTO");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(){

            public void paintComponent (Graphics g){
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        //Aba para colocar os dados do produto
        JPanel localCadastre = new JPanel(){
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());

            }
        };
        localCadastre.setOpaque(false);
        localCadastre.setBounds(600, 0, 936,792);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/estoque.png")));
        JLabel label = new JLabel(icon);
        label.setBounds(150,200,200,200);
        panel.add(label);

        JLabel text = new JLabel("CADASTRO");
        text.setOpaque(false);
        text.setBounds(178,330,200,200);
        text.setFont(new Font("Serif", Font.BOLD, 30));
        text.setForeground(Color.black);
        add(text);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 10, 100, 30);
        voltarButton.setFocusable(false);
        voltarButton.addActionListener((ActionEvent e) -> {
            dispose();
            new Estoque(); // Certifique-se de que a classe TelaInicial existe
        });
        panel.add(voltarButton, JLayeredPane.MODAL_LAYER);

        //LOCAL PARA ARMAZENAR OS DADOS DOS PRODUTOS
        JTextField textID = new JTextField("ID");
        textID.setBounds(100, 100, 500, 60);
        textID.setFont(new Font("Arial", Font.BOLD, 15));
        textID.setForeground(Color.GRAY);
        textID.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textID.getText().equals("ID")) {
                    textID.setText("");
                    textID.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (textID.getText().isEmpty()) {
                    textID.setText("ID");
                    textID.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        localCadastre.add(textID);

        JTextField textNome = new JTextField("NOME");
        textNome.setBounds(100, 200, 500, 60);
        textNome.setFont(new Font("Arial", Font.BOLD, 15));
        textNome.setForeground(Color.GRAY);
        localCadastre.add(textNome);
        textNome.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textNome.getText().equals("NOME")) {
                    textNome.setText("");
                    textNome.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (textNome.getText().isEmpty()) {
                    textNome.setText("NOME");
                    textNome.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        localCadastre.add(textNome);

        JTextField textDescricao = new JTextField("DESCRIÇÃO");
        textDescricao.setBounds(100, 300, 500, 60);
        textDescricao.setFont(new Font("Arial", Font.BOLD, 15));
        textDescricao.setForeground(Color.GRAY);
        localCadastre.add(textDescricao);
        textDescricao.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textDescricao.getText().equals("DESCRIÇÃO")) {
                    textDescricao.setText("");
                    textDescricao.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (textDescricao.getText().isEmpty()) {
                    textDescricao.setText("DESCRIÇÃO");
                    textDescricao.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        localCadastre.add(textDescricao);

        JTextField textPreco = new JTextField("PREÇO");
        textPreco.setBounds(100, 400, 500, 60);
        textPreco.setFont(new Font("Arial", Font.BOLD, 15));
        textPreco.setForeground(Color.GRAY);
        localCadastre.add(textPreco);
        textPreco.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textPreco.getText().equals("PREÇO")) {
                    textPreco.setText("");
                    textPreco.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (textPreco.getText().isEmpty()) {
                    textPreco.setText("PREÇO");
                    textPreco.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        localCadastre.add(textPreco);

        JTextField textQuantidade = new JTextField("QUANTIDADE");
        textQuantidade.setBounds(100, 500, 500, 60);
        textQuantidade.setFont(new Font("Arial", Font.BOLD, 15));
        textQuantidade.setForeground(Color.GRAY);
        localCadastre.add(textQuantidade);
        textQuantidade.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textQuantidade.getText().equals("QUANTIDADE")) {
                    textQuantidade.setText("");
                    textQuantidade.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (textQuantidade.getText().isEmpty()) {
                    textQuantidade.setText("QUANTIDADE");
                    textQuantidade.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        localCadastre.add(textQuantidade);

        JTextField textFornecedor = new JTextField("FORNECEDOR");
        textFornecedor.setBounds(100, 600, 500, 60);
        textFornecedor.setFont(new Font("Arial", Font.BOLD, 15));
        textFornecedor.setForeground(Color.GRAY);
        localCadastre.add(textFornecedor);
        textFornecedor.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textFornecedor.getText().equals("FORNECEDOR")) {
                    textFornecedor.setText("");
                    textFornecedor.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (textFornecedor.getText().isEmpty()) {
                    textFornecedor.setText("FORNECEDOR");
                    textFornecedor.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        localCadastre.add(textFornecedor);

        JButton botaoCadastrar = new JButton("CADASTRAR PRODUTO");
        botaoCadastrar.setBounds(100, 700, 500, 60);
        botaoCadastrar.setFont(new Font("Arial", Font.BOLD, 15));
        botaoCadastrar.setForeground(Color.GRAY);
        localCadastre.add(botaoCadastrar);
        botaoCadastrar.addActionListener((ActionEvent e) -> {
            try {
                // Pegando os valores dos campos
                String nome = textNome.getText().trim();
                String descricao = textDescricao.getText().trim();
                String precoText = textPreco.getText().trim();
                String quantidadeText = textQuantidade.getText().trim();
                String fornecedorText = textFornecedor.getText().trim();

                // 1. Verificar se Nome e Descrição estão preenchidos
                if (nome.equals("NOME") || nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome do produto é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (descricao.equals("DESCRIÇÃO") || descricao.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "A descrição do produto é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 2. Verificar se o preço e a quantidade são válidos
                double preco = -1;
                int quantidade = -1;

                try {
                    if (precoText.equals("PREÇO") || precoText.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "O preço é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    preco = Double.parseDouble(precoText);
                    if (preco <= 0) {
                        JOptionPane.showMessageDialog(this, "O preço deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Preço inválido, por favor insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (quantidadeText.equals("QUANTIDADE") || quantidadeText.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "A quantidade é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    quantidade = Integer.parseInt(quantidadeText);
                    if (quantidade < 0) {
                        JOptionPane.showMessageDialog(this, "A quantidade não pode ser negativa!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida, por favor insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 3. Verificar se o ID do fornecedor é válido
                int idFornecedor = -1;
                try {
                    if (fornecedorText.equals("FORNECEDOR") || fornecedorText.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "O ID do fornecedor é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    idFornecedor = Integer.parseInt(fornecedorText);
                    if (!fornecedorExiste(idFornecedor)) {
                        JOptionPane.showMessageDialog(this, "Fornecedor com ID " + idFornecedor + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID do Fornecedor inválido, por favor insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 4. Se todos os campos estiverem corretos, cadastrar o produto
                Produto produto = new Produto(0, nome, descricao, preco, quantidade, idFornecedor);
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.adicionarProduto(produto);

                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                dispose();
                new Estoque();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.setLayout(null);
        localCadastre.setLayout(null);
        add(panel);
        panel.add(localCadastre);
        setVisible(true);
    }

    private boolean fornecedorExiste(int idFornecedor) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.fornecedorExiste(idFornecedor);
    }

    public static void main(String[] args) {
        new ProdutoCadastro();
    }
}

