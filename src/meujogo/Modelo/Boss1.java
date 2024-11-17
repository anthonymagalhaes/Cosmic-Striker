package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Boss1 {
	private String[] musicas = {
	        "/Damage1.wav",
	        "/Damage2.wav",
	    };
    private Image imagem;
    private Image imagemNormal;  
    private Image imagemDano;    
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    private int life = 120;
    private final int velocidade = 2; 
    private boolean movendo = true;
    private boolean atingido = false;  
    private boolean derrotado;
    private SoundPlayer tocarfim ;
    private SoundPlayer damage;
    private URL tocarfimURL,damageURL;
    private boolean isTilting = false;
    private int tiltOffset = 10; 
    private int originalX;
    public Boss1(int x, int y) {
    	tocarfim = new SoundPlayer();
    	damage = new SoundPlayer();
    	tocarfimURL = getClass().getResource("/trumpet-fanfare-royal-entrance-soundroll-1-00-09.wav");
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
    private void randomizarDamage() {
    	Random rand = new Random();
   	    damage = new SoundPlayer();
        int musicaAleatoriaIndex = rand.nextInt(musicas.length);
        
        damageURL = getClass().getResource(musicas[musicaAleatoriaIndex]);
                 
            damage.setFile(damageURL);  
            damage.play(false); 
    }
  
    public void load() {
    	
        ImageIcon referenciaNormal = new ImageIcon(getClass().getClassLoader().getResource("Boss.png")); 
        ImageIcon referenciaDano = new ImageIcon(getClass().getClassLoader().getResource("BossDano.png")); 
        
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
            
            tocarfim.play(false);
        }
    }

   
    public void levarDano() {
    	randomizarDamage();
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
            	
                movendo = false; 
            }
        }
    }
	public void atacar() {
	    
	    if (System.currentTimeMillis() % 1000 == 0) {
	    	
	    }
	}
    
    public void setAtingido(boolean atingido) {
		this.atingido = atingido;
	}


	public Rectangle getBound() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
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
