package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Boss1 {

    private Image imagem;
    private Image imagemNormal;  
    private Image imagemDano;    
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    private int life = 80;
    private final int velocidade = 2; 
    private boolean movendo = true;
    private boolean atingido = false;  
    private boolean derrotado;
    private SoundPlayer tocarfim = new SoundPlayer("res\\trumpet-fanfare-royal-entrance-soundroll-1-00-09.wav");
    private SoundPlayer tocarBoss = new SoundPlayer("res\\BossEntrance.wav");
    private boolean isTilting = false;
    private int tiltOffset = 10; // Quantidade do tilt (pixels)
    private int originalX;
    public Boss1(int x, int y) {
        this.x = x;
        this.y = y;
        derrotado = false;
        isVisible = true;
        load();
    }
    
    public void aplicarTilt() {
    	imagem = imagemDano;
        if (!isTilting) {
            isTilting = true;
            originalX = x; 
            
            Timer timer = new Timer(100, new ActionListener() {
                int step = 0; 

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (step < 1) {
                        x += (step % 2 == 0 ? -tiltOffset : tiltOffset); 
                        step++;
                    } else {
                        x = originalX; 
                        isTilting = false; 
                        ((Timer) e.getSource()).stop(); 
                    }
                }
            });
            timer.start();
        }
    }

    public boolean isTilting() {
        return isTilting;
    }
    public void load() {
    	
        ImageIcon referenciaNormal = new ImageIcon("res\\Boss.png"); 
        ImageIcon referenciaDano = new ImageIcon("res\\BossDano.png"); 
        
        imagemNormal = referenciaNormal.getImage();
        imagemDano = referenciaDano.getImage();
        
        imagem = imagemNormal; 
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }




    public void diminuirLife(int quantidade) {
        this.life -= quantidade;
        
        if (this.life > 0) {
            levarDano();
        } else {
            this.isVisible = false; 
            this.derrotado = true;
            tocarBoss.stop();
            tocarfim.play(false);
        }
    }

   
    public void levarDano() {
      
        imagem = imagemDano;
        atingido = true;
        new Timer(500, e -> {
            setAtingido(false);
            ((Timer) e.getSource()).stop(); 
        }).start();
        
    }

    public boolean isAtingido() {
		return atingido;
	}


	public void update() {
        if (movendo) {
            this.x -= velocidade; 
            if (this.x <= 800) { 
            	tocarBoss.play(true);
                movendo = false; 
            }
        }
    }

    
    public void setAtingido(boolean atingido) {
		this.atingido = atingido;
	}


	public Rectangle getBound() {
        return new Rectangle(x, y, largura, altura);
    }

    // Getter e setter para a visibilidade do boss
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    // Getters para a posição e a imagem do boss
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
    public Image getImagemDano() {
        return imagemDano;
    }
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

	public boolean isDerrotado() {
		return derrotado;
	}

	public void setDerrotado(boolean derrotado) {
		this.derrotado = derrotado;
	}


	public int getLargura() {
		return largura;
	}


	public int getAltura() {
		return altura;
	}
    
}
