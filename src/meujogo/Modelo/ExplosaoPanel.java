package meujogo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExplosaoPanel extends JPanel {
    private List<Image> frames;
    private int currentFrame = 0;
    private Timer animationTimer;
    private boolean isAnimating = false;
    
    public ExplosaoPanel() {
        frames = new ArrayList<>();
        // Carregar imagens da explosão
        for (int i = 1; i <= 9; i++) { // Supondo 10 quadros
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("explosion\\sprite" + i + ".png"));
            frames.add(icon.getImage());
        }
        // Configurar o Timer para atualizar os quadros
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAnimating) {
                    currentFrame++;
                    if (currentFrame >= frames.size()) {
                        currentFrame = 0; // Reinicia a animação, ou pare se desejar
                        isAnimating = false; // Para a animação ao final
                    }
                    repaint();
                }
            }
        });
    }

    public void iniciarExplosao() {
        currentFrame = 0;
        isAnimating = true;
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isAnimating && !frames.isEmpty()) {
            g.drawImage(frames.get(currentFrame), 0, 0, this);
        }
    }
}
