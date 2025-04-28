package Tela.AbasEstoque;

import Modelo.Produto;
import Controlador.ProdutoDAO;
import Tela.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class AbaEditar extends JFrame {

    private Produto produto;  // Recebe o produto a ser editado

    public AbaEditar(Produto produto) {
        super("EDITAR PRODUTO");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.produto = produto;  // Atribui o produto recebido

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Aba para Editar os dados do produto
        JPanel localEditar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        localEditar.setOpaque(false);
        localEditar.setBounds(600, 0, 936, 792);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 10, 100, 30);
        voltarButton.setFocusable(false);
        voltarButton.addActionListener((ActionEvent e) -> {
            dispose();
            new Estoque();  // Retorna para a tela de estoque
        });
        panel.add(voltarButton, JLayeredPane.MODAL_LAYER);

        // Preenche os campos com os dados do produto
        JTextField textID = new JTextField(String.valueOf(produto.getIdProduto()));  // Preenche o campo ID
        textID.setBounds(100, 100, 500, 60);
        textID.setFont(new Font("Arial", Font.BOLD, 15));
        textID.setForeground(Color.GRAY);
        textID.setEditable(false);  // O ID não será editável
        localEditar.add(textID);

        JTextField textNome = new JTextField(produto.getNome());
        textNome.setBounds(100, 200, 500, 60);
        textNome.setFont(new Font("Arial", Font.BOLD, 15));
        textNome.setForeground(Color.GRAY);
        localEditar.add(textNome);

        JTextField textDescricao = new JTextField(produto.getDescricao());
        textDescricao.setBounds(100, 300, 500, 60);
        textDescricao.setFont(new Font("Arial", Font.BOLD, 15));
        textDescricao.setForeground(Color.GRAY);
        localEditar.add(textDescricao);

        JTextField textPreco = new JTextField(String.valueOf(produto.getPreco()));
        textPreco.setBounds(100, 400, 500, 60);
        textPreco.setFont(new Font("Arial", Font.BOLD, 15));
        textPreco.setForeground(Color.GRAY);
        localEditar.add(textPreco);

        JTextField textQuantidade = new JTextField(String.valueOf(produto.getQuantidade()));
        textQuantidade.setBounds(100, 500, 500, 60);
        textQuantidade.setFont(new Font("Arial", Font.BOLD, 15));
        textQuantidade.setForeground(Color.GRAY);
        localEditar.add(textQuantidade);

        JTextField textFornecedor = new JTextField(String.valueOf(produto.getIdFornecedor()));
        textFornecedor.setBounds(100, 600, 500, 60);
        textFornecedor.setFont(new Font("Arial", Font.BOLD, 15));
        textFornecedor.setForeground(Color.GRAY);
        localEditar.add(textFornecedor);

        JButton botaoEditar = new JButton("EDITAR PRODUTO");
        botaoEditar.setBounds(100, 700, 500, 60);
        botaoEditar.setFont(new Font("Arial", Font.BOLD, 15));
        botaoEditar.setForeground(Color.GRAY);
        localEditar.add(botaoEditar);

        // Quando clicar em "EDITAR PRODUTO"
        botaoEditar.addActionListener((ActionEvent e) -> {
            try {
                // Atualizando os dados do produto
                produto.setNome(textNome.getText().trim());
                produto.setDescricao(textDescricao.getText().trim());
                produto.setPreco(Double.parseDouble(textPreco.getText().trim()));
                produto.setQuantidade(Integer.parseInt(textQuantidade.getText().trim()));
                produto.setIdFornecedor(Integer.parseInt(textFornecedor.getText().trim()));

                // Validação
                if (produto.getNome().isEmpty() || produto.getDescricao().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome e Descrição são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.editarProduto(produto);  // Chamando o método de edição

                JOptionPane.showMessageDialog(this, "Produto editado com sucesso!");
                dispose();
                new Estoque();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos numéricos (Preço, Quantidade, ID Fornecedor).", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao editar o produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.setLayout(null);
        add(panel);
        panel.add(localEditar);
        localEditar.setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto", 20.0, 50, 1, "Fornecedor 1");
        new AbaEditar(produto);
    }
}
