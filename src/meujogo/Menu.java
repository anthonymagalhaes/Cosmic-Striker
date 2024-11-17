package meujogo;

import javax.swing.*;
import meujogo.Modelo.Fase;
import meujogo.Modelo.SoundPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends JFrame {

    private ImageIcon backgroundImage;
    private Image fundo;
    private JButton btnIniciar;
    private JButton btnSair;
    private SoundPlayer click;
    private SoundPlayer menu;
    private URL clickURL, menuURL;

    public Menu() {
        this.setTitle("Cosmic Strike");
        setSize(1024, 728);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("Fundo2.png"));
        fundo = backgroundImage.getImage();
        clickURL = getClass().getResource("/click.wav");
        menuURL = getClass().getResource("/menu.wav");
        SoundPlayer click = new SoundPlayer();
        SoundPlayer menu = new SoundPlayer();
        menu.setFile(menuURL);
        menu.play(false);
        click.setFile(clickURL);
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
                menu.stop();
                click.play(false);
                setContentPane(new Fase());  
                revalidate();  
                repaint();    
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                });
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
