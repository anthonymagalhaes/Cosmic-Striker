package meujogo.Modelo;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {
	private Image imagem;
	private int x,y;
	private int largura;
	private boolean isVisible;
	
	
	//private static final int LARGURA = 938; 
	private static int VELOCIDADE = 5;
	
	
	public Stars(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
		
	}
	
	public void load() {
		if(VELOCIDADE == 5) {
			ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("estrela.png"));
			imagem= referencia.getImage();
				
			this.largura = imagem.getWidth(null);
			imagem.getHeight(null);
		}else {
			ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("effect.png"));
			imagem= referencia.getImage();
					
			this.largura = imagem.getWidth(null);
			imagem.getHeight(null);
		}
	}
	
	public void update() {
		
		if(this.x <0) {
			this.x = largura;
			Random a = new Random();
			int m = a.nextInt(500);
			this.x = m + 1024;
			Random r = new Random();
			int n = r.nextInt(768);
			this.y = n;
			
		}else {
			this.x -= VELOCIDADE;
		}
		
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

	
	
	
	
	
}
