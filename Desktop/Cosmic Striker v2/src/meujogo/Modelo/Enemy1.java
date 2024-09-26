package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.ImageIcon;

public class Enemy1 {
	private Image imagem;
	private int x,y;
	private int largura,altura;
	private boolean isVisible;
	private static int SPAWN_INTERVAL = 230;
	private boolean descendo = true;
	private boolean movimento;
	private static int VELOCIDADE = 3;
	

	
	
	public Enemy1(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
		
		
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res//Enemy.png");
		imagem= referencia.getImage();
			
		this.largura = 100;
		this.altura = 140;
	}
	public void update() {
		this.x -= VELOCIDADE;
		
		if(this.movimento) {
			this.y -= 1;
		}
		
	}
	
	public Rectangle getBound() {
		return new Rectangle (x,y,largura,altura);
	}
	
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
	
	public void setMovimento(boolean movimento) {
		this.movimento = movimento;
	}

	public static int getSPAWN_INTERVAL() {
		return SPAWN_INTERVAL;
	}

	public static void setSPAWN_INTERVAL(int sPAWN_INTERVAL) {
		SPAWN_INTERVAL = sPAWN_INTERVAL;
	}
	

	
}
