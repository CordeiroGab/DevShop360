package Tela.abaCadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class abaEditarFuncionario extends JFrame {

    public abaEditarFuncionario() {

        super("EDITAR-FUNCIONARIO");
        setResizable(false);
        setSize(700,600);
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

        JTextField nome = new JTextField("NOME");
        nome.setBounds(100, 80, 500, 50);
        nome.setFont(new Font("Tahoma", Font.BOLD, 12));
        nome.setForeground(Color.BLACK);
        nome.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(nome.getText().equals("NOME")){
                    nome.setText("");
                    nome.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if(nome.getText().isEmpty()){
                    nome.setText("NOME");
                    nome.setForeground(Color.BLACK);
                }
            }

        });

        JTextField cargo = new JTextField("Cargo");
        cargo.setBounds(100, 160, 500, 50);
        cargo.setFont(new Font("Tahoma", Font.BOLD, 12));
        cargo.setForeground(Color.BLACK);
        cargo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(cargo.getText().equals("Cargo")){
                    cargo.setText("");
                    cargo.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(cargo.getText().isEmpty()){
                    cargo.setText("Cargo");
                    cargo.setForeground(Color.BLACK);
                }
            }
        });

        JTextField emaIl = new JTextField("EMAIl");
        emaIl.setBounds(100, 240, 500, 50);
        emaIl.setFont(new Font("Tahoma", Font.BOLD, 12));
        emaIl.setForeground(Color.BLACK);
        emaIl.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(emaIl.getText().equals("EMAIl")){
                    emaIl.setText("");
                    emaIl.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(emaIl.getText().isEmpty()){
                    emaIl.setText("EMAIl");
                    emaIl.setForeground(Color.BLACK);
                }
            }
        });


        JTextField admissao = new JTextField("ADMISSÂO");
        admissao.setBounds(100, 320, 500, 50);
        admissao.setFont(new Font("Tahoma", Font.BOLD, 12));
        admissao.setForeground(Color.BLACK);
        admissao.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(admissao.getText().equals("ADMISSÂO")){
                    admissao.setText("");
                    admissao.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(admissao.getText().isEmpty()){
                    admissao.setText("ADMISSÂO");
                    admissao.setForeground(Color.BLACK);
                }
            }
        });




        JButton adicionar = new JButton("SALVAR");
        adicionar.setBounds(190,480,150,50);
        adicionar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionar.setForeground(Color.GRAY);



        JButton limpar = new JButton("LIMPAR");
        limpar.setBounds(370,480,150,50);
        limpar.setFont(new Font("Arial", Font.BOLD, 15));
        limpar.setForeground(Color.GRAY);


        //botões salvar e limoar
        panel.add(adicionar);
        panel.add(limpar);


        //entrada de dados do cliente
        panel.add(cargo);
        panel.add(nome);
        panel.add(emaIl);
        panel.add(admissao);

        setResizable(false);
        add(panel);
        setVisible(true);

    }
    public static void main(String[] args) {
        new abaEditarFuncionario();
    }
}
