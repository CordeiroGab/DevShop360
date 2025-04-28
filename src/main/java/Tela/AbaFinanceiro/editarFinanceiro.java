package Tela.AbaFinanceiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class editarFinanceiro extends JFrame {

    public editarFinanceiro() {

        super("FINANCEIRO-EDITAR");
        setResizable(false);
        setSize(700, 600);
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

        JTextField codigo = new JTextField("CÓDIGO");
        codigo.setBounds(100, 80, 500, 60);
        codigo.setFont(new Font("Arial", Font.BOLD, 15));
        codigo.setForeground(Color.GRAY);
        codigo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (codigo.getText().equals("CÓDIGO")) {
                    codigo.setText("");
                    codigo.setForeground(Color.BLACK);  // Texto normalcliente
                }
            }

            public void focusLost(FocusEvent e) {
                if (codigo.getText().isEmpty()) {
                    codigo.setText("CÓDIGO");
                    codigo.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(codigo);


        //LOCAL PARA ARMAZENAR OS DADOS DOS PRODUTOS
        JTextField descricao = new JTextField("DESCRIÇÃO");
        descricao.setBounds(100, 160, 500, 60);
        descricao.setFont(new Font("Arial", Font.BOLD, 15));
        descricao.setForeground(Color.GRAY);
        descricao.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (descricao.getText().equals("DESCRIÇÃO")) {
                    descricao.setText("");
                    descricao.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (descricao.getText().isEmpty()) {
                    descricao.setText("DESCRIÇÃO");
                    descricao.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(descricao);


        JTextField data = new JTextField("DATA");
        data.setBounds(100, 240, 500, 60);
        data.setFont(new Font("Arial", Font.BOLD, 15));
        data.setForeground(Color.GRAY);
        data.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (data.getText().equals("DATA")) {
                    data.setText("");
                    data.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (data.getText().isEmpty()) {
                    data.setText("DATA");
                    data.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(data);

        JTextField entrada = new JTextField("VALOR");
        entrada.setBounds(100, 320, 500, 60);
        entrada.setFont(new Font("Arial", Font.BOLD, 15));
        entrada.setForeground(Color.GRAY);
        entrada.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (entrada.getText().equals("VALOR")) {
                    entrada.setText("");
                    entrada.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (entrada.getText().isEmpty()) {
                    entrada.setText("VALOR");
                    entrada.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });
        panel.add(entrada);


        JButton adicionar = new JButton("SALVAR");
        adicionar.setBounds(190,450,150,50);
        adicionar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionar.setForeground(Color.GRAY);
        panel.add(adicionar);


        JButton vender = new JButton("LIMPAR");
        vender.setBounds(370,450,150,50);
        vender.setFont(new Font("Arial", Font.BOLD, 15));
        vender.setForeground(Color.GRAY);
        panel.add(vender);




        panel.setLayout(null);

        add(panel);
        setVisible(true); // Tornar a janela visível deve ser o último passo
    }

    public static void main(String[] args) {
        new editarFinanceiro();
    }


}


