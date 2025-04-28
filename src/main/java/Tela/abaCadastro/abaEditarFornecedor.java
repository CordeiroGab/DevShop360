package Tela.abaCadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class abaEditarFornecedor extends JFrame {

    public abaEditarFornecedor() {

        super("EDITAR-FORNECEDOR");
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

        JTextField endereco = new JTextField("ENDEREÇO");
        endereco.setBounds(100, 160, 500, 50);
        endereco.setFont(new Font("Tahoma", Font.BOLD, 12));
        endereco.setForeground(Color.BLACK);
        endereco.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(endereco.getText().equals("ENDEREÇO")){
                    endereco.setText("");
                    endereco.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(endereco.getText().isEmpty()){
                    endereco.setText("ENDEREÇO");
                    endereco.setForeground(Color.BLACK);
                }
            }
        });

        JTextField telefone = new JTextField("TELEFONE");
        telefone.setBounds(100, 240, 500, 50);
        telefone.setFont(new Font("Tahoma", Font.BOLD, 12));
        telefone.setForeground(Color.BLACK);
        telefone.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(telefone.getText().equals("TELEFONE")){
                    telefone.setText("");
                    telefone.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(telefone.getText().isEmpty()){
                    telefone.setText("EMAIl");
                    telefone.setForeground(Color.BLACK);
                }
            }
        });


        JTextField email = new JTextField("EMAIL");
        email.setBounds(100, 320, 500, 50);
        email.setFont(new Font("Tahoma", Font.BOLD, 12));
        email.setForeground(Color.BLACK);
        email.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(email.getText().equals("EMAIL")){
                    email.setText("");
                    email.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(email.getText().isEmpty()){
                    email.setText("EMAIL");
                    email.setForeground(Color.BLACK);
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
        panel.add(endereco);
        panel.add(nome);
        panel.add(telefone);
        panel.add(email);

        setResizable(false);
        add(panel);
        setVisible(true);

    }
    public static void main(String[] args) {
        new abaEditarFornecedor();
    }
}
