package principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import dale.GestionatorDale;
import entitati.Inamic;
import entitati.Jucator;
import entitati.Premiu;

public class Panou extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	//ecranul jocului
	final int marimeOriginalaDale = 10;
	final int scala = 5;
	public final int marimeaDale = marimeOriginalaDale * scala;
	public final int maxEcranColoane = 12;
	public final int maxEcranRanduri = 12;
	public final int latimeEcran = marimeaDale * maxEcranColoane;
	public final int inaltimeEcran = marimeaDale * maxEcranRanduri;
	//NOU inamic standalone
	public Inamic inamic = new Inamic(this);
	
	// configurarea valorilor lumii jocului
	public final int maxLumeColoane = 50;
	public final int maxLumeRanduri = 50;
	public final int latimeaLumii = marimeaDale * maxLumeColoane;
	public final int inaltimeaLumii = marimeaDale * maxLumeRanduri;
	
	// ceas, ceasul final
	public int ceas = 0, ceasFinal = 0;
	public Cronometru cronometru = new Cronometru(this);
	
	// valorile X si Y ale premiului
	public final int premiuX = 23 * marimeaDale;
	public final int premiuY = 18 * marimeaDale;
	
	// obiect premiu/banana
	public Premiu premiu = new Premiu(this);
	
	GestionatorDale gestiuneDale = new GestionatorDale(this);
	GestionatorTaste gestiuneTaste = new GestionatorTaste();
	
	int FPS=60; // frames per second sau cadre pe secunda
	
	Thread firulJocului;
	public VerificColiziune verifificatorColiziune = new VerificColiziune(this);
	public Jucator jucatorul = new Jucator(this, gestiuneTaste);
	
	public Panou() {

		this.setPreferredSize(new Dimension( latimeEcran , inaltimeEcran ));
		this.setBackground(new Color( 247 , 173 , 100));
		this.setDoubleBuffered(true);
		this.addKeyListener(gestiuneTaste);
		this.setFocusable(true);
		
	}
	
	public void pornesteFirulJocului() {
		firulJocului = new Thread(this);
		firulJocului.start();
	}
	
	@Override
	public void run() {
		
		double intervalDesenare = 1000000000/FPS;
		double urmatorulMomentDesenare= System.nanoTime() + intervalDesenare;
		while(firulJocului != null) {
			update();
			repaint();
			try {
				double timpulRamas = urmatorulMomentDesenare - System.nanoTime();
				timpulRamas = timpulRamas / 1000000;
				
				if(timpulRamas < 0) {
					timpulRamas = 0;
				}
				
				Thread.sleep((long) timpulRamas);
				urmatorulMomentDesenare = urmatorulMomentDesenare + intervalDesenare;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2 =(Graphics2D) g;
		 gestiuneDale.deseneaza(g2);
		 if(premiu.verificaPremiu(jucatorul) == true) {premiu.deseneaza(g2);}  
		 jucatorul.deseneaza(g2);
		// NOU desenarea alternativa inamic
		if( this.ceas > 2 ) inamic.deseneaza(g2);
		 g2.dispose();
	 }
	
	public void update() {
		jucatorul.update();
		// NOU update la inamic
		if( this.ceas > 2 && this.inamic.inamicCastiga ==false ) inamic.update();
	}
	
}
