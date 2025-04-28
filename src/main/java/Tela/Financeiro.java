package Tela;

import Tela.AbaFinanceiro.abaAdicionar;
import Tela.AbaFinanceiro.editarFinanceiro;
import Tela.AbaVenda.estorMotivo;
import Tela.AbaVenda.registrarVenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Financeiro extends JFrame {
    public Financeiro() {
        setTitle("DevShop360 - Financeiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = configurarPainel();
        configurarBotaoVoltar(panel);

        add(panel);

        setVisible(true);
    }

    private JPanel configurarPainel() {
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon fundo;
                try {
                    fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                } catch (NullPointerException ex) {
                    System.err.println("Imagem de fundo não encontrada!");
                    fundo = new ImageIcon(); // Imagem vazia como fallback
                }

                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null); // Desabilitar o layout gerenciado
        return panel;
    }


    private void configurarBotaoVoltar(JPanel panel) {
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 10, 100, 30); // Posição inicial do botão
        voltarButton.setFocusable(false);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaInicial(); // Certifique-se de que TelaInicial existe
            }
        });

        JPanel panelFinanceiro = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelFinanceiro.setLayout(null);
        panelFinanceiro.setBounds(150, 200, 1260, 560);

        // Configurando a tabela
        String[] colunas = {"Código", "Descrição", "Data", "Entrada", "Despesa", "Saldo"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0); // Sem linhas inicialmente
        JTable tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 20, 1220, 400);
        panelFinanceiro.add(scrollPane);

        // Adicionando botões
        JButton adicionar = new JButton("ADICIONAR");
        adicionar.setBounds(35, 450, 180, 50);
        adicionar.setFocusable(false);
        adicionar.addActionListener(e -> new abaAdicionar());

        JButton editar = new JButton("EDITAR");
        editar.setBounds(235, 450, 180, 50);
        editar.setFocusable(false);
        editar.addActionListener(e -> new editarFinanceiro());

        JButton encerrar = new JButton("EXCLUIR");
        encerrar.setBounds(435, 450, 180, 50);
        encerrar.setFocusable(false);
        encerrar.addActionListener(e -> new estorMotivo());

        panelFinanceiro.add(adicionar);
        panelFinanceiro.add(editar);
        panelFinanceiro.add(encerrar);

        JTextField campoBusca = new JTextField();
        campoBusca.setFont(new Font("Tahoma", Font.BOLD, 15));
        campoBusca.setBounds(450, 50, 300, 33);
        panel.add(campoBusca);

        JLabel valorDisponivel = new JLabel("Valor Total:");
        valorDisponivel.setOpaque(false);
        valorDisponivel.setBounds(660,370,200,200);
        valorDisponivel.setFont(new Font("Serif", Font.BOLD, 25));
        valorDisponivel.setForeground(Color.black);
        panelFinanceiro.add(valorDisponivel);



        panel.add(panelFinanceiro);
        panel.add(voltarButton);
    }

    public static void main(String[] args) {
        new Financeiro();
    }
}
