package Tela.AbaVenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class registrarVenda extends JFrame {
    public registrarVenda() {
        super("REGISTRAR VENDA");
        setResizable(false);
        setSize(1200, 800);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JPanel localProduto = new JPanel();
        localProduto.setLayout(null);
        localProduto.setBounds(700, 0, 490, 800);
        localProduto.setOpaque(true); // Torna o fundo visível
        localProduto.setBackground(Color.white); // Adiciona uma cor de fundo

        JLabel text = new JLabel("REGISTRO DE VENDAS");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setForeground(Color.white);
        text.setBounds(220,0,300,80);
        panel.add(text);
        //LOCAL PARA ARMAZENAR OS DADOS DOS PRODUTOS
        JTextField idProduto = new JTextField("ID");
        idProduto.setBounds(100, 160, 500, 60);
        idProduto.setFont(new Font("Arial", Font.BOLD, 15));
        idProduto.setForeground(Color.GRAY);
        idProduto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (idProduto.getText().equals("ID")) {
                    idProduto.setText("");
                    idProduto.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (idProduto.getText().isEmpty()) {
                    idProduto.setText("ID");
                    idProduto.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(idProduto);


        JTextField quantidade = new JTextField("QUANTIDADE");
        quantidade.setBounds(100, 240, 500, 60);
        quantidade.setFont(new Font("Arial", Font.BOLD, 15));
        quantidade.setForeground(Color.GRAY);
        quantidade.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (quantidade.getText().equals("QUANTIDADE")) {
                    quantidade.setText("");
                    quantidade.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (quantidade.getText().isEmpty()) {
                    quantidade.setText("QUANTIDADE");
                    quantidade.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(quantidade);

        JTextField valorPago = new JTextField("VALOR PAGO");
        valorPago.setBounds(100, 320, 500, 60);
        valorPago.setFont(new Font("Arial", Font.BOLD, 15));
        valorPago.setForeground(Color.GRAY);
        valorPago.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (valorPago.getText().equals("VALOR PAGO")) {
                    valorPago.setText("");
                    valorPago.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (valorPago.getText().isEmpty()) {
                    valorPago.setText("VALOR PAGO");
                    valorPago.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(valorPago);

        JTextField cliente = new JTextField("CLIENTE");
        cliente.setBounds(100, 400, 500, 60);
        cliente.setFont(new Font("Arial", Font.BOLD, 15));
        cliente.setForeground(Color.GRAY);
        cliente.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (cliente.getText().equals("CLIENTE")) {
                    cliente.setText("");
                    cliente.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (cliente.getText().isEmpty()) {
                    cliente.setText("CLIENTE");
                    cliente.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });

        panel.add(cliente);

        JButton limpar = new JButton("LIMPAR");
        limpar.setBounds(30,600,150,50);
        limpar.setFont(new Font("Arial", Font.BOLD, 15));
        limpar.setForeground(Color.GRAY);
        panel.add(limpar);

        JButton adicionar = new JButton("ADICIONAR");
        adicionar.setBounds(200,600,150,50);
        adicionar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionar.setForeground(Color.GRAY);
        panel.add(adicionar);


        JButton vender = new JButton("VENDER");
        vender.setBounds(370,600,150,50);
        vender.setFont(new Font("Arial", Font.BOLD, 15));
        vender.setForeground(Color.GRAY);
        panel.add(vender);

        JButton excluir = new JButton("EXCLUIR");
        excluir.setBounds(540,600,150,50);
        excluir.setFont(new Font("Arial", Font.BOLD, 15));
        excluir.setForeground(Color.GRAY);
        panel.add(excluir);

        JLabel valorTotal = new JLabel("Valor Total: ");
        valorTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
        valorTotal.setForeground(Color.white);
        valorTotal.setBounds(100,480,300,80);
        panel.add(valorTotal);

        panel.add(cliente);

        panel.setLayout(null);
        panel.add(localProduto);

        add(panel);
        setVisible(true); // Tornar a janela visível deve ser o último passo
    }

    public static void main(String[] args) {
        new registrarVenda();
    }
}
