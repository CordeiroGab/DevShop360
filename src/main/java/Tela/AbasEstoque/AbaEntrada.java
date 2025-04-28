package Tela.AbasEstoque;

import Controlador.ProdutoDAO;
import Modelo.Produto;
import Tela.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class AbaEntrada extends JFrame {

    public AbaEntrada(Produto produto) {
        super("DevShop360 - ENTRADA");
        ;
        setSize(400,400);

        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                ImageIcon fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel label = new JLabel("Produto: " + produto.getNome());
        label.setBounds(100, 50, 200, 30);

        JTextField entradaProduto = new JTextField("QUANTIDADE");
        entradaProduto.setBounds(105,85,200,40);
        entradaProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
               if (entradaProduto.getText().equals("QUANTIDADE")){
                   entradaProduto.setText("");
                   entradaProduto.setForeground(Color.BLACK);
               }

            }
        });

        JButton adicProdu = new JButton("ADICIONAR");
        adicProdu.setBounds(118,170,170,40);
        adicProdu.addActionListener(e -> {
            try {
                int quantidade = Integer.parseInt(entradaProduto.getText().trim());
                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ProdutoDAO produtoDAO = new ProdutoDAO();
                // Supondo que o ID do Funcionário seja 1 (você pode modificar para pegar da sessão ou login do usuário)
                int idFuncionario = 1;
                // Atualiza o estoque e registra a movimentação de entrada
                produtoDAO.atualizarEstoque(produto.getIdProduto(), quantidade, true);
                produtoDAO.registrarMovimentacao(produto.getIdProduto(), "Entrada", quantidade, idFuncionario, "Entrada no estoque");

                JOptionPane.showMessageDialog(this, "Entrada registrada com sucesso!");
                dispose();
                new Estoque();  // Atualiza a tabela após a entrada
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar entrada: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label);
        panel.add(entradaProduto);
        panel.add(adicProdu);
        setLocationRelativeTo(null);
        panel.setLayout(null);
        setResizable(false);
        add(panel);
        setVisible(true);

    }
    public static void main(String[] args) {

        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto", 20.0, 50, 1);
        new AbaEntrada(produto);
    }
}
