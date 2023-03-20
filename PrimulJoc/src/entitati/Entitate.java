package entitati;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entitate {
	public int worldX, worldY;
	public int viteza;
	public String directia;
	
	public BufferedImage jucatorSus1, jucatorSus2, jucatorJos1, jucatorJos2, 
			jucatorStanga1, jucatorStanga2, jucatorDreapta1, jucatorDreapta2, generica;
	
	public int numaratorImagine = 0;
	public int numarImagine = 1;
	
	public Rectangle zonaSolida;
	public boolean coliziunePornita = false;
}
