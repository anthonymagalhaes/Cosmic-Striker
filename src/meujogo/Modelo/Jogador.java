package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;

public class Jogador implements ActionListener  {
	private String[] musicas = {
	        "/TiroSpace.wav",
	        "/tiro2.wav",
	        "/tiro3.wav"
	        
	    };
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private List<Tiro> tiros;
    private static int velocidade = 6;
    private boolean isVisivel, isTurbo;
    private Timer turboTimer; 
    private Timer disparoTimer;
    private static final int INTERVALO_DISPARO = 250;
    private static final int TEMPO_TURBO = 5000; 
    private static int nivel = 1;
    private SoundPlayer tocarTiro;
    private SoundPlayer turboActivated ;
    private URL turboActivatedURL,tocarTiroURL;
    private boolean turboDisponivel = true; 
    private Timer cooldownTurboTimer;
    private static final int TEMPO_COOLDOWN = 20000; 
    public Jogador() {
        this.x = 100;
        this.y = 310;
        this.nivel = 1;
        this.isVisivel = true;
        isTurbo = false;
        tocarTiro = new SoundPlayer();
        turboActivated =new SoundPlayer();
        turboActivatedURL = getClass().getResource("/turbo.wav");
        turboActivated.setFile(turboActivatedURL);
        tiros = new ArrayList<Tiro>();
        disparoTimer = new Timer(INTERVALO_DISPARO, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        disparoTimer.setRepeats(false);
    }

    public static int getVelocidade() {
        return velocidade;
    }

    public static void setVelocidade(int velocidade) {
        Jogador.velocidade = velocidade;
    }

    public static void setNivel(int nivel) {
        Jogador.nivel = nivel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    private void randomizarTiro() {
    	 Random rand = new Random();
    	 tocarTiro = new SoundPlayer();
         int musicaAleatoriaIndex = rand.nextInt(musicas.length);
         
         tocarTiroURL = getClass().getResource(musicas[musicaAleatoriaIndex]);
                  
         tocarTiro.setFile(tocarTiroURL);  
         tocarTiro.play(false); 
    }
  
    public void turbo() {
    	if (!isTurbo && turboDisponivel) {
            isTurbo = true;
            ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("naveTurbo.gif"));
            imagem = referencia.getImage();
            turboActivated.play(false);
         
            turboDisponivel = false;
            cooldownTurboTimer = new Timer(TEMPO_COOLDOWN, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    turboDisponivel = true; 
                }
            });
            cooldownTurboTimer.setRepeats(false);
            cooldownTurboTimer.start();

            turboTimer = new Timer(TEMPO_TURBO, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    desativarTurbo(); 
                }
            });
            turboTimer.setRepeats(false); 
            turboTimer.start();
        }
    }

 
    public void desativarTurbo() {
        isTurbo = false;
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("nave.png"));
        imagem = referencia.getImage();
    }
    public void Explodindo() {
    	isTurbo = false;
    	turboDisponivel = false;
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("Explosão.gif"));
        imagem = referencia.getImage();
        imagem.flush();
    }

    public void load() {
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("nave.png"));
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = 60;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void tirosTriplos() {
        if (!isTurbo && !disparoTimer.isRunning()) {
        	randomizarTiro();
            this.tiros.add(new Tiro(x + largura, y + (altura / 2) - 25));
            this.tiros.add(new Tiro(x + largura - 1, y + (altura / 2) + 1));
            this.tiros.add(new Tiro(x + largura - 2, y + (altura / 2) + 25));
            
            disparoTimer.start();
        }
    }

    public void tirosDuplos() {
        if (!isTurbo && !disparoTimer.isRunning()) {
        	
        	randomizarTiro();
            this.tiros.add(new Tiro(x + largura, y + (altura / 2) - 17));
            this.tiros.add(new Tiro(x + largura - 1, y + (altura / 2) + 17));
            
            disparoTimer.start();
        }
    }

    public void tirosSimples() {
        if (!isTurbo && !disparoTimer.isRunning()) {
        	
        	randomizarTiro();
            this.tiros.add(new Tiro(x + largura, y + (altura / 2)));
            
            disparoTimer.start();
        }
    }

    public void tiro() {
    	
        
        if (nivel == 1) {
            tirosSimples();
        } else if (nivel == 2) {
            tirosDuplos();
        } else if (nivel == 3) {
            tirosTriplos();
        }
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE ) {
            turbo();
        }
        if (codigo == KeyEvent.VK_A) {
            tiro();
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = -velocidade;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = velocidade;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = velocidade;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -velocidade;
        }
    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean iVisivel) {
        this.isVisivel = iVisivel;
    }

    public boolean isTurbo() {
        return isTurbo;
    }

    public void setTurbo(boolean isTurbo) {
        this.isTurbo = isTurbo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImagem() {
        return imagem;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public static int getNivel() {
        return nivel;
    }

	public boolean isTurboDisponivel() {
		return turboDisponivel;
	}

	
    
}
