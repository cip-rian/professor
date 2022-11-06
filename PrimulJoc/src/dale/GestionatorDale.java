package dale;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import principal.Panou;

public class GestionatorDale {

	Panou panoulJocului;
	public Dale[] dale;
	public int hartaNumereDale[][];
	
	public GestionatorDale( Panou panoulJocului) {
		this.panoulJocului = panoulJocului;
		dale = new Dale[10];
		hartaNumereDale = new int[panoulJocului.maxLumeColoane][panoulJocului.maxLumeRanduri];
		obtineImagineDale();
		incarcaHarta("/harti/harta1.txt");
	}
	
	private void incarcaHarta(String harta) {
		try {
		InputStream is = getClass().getResourceAsStream(harta);
		BufferedReader br = new BufferedReader (new InputStreamReader(is));
		
		int coloana = 0;
		int randul = 0;
		
		while(coloana < panoulJocului.maxLumeColoane && randul < panoulJocului.maxLumeRanduri) {
			String linie;
			
				linie = br.readLine();
			
			while(coloana < panoulJocului.maxLumeColoane) {
				String numere[] = linie.split(" ");
				int numar = Integer.parseInt(numere[coloana]);
				hartaNumereDale[coloana][randul] = numar; 
				coloana++;
			}
			if(coloana == panoulJocului.maxLumeColoane) {
				coloana = 0;
				randul++;
			}
		}
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void obtineImagineDale() {	
		try {
			dale[0] = new Dale();
			dale[0].image = ImageIO.read(getClass().getResourceAsStream("/dale/arbore.png"));
			dale[0].coliziune = true;
			
			dale[1] = new Dale();
			dale[1].image = ImageIO.read(getClass().getResourceAsStream("/dale/drum.png"));
			
			dale[2] = new Dale();
			dale[2].image = ImageIO.read(getClass().getResourceAsStream("/premiu/banana.png"));
			
			dale[3] = new Dale();
			dale[3].image = ImageIO.read(getClass().getResourceAsStream("/dale/pomMar1.png"));
			dale[3].coliziune = true;
			
			dale[4] = new Dale();
			dale[4].image = ImageIO.read(getClass().getResourceAsStream("/dale/pomMar2.png"));
			dale[4].coliziune = true;
			
			dale[5] = new Dale();
			dale[5].image = ImageIO.read(getClass().getResourceAsStream("/dale/pomMar3.png"));
			dale[5].coliziune = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void deseneaza(Graphics2D g2) {
		
		int lumeColoane = 0;
		int lumeRanduri = 0;
		
		while( lumeColoane < panoulJocului.maxLumeColoane && lumeRanduri < panoulJocului.maxLumeRanduri) {
			int numarDale = hartaNumereDale[lumeColoane][lumeRanduri];
			
			int lumeX = lumeColoane * panoulJocului.marimeaDale;
			int lumeY = lumeRanduri * panoulJocului.marimeaDale;
			
			int ecranX = lumeX - panoulJocului.jucatorul.worldX + panoulJocului.jucatorul.ecranX;
			int ecranY = lumeY - panoulJocului.jucatorul.worldY + panoulJocului.jucatorul.ecranY;

			g2.drawImage(dale[numarDale].image, ecranX, ecranY, panoulJocului.marimeaDale , panoulJocului.marimeaDale, null );
			lumeColoane++;
			
			if(lumeColoane == panoulJocului.maxLumeColoane) {
				lumeColoane = 0;
				lumeRanduri++;
			}
		}		
	}
}
