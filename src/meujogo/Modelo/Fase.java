package meujogo.Modelo;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import meujogo.Menu;

public class Fase extends JPanel implements ActionListener{
	private Image fundo;
	private Jogador player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<EnemyOscilante> enemy2;
	private List<Boss1> boss;
	private List<Stars> stars;
	private List<Poder> poder;
	private boolean emJogo;
	private int quantidade = 250;
	private int pontuacao = 0;
	private boolean mostrarTexto= true;
    private Timer timerTutorial;
    private Timer timerMorte;
    private boolean bossAtivo;
    private Timer timerFim;
    private boolean fim = false;
    private int vlc = 3;
    private int turbo = 10;
    private SoundPlayer tocar = new SoundPlayer("res\\musica1.wav");
    private SoundPlayer tocarPowerUp = new SoundPlayer("res\\PowerUp.wav");
    private SoundPlayer tocarMorte = new SoundPlayer("res\\Morreu.wav");
    private SoundPlayer tocarBoss = new SoundPlayer("res\\BossEntrance.wav");
    private SoundPlayer gameover = new SoundPlayer("res\\game-over.wav");
    private int contador = 1;
    private boolean mostrarTimer = false;
    private boolean TurboAtivo = false;
    private boolean morteEmAndamento; 
    private boolean fimemandamento;
    public Fase() {
    	
    	emJogo = true;
    	bossAtivo = false;
    	morteEmAndamento = false;
    	fimemandamento = false;
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res\\Fundo.png");
		
		fundo = referencia.getImage();
		inicializaBoss(false);
		inicializaInimigos(true);
		inicializarInimigos2(false,0);
		inicializaStars();
		inicializarPoder(false);
		player = new Jogador();
		player.load();
		
		addKeyListener(new TecladoAdapter());

		timer = new Timer(1,this);
		timer.start();
		this.tocar.play(true);
		timerMorte = new Timer(2000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        emJogo = false;
		        gameover.play(false);
		        
		    }
		});
		timerMorte.setRepeats(false);
		timerFim = new Timer(8000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        emJogo = false;
		        
		    }
		});
		timerFim.setRepeats(false);
      
		timerTutorial = new Timer(5000, new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                mostrarTexto = false;
                timerTutorial.stop();
                repaint(); 
            }
        });
		timerTutorial.setRepeats(false);
		new Timer(20, new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		 
    }

	        
	
    int i =1;
   
	public void Morte() {
		if (morteEmAndamento) {
           
            return;
        }
		morteEmAndamento = true; 
        tocar.stop();
        
        player.Explodindo();
        tocarMorte.play(false);
        new Timer(3000, e -> morteEmAndamento = false).start(); 
        
	}
	public void MorteBoss() {
		if (fimemandamento) {
           
            return;
        }
		fimemandamento = true; 
        tocar.stop();
        
        
        new Timer(3000, e -> fimemandamento = false).start(); 
        
	}
	public void inicializaInimigos(boolean t) {
		enemy1 = new ArrayList<Enemy1>();
        int baseX = 1024;
        if(t) {
        for (int i = 0; i < quantidade; i++) {
            int x = baseX + i * Enemy1.getSpawnInterval(); 
            int y = (int) (Math.random() * 500 + 30); 
            enemy1.add(new Enemy1(x, y));
            
        }
        }
        
		}	
	public void inicializarInimigos2(boolean t,int p) {
		enemy2 = new ArrayList<EnemyOscilante>();
        int baseX = 1024;
        if(t) {
		for (int i = 0; i < p; i++) {
            int x = baseX + i * EnemyOscilante.getSpawnInterval(); 
            int y = (int) (Math.random() * 500 + 30); 
           enemy2.add(new EnemyOscilante(x, y));
		}
        }
	}
	public void inicializaBoss(boolean i){
		boss = new ArrayList<Boss1>();
		
        int baseX = 1024;
        int x = baseX ;
        int y = 100;
        if(i) {
        	boss.add(new Boss1(x, y));
        	this.tocar.stop();
        }else {
        	
        }
		}
	
	public void inicializaStars() {
		int cordenadas[]= new int[600];
		stars = new ArrayList<Stars>();
		for(int i = 0;i <cordenadas.length;i++) {
			int x = (int)(Math.random() * 1024+0);
			int y = (int)(Math.random() * 768+0);
			stars.add(new Stars(x,y));
		}
	}
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		 g.setFont(new Font("Arial", Font.BOLD, 24));
	     g.setColor(Color.WHITE);
		if(emJogo ) {
			super.paintComponent(g); 
	        graficos.drawImage(fundo, 0, 0,  null);
			graficos.drawString("Pontuação: "+ pontuacao, 800, 100);
			if (mostrarTexto) {
                graficos.drawString("A = Atirar | SPACEBAR = Turbo | Esc = Pause", 40, 20);
            }
			if (mostrarTimer) {
		        graficos.drawString(""+contador, player.getX(), player.getY()); 
		    }
			if(player.isTurboDisponivel()) {
				
				ImageIcon imagemTurbo= new ImageIcon("res\\TurboDisponivel.gif");
				if(timerMorte.isRunning()) {
					imagemTurbo.getImage().flush();
				}
				graficos.drawImage(imagemTurbo.getImage(),player.getX()-28,player.getY()+10,this);
			}
			if(player.isTurbo() ) {
				ImageIcon imagemFlames= new ImageIcon("res\\Flames.gif");
				ImageIcon imagemSpeed= new ImageIcon("res\\Speed.gif");
				
				graficos.drawImage(imagemFlames.getImage(),player.getX()-28,player.getY()+5,this);
				graficos.drawImage(imagemSpeed.getImage(),player.getX()-10 ,player.getY()-22,this);
				
			}
			
			for(int p=0;p<stars.size();p++) {
				Stars q = stars.get(p);
				q.load();
				graficos.drawImage(q.getImagem(),q.getX(),q.getY(),this);
			}
			
				graficos.drawImage(player.getImagem(), player.getX(), player.getY(),this);
			
			
			List<Tiro> tiros = player.getTiros();
			for(int i=0;i<tiros.size();i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(),m.getX(),m.getY(),this);
			}
			for(int o = 0;o<enemy1.size();o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(),in.getX() ,in.getY(),this);
			}
			for(int o = 0;o<enemy2.size();o++) {
				EnemyOscilante in = enemy2.get(o);
				in.load();
				graficos.drawImage(in.getImagem(),in.getX() ,in.getY(),this);
			}
			for(int o = 0;o<poder.size();o++) {
				Poder in = poder.get(o);
				in.load();
				graficos.drawImage(in.getImagem(),in.getX() ,in.getY(),this);
			}
			for(int o = 0;o<boss.size();o++) {
				Boss1 in = boss.get(o);
				
				in.load();
				if (in.isAtingido()) {
			        in.aplicarTilt(); 
			        
			        
			    }
				
				graficos.drawImage(in.getImagem(),in.getX() ,in.getY(),this);
			}
			
			
			
		}
		
		if (emJogo == false) {
		
		    int valor = SalvarJogo.lerNumeroDoArquivo();
		    
		   
		    if (pontuacao > valor) {
		     
		        SalvarJogo.atualizarArquivo(pontuacao);
		        valor = pontuacao;  
		    }
		    if (fim) {
		        ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png");
		        graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		        graficos.drawString("Melhor Pontuação: " + valor, 700, 60);
		        graficos.drawString("Pontuação: " + pontuacao, 800, 100);
		        graficos.drawString("Pressione ENTER", 100, 100);
		    } else {
		        ImageIcon fimJogo = new ImageIcon("res\\fimdejogo2.png");
		        graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		        graficos.drawString("Melhor Pontuação: " + valor, 700, 60);
		        graficos.drawString("Pontuação: " + pontuacao, 800, 100);
		        graficos.drawString("Pressione ENTER", 100, 100);
		    }
		}

		
		g.dispose();
	}
	
	private void iniciarTimer() {
	    mostrarTimer = true;
	    contador = 5; 
	    timer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (contador > 1) {
	                contador--;
	            } else {
	                timer.stop();
	                mostrarTimer = false; // Para de mostrar o timer após 5 segundos
	            }
	            repaint(); // Atualiza a tela
	        }
	    });
	    timer.start();
	}
	public void reiniciarFase() {
		fim = false;
		player = new Jogador();
        player.load();
		emJogo = true;
        player.setTurbo(false);
        Jogador.setVelocidade(6);
        player.setVisivel(true);
        player.setTurbo(false);
        tocarBoss.stop();
        bossAtivo = false;
        this.tocar.play(true);
        timerTutorial.stop();
        inicializarPoder(false);
        inicializaInimigos(true);
        inicializarInimigos2(false,0);
        inicializaStars();
        inicializaBoss(false);
        pontuacao = 0;
        
        mostrarTexto = true;
        repaint();
    }
	public void repainttela() {
		repaint();
	}
	
	public void inicializarPoder(boolean res) {
		poder = new ArrayList<Poder>();
		if(res == false) {
		   
		}else {
        int baseX = 1024;
        
           int x = baseX + 1 * Poder.getSpawnInterval(); 
            int y = (int) (Math.random() * 500 + 30); 

            poder.add(new Poder(x, y));
        
		}
		}	
	@Override
	public void actionPerformed(ActionEvent e) {
		
        
		player.update();
		
        timerTutorial.setRepeats(false); 
        timerTutorial.start();
		
        
		switch(pontuacao) {
		case 0:
			
			
			vlc = 3;
			Enemy1.setVELOCIDADE(vlc);
			Stars.setVELOCIDADE(20);
			
			
			break;
		case 3:
			
			vlc=4;
			Enemy1.setVELOCIDADE(vlc);
			break;
		case 10:
			vlc = 5;
			Enemy1.setVELOCIDADE(vlc);
			
			break;
		case 25:
			inicializarInimigos2(true,5);
			vlc = 6;
			Enemy1.setVELOCIDADE(vlc);
			break;
		case 45:
			inicializarPoder(true);
			vlc = 7;
			turbo = 10;
			Enemy1.setVELOCIDADE(vlc);
			break;
		case 70:
			
			inicializarInimigos2(true,16);
			break;
		case 100:
			if(!bossAtivo) {
				bossAtivo = true;
				inicializaBoss(true);
			}
			vlc = 8;
			turbo = 13;
			Enemy1.setVELOCIDADE(vlc);
			break;
		case 150:
			vlc = 8;
			turbo = 13;
			inicializarPoder(true);
			inicializarInimigos2(true,15);
			Enemy1.setVELOCIDADE(vlc);
			break;
		case 200:
			vlc = 9;
			turbo = 13;
			Enemy1.setVELOCIDADE(vlc);
			inicializarInimigos2(true,9);
			break;
		}
		
		if (!emJogo) {
			
            return; 
        }
		if(player.isTurbo()) {
			if(!TurboAtivo) {
				TurboAtivo = true;
				iniciarTimer();
			}
			Stars.setVELOCIDADE(40);
			Enemy1.setVELOCIDADE(turbo);
			Jogador.setVelocidade(8);
			
		}
		
	    if(player.getX()<0) {
	    	player.setX(0);
	    }
	    if(player.getX() > 936) {
	    	player.setX(936);
	    }
	    if(player.getY()<0) {
	    	player.setY(0);
	    }
	    if(player.getY()>622) {
	    	player.setY(622);
	    }
		if(player.isTurbo() == false) {
			TurboAtivo =false;
			Jogador.setVelocidade(6);
			
			Stars.setVELOCIDADE(5);
			Enemy1.setVELOCIDADE(vlc);
		}
		
		for(int p =0;p<stars.size();p++) {
			Stars on = stars.get(p);
			if(on.isVisible()) {
				on.update();
			}else {
				stars.remove(p);
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for(int i=0;i < tiros.size();i++) {
			Tiro m = tiros.get(i);
			if(m.isVisible()) {
				m.update();
				if(player.isTurbo()) {
					tiros.get(i).setVELOCIDADE(-1);
				}
				if(player.isTurbo()== false) {
					tiros.get(i).setVELOCIDADE(8);
				}
			}else {
				tiros.remove(i);
			}
		}
		
		for(int o = 0;o<enemy1.size();o++) {
			Enemy1 in = enemy1.get(o);
			if(in.isVisible()) {
				in.update();
			}else {
				enemy1.remove(o);
			}
		}
		for(int o = 0;o<enemy2.size();o++) {
			EnemyOscilante in = enemy2.get(o);
			if(in.isVisible()) {
				in.update();
			}else {
				enemy2.remove(o);
			}
		}
		
		for(int o = 0;o<boss.size();o++) {
			Boss1 in = boss.get(o);
			if(in.isVisible()) {
				in.update();
			}else {
				this.tocarBoss.stop();
				
				boss.remove(o);
			}
		}
		for(int o = 0;o<poder.size();o++) {
			Poder in = poder.get(o);
			if(in.isVisible()) {
				in.update();
			}else {
				tocarPowerUp.play(false);
				poder.remove(o);
			}
		}
		
		checarColisoes();
		repaint();
	}
	


	public void checarColisoes() {
		Rectangle formaNave = player.getBound();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		Rectangle formaPoder;
		Rectangle formaBoss;
		Rectangle formaEnemy2;
		for(int i= 0;i<enemy1.size();i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBound();
			if(formaNave.intersects(formaEnemy1)) {
				if(player.isTurbo()) {
					
					tempEnemy1.setVisible(false);
					pontuacao++;
					
					
					
					
				}else{
					
					Morte();
					
					tempEnemy1.setVisible(false);
					timerMorte.start();
				
					
					
				}
				
			}
			
			
		}
		for(int i= 0;i<enemy2.size();i++) {
			EnemyOscilante tempEnemy2 = enemy2.get(i);
			formaEnemy2 = tempEnemy2.getBound();
			if(formaNave.intersects(formaEnemy2)) {
				if(player.isTurbo()) {
					
					tempEnemy2.setVisible(false);
					pontuacao++;
					
					
					
					
				}else{
					
					Morte();
					tempEnemy2.setVisible(false);
					timerMorte.start();
				
					
					
				}
				
			}
			
			
		}
		for(int i= 0;i< poder.size();i++) {
			Poder tempPoder = poder.get(i);
			formaPoder = tempPoder.getBound();
			if(formaNave.intersects(formaPoder)) {
					
					tempPoder.setVisible(false);
					Jogador.setNivel(Jogador.getNivel()+1);
					
					
			
				
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
		    Tiro tempTiro = tiros.get(i);
		    
		  
		    if (!tempTiro.isVisible()) {
		        continue; 
		    }
		    
		    formaTiro = tempTiro.getBound();
		    boolean colisaoDetectada = false;
		    
		    for (int o = 0; o < enemy1.size(); o++) {
		        Enemy1 tempEnemy1 = enemy1.get(o);
		        
		       
		        if (!tempEnemy1.isVisible()) {
		            continue; 
		        }
		        
		        formaEnemy1 = tempEnemy1.getBound();
		        
		        if (formaTiro.intersects(formaEnemy1)) {
		            tempEnemy1.setVisible(false);
		            tempTiro.setVisible(false);
		            pontuacao++;
		            colisaoDetectada = true;
		            break;
		        }
		    }
		    for (int o = 0; o < enemy2.size(); o++) {
		        EnemyOscilante tempEnemy2 = enemy2.get(o);
		        
		       
		        if (!tempEnemy2.isVisible()) {
		            continue; 
		        }
		        
		        formaEnemy2 = tempEnemy2.getBound();
		        
		        if (formaTiro.intersects(formaEnemy2)) {
		            tempEnemy2.setVisible(false);
		            tempTiro.setVisible(false);
		            pontuacao++;
		            colisaoDetectada = true;
		            break;
		        }
		    }
		    for (int o = 0; o < boss.size(); o++) {
		        Boss1 tempBoss= boss.get(o);
		        
		       
		        if (!tempBoss.isVisible()) {
		            continue; 
		        }
		        
		        formaBoss = tempBoss.getBound();
		        
		        if (formaTiro.intersects(formaBoss)) {
		            tempTiro.setVisible(false);
		            tempBoss.levarDano();
		            tempBoss.diminuirLife(1);
		            
		            if(tempBoss.getLife() <=0) {
		            	fim = true;
		            	tocar.stop();
		            	timerFim.start();
		            	tocarBoss.stop();
		            	MorteBoss();
		            	
		            	enemy1.clear();
		            	enemy2.clear();
		            	poder.clear();
		            }
		            colisaoDetectada = true;
		            break;
		        }
		    }
		   
		    if (colisaoDetectada) {
		        break;
		    }
		}

			
			
		
		
		
		}
	
	private class TecladoAdapter extends KeyAdapter {
		boolean stop =true;
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE && emJogo) {
	        	if(stop == true) {
	        		stop = false;
	        		timer.stop();
	        		tocar.stop();
	        	}else {
	        		stop = true;
	        		timer.start();
	        		tocar.play(true);
	        	}
	            
	        }
			if (e.getKeyCode() == KeyEvent.VK_ENTER && !emJogo) {
                reiniciarFase();
            }
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		 
		}
		 
	}
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		
	}
	
}
