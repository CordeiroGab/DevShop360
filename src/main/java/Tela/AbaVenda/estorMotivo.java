package Tela.AbaVenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class estorMotivo extends JFrame {
    public estorMotivo() {

        super("MOTIVO");
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(400,300);

        JPanel panel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/TelaInicial.png")));                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JTextField motivo = new JTextField("MOTIVO");
        motivo.setBounds(90, 60, 200, 40);
        motivo.setFont(new Font("MOTIVO", Font.BOLD, 15));
        motivo.setForeground(Color.GRAY);
        motivo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (motivo.getText().equals("MOTIVO")) {
                    motivo.setText("");
                    motivo.setForeground(Color.BLACK);  // Texto normal
                }
            }

            public void focusLost(FocusEvent e) {
                if (motivo.getText().isEmpty()) {
                    motivo.setText("MOTIVO");
                    motivo.setForeground(Color.GRAY);  // Volta dica
                }
            }
        });

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(90, 120, 200, 40);
        confirmar.setFont(new Font("MOTIVO", Font.BOLD, 15));
        confirmar.setForeground(Color.GRAY);
        panel.add(confirmar);

        panel.add(motivo);
        panel.setLayout(null);

        setVisible(true);
        add(panel);
    }
    public static void main(String[] args) {
        new estorMotivo();

    }
}
