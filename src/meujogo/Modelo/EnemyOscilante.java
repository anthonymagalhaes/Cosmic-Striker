package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class EnemyOscilante {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    private static int VELOCIDADE = 3;
    private static int SPAWN_INTERVAL =100;
    private boolean descendo = true; 
    private static int VELOCIDADE_VERTICAL = 3; 

    public EnemyOscilante(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("EnemyOscilante.gif"));
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    // Atualiza a posição do inimigo
    public void update() {
        this.x -= VELOCIDADE; // Movimento para a esquerda

        // Movimento vertical oscilante
        if (descendo) {
            this.y += VELOCIDADE_VERTICAL;
        } else {
            this.y -= VELOCIDADE_VERTICAL;
        }

        // Limites para inverter o movimento vertical (oscilação)
        if (this.y >= 710) {  // Limite inferior
            descendo = false;
        } else if (this.y <= 10) {  // Limite superior
            descendo = true;
        }
    }

    // Retorna o limite do inimigo para detecção de colisões
    public Rectangle getBound() {
        return new Rectangle(x, y, largura, altura);
    }

    // Métodos getters e setters
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public static int getSpawnInterval() {
        return SPAWN_INTERVAL;
    }

    public static void setSpawnInterval(int spawnInterval) {
        SPAWN_INTERVAL = spawnInterval;
    }

    public static int getVelocidadeVertical() {
        return VELOCIDADE_VERTICAL;
    }

    public static void setVelocidadeVertical(int velocidadeVertical) {
        VELOCIDADE_VERTICAL = velocidadeVertical;
    }
}
