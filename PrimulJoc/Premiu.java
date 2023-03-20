package entitati;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import principal.Panou;

public class Premiu extends Entitate{

	public Panou panouJoc;
	public boolean inViata = true;
	public boolean timpDa = true;
	public Premiu(Panou panou) {
		this.panouJoc = panou;
		try {
			this.generica = ImageIO.read(getClass().getResourceAsStream("/premiu/banana.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		zonaSolida = new Rectangle();
		zonaSolida.width = 32; 
		zonaSolida.height = 32;
	}
	public void deseneaza(Graphics2D g2) {
		int ecranX = panouJoc.premiuX - panouJoc.jucatorul.worldX + panouJoc.jucatorul.ecranX;
		int ecranY = panouJoc.premiuY - panouJoc.jucatorul.worldY + panouJoc.jucatorul.ecranY;
		g2.drawImage(generica, ecranX, ecranY, panouJoc.marimeaDale, panouJoc.marimeaDale, null );
	}
	
	public boolean verificaPremiu(Jucator jucator) { 
		if(panouJoc.cronometru.maiEsteTimp > 0)
			timpDa = true;
		else
			timpDa = false;
		
		if( jucator.worldX >= panouJoc.premiuX - zonaSolida.width && 
			jucator.worldX <= panouJoc.premiuX + zonaSolida.width &&
			jucator.worldY >= panouJoc.premiuY - zonaSolida.height &&
			jucator.worldY <= panouJoc.premiuY + zonaSolida.height){
			inViata = false; 
		}
		return inViata;	
	}
}
