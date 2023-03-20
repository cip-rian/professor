package entitati;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javax.imageio.ImageIO;

import principal.Panou;

public class Inamic extends Entitate{
	public int ecranX;
	public int ecranY;
	Panou panouJoc;
	String sus1, sus2, jos1, jos2, stanga1, stanga2, dreapta1, dreapta2, personajul;
	//aici cream variabile ce ajuta la formarea creierului inamicului
	boolean sus = false, jos = false, stanga= false, dreapta = false;
	ArrayList<Neuron> creier = new ArrayList<>();
	int loculDinMemorie;
	public boolean inamicCastiga = false;
	
	private void obtinemImagineaInamicului() {	
		try {	
			personajul = "elefant";
			sus1 = "/inamic/" + personajul + "Sus1.png";
			sus2 = "/inamic/" + personajul + "Sus2.png";
			
			jos1 = "/inamic/" + personajul + "Jos1.png";
			jos2 = "/inamic/" + personajul + "Jos2.png";
			
			dreapta1 = "/inamic/" + personajul + "Dreapta1.png";
			dreapta2 = "/inamic/" + personajul + "Dreapta2.png";
			
			stanga1 = "/inamic/" + personajul + "Stanga1.png";
			stanga2 = "/inamic/" + personajul + "Stanga2.png";
			
			jucatorSus1 = ImageIO.read(getClass().getResourceAsStream(sus1));
			jucatorSus2 = ImageIO.read(getClass().getResourceAsStream(sus2));
			
			jucatorJos1 = ImageIO.read(getClass().getResourceAsStream(jos1));
			jucatorJos2 = ImageIO.read(getClass().getResourceAsStream(jos2));
			
			jucatorStanga1 = ImageIO.read(getClass().getResourceAsStream(stanga1));
			jucatorStanga2 = ImageIO.read(getClass().getResourceAsStream(stanga2));
			
			jucatorDreapta1 = ImageIO.read(getClass().getResourceAsStream(dreapta1));
			jucatorDreapta2 = ImageIO.read(getClass().getResourceAsStream(dreapta2));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private void configuramValorileInitiale() {
		worldX = panouJoc.marimeaDale * 10;
		worldY = panouJoc.marimeaDale * 6;	
		viteza = 3;
		directia = "stanga";
		inamicCastiga = false;
	}
	public Inamic(Panou panou ) {
		this.panouJoc = panou;

		zonaSolida = new Rectangle(); 
		zonaSolida.x = 12; 
		zonaSolida.y = 12; 
		zonaSolida.width = 14; 
		zonaSolida.height= 14; 
		
		configuramValorileInitiale();
		obtinemImagineaInamicului();
	}
public void deseneaza(Graphics2D g2) {		
		BufferedImage image = null;
		switch( directia ) {
			case "sus":
				if( numarImagine == 1) {
					image = jucatorSus1;
				}
				if( numarImagine == 2) {
					image = jucatorSus2;
				}				
				break;
			case "jos":
				if( numarImagine == 1) {
					image = jucatorJos1;
				}
				if( numarImagine == 2) {
					image = jucatorJos2;
				}							
				break;
			case "stanga":
				if( numarImagine == 1) {
					image = jucatorStanga1;
				}
				if( numarImagine == 2) {
					image = jucatorStanga2;
				}								
				break;
			case "dreapta":
				if( numarImagine == 1) {
					image = jucatorDreapta1;
				}
				if( numarImagine == 2) {
					image = jucatorDreapta2;
				}							
				break;
		} 
			
		int ecranX = worldX - panouJoc.jucatorul.worldX + panouJoc.jucatorul.ecranX;
		int ecranY = worldY - panouJoc.jucatorul.worldY + panouJoc.jucatorul.ecranY;
		
		if(	worldX + panouJoc.marimeaDale > panouJoc.jucatorul.worldX - panouJoc.jucatorul.ecranX &&
			worldX - panouJoc.marimeaDale < panouJoc.jucatorul.worldX + panouJoc.jucatorul.ecranX &&
			worldY + panouJoc.marimeaDale > panouJoc.jucatorul.worldY - panouJoc.jucatorul.ecranY &&
			worldY - panouJoc.marimeaDale < panouJoc.jucatorul.worldY + panouJoc.jucatorul.ecranY
			) {
			g2.drawImage(image, ecranX, ecranY, panouJoc.marimeaDale , panouJoc.marimeaDale, null );
		}
	}

public void undeEsteTigrul() {
	
		if( panouJoc.jucatorul.worldX - this.worldX < 0 ) {
			// jucatorul este la stanga fata de inamic
			System.out.println("stanga");
		}else if ( panouJoc.jucatorul.worldX - this.worldX > 0 ){
			// jucatorul este la dreapta fata de inamic
			System.out.println("dreapta");
		}
		if( panouJoc.jucatorul.worldY - this.worldY < 0 ) {
			// jucatorul este mai sus fata de inamic
			System.out.println("sus");
		}else  if( panouJoc.jucatorul.worldY - this.worldY > 0 ){
			// jucatorul este mai jos fata de inamic
			System.out.println("jos");
		} 
		if( panouJoc.jucatorul.worldX - this.worldX > - zonaSolida.width && 
			panouJoc.jucatorul.worldX - this.worldX < zonaSolida.width &&
			panouJoc.jucatorul.worldY - this.worldY >-zonaSolida.height && 
			panouJoc.jucatorul.worldY - this.worldY < zonaSolida.height ) {
			
			System.out.println("<<<prins tigrul O>>>");
			panouJoc.jucatorul.viteza = 0;
			panouJoc.jucatorul.directia = "invins";
			this.viteza = 0;
			panouJoc.jucatorul.fondSonor.clip.close();
			panouJoc.jucatorul.sunetPierdut.clip.start();
			inamicCastiga = true;			
		}
}

public void update() {

	this.undeEsteTigrul();
	coliziunePornita = false;
	panouJoc.verifificatorColiziune.verificDala(this);	
	
	if(coliziunePornita == false) {
		// aici putem misca in directia in care dorim deoarece 
		// coliziunea esta false (nu este copac, sau obiect pe care nu putem sa ne deplasam)
		switch( directia ) {
		case "sus":
			worldY = worldY - viteza;
			break;
		case "jos":
			worldY = worldY + viteza;
			break;
		case "stanga":
			worldX = worldX - viteza;
			break;
		case "dreapta":
			worldX = worldX + viteza;
			break;
		}
	}else {
		//Aici este copac sau obiect pe care nu ne putem deplasa deoarece coliziuneaPornita este true	
		// trebuie sa resetam coliziunea (dar nu facem nici o mutare, ci verificam unde ne putem deplasa)
		coliziunePornita = false;
		
		//dar inainte verificam in creier daca exista neuron cu acest punct la care suntem sau nu
		//daca nu exista acest neuron; iar daca exista pastram loculDinMemorie unde deja este
		if( creier.size() > 0 ) { // daca lista de neuroni nu e goala (are deja cel putin un neuron)
			if( creier.get(0).existaNeuron( this.worldX, this.worldY, creier ) == false) { 
				// verificam daca nu exista deja neuronul la care ne referim
				creier.add(0, new Neuron( this.worldX, this.worldY )); // daca nu exista il adaugam
				loculDinMemorie = 0;
			}else { // iar daca exista verificam la ce indice este deja
				loculDinMemorie = creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );		
			}
		}else { // daca lista de neuroni e goala adaug primul neuron
			creier.add(0, new Neuron( this.worldX, this.worldY ));
			loculDinMemorie = 0;
		}
		
		// DEBUG 
		creier.get(0).imprimaListaNeuroniNeuron(creier);
		
		System.out.println("\nLoculDinMemorie:"+ loculDinMemorie);
		switch( directia ) {
		case "sus": 
			System.out.println("Mergem in sus");
			sus = false;
			
			directia="jos"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				jos = true;
			}else {	
				jos = false;
			}			
			directia="stanga"; 	
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				stanga = true;
			}else {	
				stanga = false;
			}
			directia="dreapta"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				dreapta = true;
			}else {	
				dreapta = false;
			}
			System.out.println("sus:"+sus);
			System.out.println("jos:"+jos);
			System.out.println("stanga:"+stanga);
			System.out.println("dreapta:"+dreapta);
			
			if( sus == true ) creier.get(loculDinMemorie).optiuneaSus[1] = 1;
			if( jos == true ) creier.get(loculDinMemorie).optiuneaJos[1] = 1;
			if( stanga == true ) creier.get(loculDinMemorie).optiuneaStanga[1] = 1;
			if( dreapta == true ) creier.get(loculDinMemorie).optiuneaDreapta[1] = 1;

			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("< Inainte");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			directia="nimic";
			while( directia.equals("nimic")) {
				directia= creier.get(0).verificOptiunileNeuronului( this.worldX, this.worldY, creier );
			}
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("Dupa >");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			System.out.println("Directia aleasa:" + directia);
			creier.get(0).imprimaListaNeuroniNeuron(creier);
			break;
		case "jos":
			//mergem pe directia jos deci lovindu-ne de un copac verificam daca stanga si dreapta sunt disponibile
			System.out.println("Mergem in jos");
			jos = false;
			directia="sus"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				sus = true;
			}else {
				sus = false;
			}
						
