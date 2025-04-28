package Tela.AbasEstoque;

import Controlador.ProdutoDAO;
import Modelo.Produto;
import Tela.Estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;



public class AbaSaida extends JFrame {

    public AbaSaida(Produto produto) {
        super("DevShop360 - Saida");
        setSize(400,400);


        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                ImageIcon fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel label = new JLabel("Produto: " + produto.getNome());
        label.setBounds(100, 20, 200, 30);

        JTextField saidaProdu = new JTextField("QUANTIDADE");
        saidaProdu.setBounds(105,85,200,40);
        saidaProdu.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (saidaProdu.getText().equals("QUANTIDADE")){
                    saidaProdu.setText("");
                    saidaProdu.setForeground(Color.BLACK);
                }

            }
        });

        JTextField descricao = new JTextField("MOTIVO");
        descricao.setBounds(105,150,200,40);
        descricao.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (descricao.getText().equals("MOTIVO")){
                    descricao.setText("");
                    descricao.setForeground(Color.BLACK);
                }

            }
        });

        JButton botaoSiada = new JButton("RETIRAR");
        botaoSiada.setBounds(118,230,170,40);
        botaoSiada.addActionListener(e -> {
            try {
                int quantidade = Integer.parseInt(saidaProdu.getText().trim());
                String justificativa = descricao.getText().trim();

                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (justificativa.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O motivo é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ProdutoDAO produtoDAO = new ProdutoDAO();
                // Supondo que o ID do Funcionário seja 1 (você pode modificar para pegar da sessão ou login do usuário)
                int idFuncionario = 1;
                // Atualiza o estoque e registra a movimentação de saída
                produtoDAO.atualizarEstoque(produto.getIdProduto(), quantidade, false);
                produtoDAO.registrarMovimentacao(produto.getIdProduto(), "Saída", quantidade, idFuncionario, justificativa);

                JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!");
                dispose();
                new Estoque();  // Atualiza a tabela após a saída
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao registrar saída: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label);
        panel.add(botaoSiada);
        panel.add(descricao);
        panel.add(saidaProdu);
        setLocationRelativeTo(null);
        panel.setLayout(null);
        setResizable(false);
        add(panel);
        setVisible(true);

    }
    public static void main(String[] args) {
        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto", 20.0, 50, 1);
        new AbaSaida(produto);
    }
}
