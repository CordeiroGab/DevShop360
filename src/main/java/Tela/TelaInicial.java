package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TelaInicial extends JFrame {

    public TelaInicial() {

        setTitle("DevShop360 - Tela Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        //Adiconando o tamanho e o background da tela inicial
        JPanel TelaInicial = new JPanel(){

            public void paintComponent(Graphics g){
                super.paintComponent(g);

                ImageIcon Inicial = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));
                g.drawImage(Inicial.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        TelaInicial.setLayout(null);


        //Botao  estoque
        ImageIcon IconeEstoque = new ImageIcon(Objects.requireNonNull(getClass().getResource("/estoque.png")));
        JButton BotaoEstoque = new JButton ();
        BotaoEstoque.setIcon(IconeEstoque);
        BotaoEstoque.setBounds(400,150,183,194);
        BotaoEstoque.setBackground(new Color(39, 78, 197));
        BotaoEstoque.setBorderPainted(false);
        BotaoEstoque.setFocusable(false);
        BotaoEstoque.setContentAreaFilled(false);
        BotaoEstoque.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                dispose();
                new Estoque();
            }
        });

        ImageIcon IconeCadastro = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cadastro.png")));
        JButton BotaoCadastro = new JButton (IconeCadastro);
        BotaoCadastro.setBorderPainted(false);
        BotaoCadastro.setBounds(1000,150,200,200);
        BotaoCadastro.setBackground(new Color(39, 78, 197));
        BotaoCadastro.setFocusable(false);
        BotaoCadastro.setContentAreaFilled(false);

        BotaoCadastro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                try {
                    new Cadastro();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ImageIcon IconeRelatorio = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Relatorio.png")));
        JButton BotaoRelatorio = new JButton (IconeRelatorio);
        BotaoRelatorio.setBounds(700,300,200,200);
        BotaoRelatorio.setBackground(new Color(39, 78, 197));
        BotaoRelatorio.setBorderPainted(false);
        BotaoRelatorio.setFocusable(false);
        BotaoRelatorio.setContentAreaFilled(false);
        BotaoRelatorio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new Relatorio();
            }
        });

        ImageIcon IconeFinanceiero = new ImageIcon(Objects.requireNonNull(getClass().getResource("/financeiro.png")));
        JButton BotaoFinanceiro = new JButton (IconeFinanceiero);
        BotaoFinanceiro.setBounds(1000,450,200,200);
        BotaoFinanceiro.setBackground(new Color(39, 78, 197));
        BotaoFinanceiro.setBorderPainted(false);
        BotaoFinanceiro.setFocusable(false);
        BotaoFinanceiro.setContentAreaFilled(false);
        BotaoFinanceiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new Financeiro();
            }
        });

        //Botao vendas
        ImageIcon IconeVendas = new ImageIcon(Objects.requireNonNull(getClass().getResource("/vendas.png")));
        JButton BotaoVendas = new JButton(IconeVendas);
        BotaoVendas.setBounds(400,450,200,200);
        BotaoVendas.setBackground(new Color(39, 78, 197));
        BotaoVendas.setBorderPainted(false);
        BotaoVendas.setFocusable(false);
        BotaoVendas.setContentAreaFilled(false);
        BotaoVendas.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new Vendas();
            }
        });

        //botoes
        TelaInicial.add(BotaoFinanceiro);
        TelaInicial.add(BotaoRelatorio);
        TelaInicial.add(BotaoVendas);
        TelaInicial.add(BotaoEstoque);
        TelaInicial.add(BotaoCadastro);

        TelaInicial.revalidate();
        TelaInicial.repaint();
        add(TelaInicial);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaInicial::new);
    }
}
