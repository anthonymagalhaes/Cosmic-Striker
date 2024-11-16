package meujogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

   
    private ImageIcon backgroundImage;
    private Image fundo;
    private JButton btnIniciar;
    private JButton btnSair;
    public Menu() {
    	 this.setTitle("Cosmic Strike");
    	 setSize(1024, 728);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         getContentPane().setLayout(null);
         backgroundImage = new ImageIcon("res\\Fundo2.png");
         fundo = backgroundImage.getImage();
         
         btnIniciar = new JButton("Iniciar Jogo");
         btnIniciar.setBounds(370, 375, 300, 70);
         btnIniciar.setFont(new Font("Arial", Font.BOLD, 30));
         btnSair = new JButton("Sair");
         btnSair.setBounds(420, 460, 200, 70);
         btnSair.setFont(new Font("Arial", Font.BOLD, 30));
         this.add(btnSair);
         this.add(btnIniciar);
         


        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  
                new Container(); 
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