			directia="stanga"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				stanga = true;
			}else {	
				stanga = false;
			}
			directia="dreapta"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				dreapta = true;
			}else {	
				dreapta = false;
			}
			System.out.println("sus:"+sus);
			System.out.println("jos:"+jos);
			System.out.println("stanga:"+stanga);
			System.out.println("dreapta:"+dreapta);

			if( sus == true ) creier.get(loculDinMemorie).optiuneaSus[1] = 1;
			if( jos == true ) creier.get(loculDinMemorie).optiuneaJos[1] = 1;
			if( stanga == true ) creier.get(loculDinMemorie).optiuneaStanga[1] = 1;
			if( dreapta == true ) creier.get(loculDinMemorie).optiuneaDreapta[1] = 1;
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("< Inainte");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			directia="nimic";
			while( directia.equals("nimic")) {
				directia= creier.get(0).verificOptiunileNeuronului( this.worldX, this.worldY, creier );
			}
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("Dupa >");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );

			System.out.println("Directia aleasa:" + directia);
			creier.get(0).imprimaListaNeuroniNeuron(creier);
			break;
		case "stanga":
			System.out.println("Mergem la stanga");
			//mergem pe directia stanga deci lovindu-ne de un copac verificam daca sus si jos sunt disponibile
			stanga=false;
			directia="sus"; 	
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				sus = true;
			}else {
				sus = false;
			}
			directia="jos"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				jos = true;
			}else {	
				jos = false;
			}			

			directia="dreapta"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				dreapta = true;
			}else {	
				dreapta = false;
			}
			System.out.println("sus:"+sus);
			System.out.println("jos:"+jos);
			System.out.println("stanga:"+stanga);
			System.out.println("dreapta:"+dreapta);
			
			if( sus == true ) creier.get(loculDinMemorie).optiuneaSus[1] = 1;
			if( jos == true ) creier.get(loculDinMemorie).optiuneaJos[1] = 1;
			if( stanga == true ) creier.get(loculDinMemorie).optiuneaStanga[1] = 1;
			if( dreapta == true ) creier.get(loculDinMemorie).optiuneaDreapta[1] = 1;
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("< Inainte");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			directia="nimic";
			while( directia.equals("nimic")) {
				directia= creier.get(0).verificOptiunileNeuronului( this.worldX, this.worldY, creier );
			}
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("Dupa >");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			System.out.println("Directia aleasa:" + directia);		
			creier.get(0).imprimaListaNeuroniNeuron(creier);
			break;
		case "dreapta":
			//mergem pe directia dreapta deci lovindu-ne de un copac verificam daca sus si jos sunt disponibile
			System.out.println("Mergem la dreapta");
			dreapta = false;
			directia="sus"; 	
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				sus = true;
			}else {
				sus = false;
			}
			directia="jos"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				jos = true;
			}else {	
				jos = false;
			}			
			directia="stanga"; 
			coliziunePornita=false;
			panouJoc.verifificatorColiziune.verificDala(this);
			if(coliziunePornita==false) {
				stanga = true;
			}else {	
				stanga = false;
			}
			
			System.out.println("sus:"+sus);
			System.out.println("jos:"+jos);
			System.out.println("stanga:"+stanga);
			System.out.println("dreapta:"+dreapta);

			if( sus == true ) creier.get(loculDinMemorie).optiuneaSus[1] = 1;
			if( jos == true ) creier.get(loculDinMemorie).optiuneaJos[1] = 1;
			if( stanga == true ) creier.get(loculDinMemorie).optiuneaStanga[1] = 1;
			if( dreapta == true ) creier.get(loculDinMemorie).optiuneaDreapta[1] = 1;
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("< Inainte");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			directia="nimic";
			while( directia.equals("nimic")) {
				directia= creier.get(0).verificOptiunileNeuronului( this.worldX, this.worldY, creier );
			}
			
			// DEBUG scriem ce se intampla pentru a usura intelegerea
			System.out.println("Dupa >");
			creier.get(0).obtineNeuron( this.worldX, this.worldY, creier );
			
			System.out.println("Directia aleasa:" + directia);
			creier.get(0).imprimaListaNeuroniNeuron(creier);
			break;
		}		
	}
	numaratorImagine++;
	if( numaratorImagine > 10) {
		if( numarImagine == 1) {
			numarImagine = 2;
		}else if ( numarImagine == 2 ) {
			numarImagine = 1;
		}
		numaratorImagine = 0;
		}
	}
}

