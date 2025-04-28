package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Relatorio extends JFrame {

    public Relatorio(){

        setTitle("DevShop360 - Relatório");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        JPanel panel = new JPanel(){

            public void paintComponent(Graphics g){
                super.paintComponent(g);

                ImageIcon fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        add(panel);

        //Criando o JButton para voltar à tela inicial
        JButton voltarButton = getJButton();

        // Adicionando o botão ao painel
        panel.setLayout(null); // Desabilita o layout do painel para o botão ser posicionado manualmente
        panel.add(voltarButton);

        // Tornar visível
        setVisible(true);
    }

    private JButton getJButton() {
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 10, 100, 30); // Posicionando o botão no canto superior esquerdo
        voltarButton.setFocusable(false);

        // Adicionando ActionListener para o botão
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a tela de cadastro
                dispose();
                // Mostrar a tela principal TelaInicial
                new TelaInicial(); // Certifique-se de ter uma classe Main com a interface inicial
            }
        });
        return voltarButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Relatorio::new);
    }
}
