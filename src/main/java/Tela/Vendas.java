package Tela;

import Tela.AbaVenda.estorMotivo;
import Tela.AbaVenda.registrarVenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Vendas extends JFrame {

    public Vendas() {

        setTitle("DevShop360 - Vendas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);  // Centraliza a janela
        setResizable(true);  // Permite redimensionar a janela

        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                ImageIcon fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        add(panel);



        JPanel panelVendas = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelVendas.setLayout(null);
        panelVendas.setBounds(250, 200, 1260, 560);

        JButton emitirNF = new JButton("EMITIR NF");
        emitirNF.setBounds(35, 280, 180, 50);
        emitirNF.setFocusable(false);
        emitirNF.addActionListener((ActionEvent e) -> {
            dispose();
            new TelaInicial(); // Certifique-se de que a classe TelaInicial existe
        });
        panel.add(emitirNF);

        JButton botaoRegistre = new JButton("REGISTRAR");
        botaoRegistre.setBounds(35, 380, 180, 50);
        botaoRegistre.setFocusable(false);
        botaoRegistre.addActionListener((ActionEvent e) -> {

            new registrarVenda(); // Certifique-se de que a classe TelaInicial existe
        });
        panel.add(botaoRegistre);

        JButton botaoEstornar = new JButton("ESTORNAR");
        botaoEstornar.setBounds(35, 480, 180, 50);
        botaoEstornar.setFocusable(false);
        botaoEstornar.addActionListener((ActionEvent e) -> {

            new estorMotivo(); // Certifique-se de que a classe TelaInicial existe
        });
        panel.add(botaoEstornar);

        JTextField campoBusca = new JTextField();
        campoBusca.setFont(new Font("Tahoma", Font.BOLD, 15));
        campoBusca.setBounds(450, 50, 300, 33);
        panel.add(campoBusca);




        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(12, 10, 100, 30);
        voltarButton.setFocusable(false);
        voltarButton.addActionListener((ActionEvent e) -> {
            dispose();
            new TelaInicial(); // Certifique-se de que a classe TelaInicial existe
        });
        panel.add(voltarButton, JLayeredPane.MODAL_LAYER);


        // Adicionando o botão ao painel
        panel.setLayout(null); // Desabilita o layout do painel para o botão ser posicionado manualmente
        panel.add(voltarButton);
        panel.add(panelVendas);

        // Tornar visível
        setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Vendas::new);
    }
}
