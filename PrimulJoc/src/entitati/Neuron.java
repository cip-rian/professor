package entitati;

import java.util.ArrayList;

public class Neuron {
	int coordonataX;
	int coordonataY;
	
	int[] optiuneaSus=new int[2]; // pozitia indice 0 daca a fost ales; pozitia indice 1 daca este posibil
	int[] optiuneaJos=new int[2];
	int[] optiuneaStanga=new int[2]; 
	int[] optiuneaDreapta=new int[2];
	
	public Neuron(int coordX, int coordY) {
		this.coordonataX = coordX;
		this.coordonataY = coordY;
		
		optiuneaSus[0] = 0; // pozitia 0 valoarea 0 indica faptul ca sus nu a fost inca aleasa sau verificata
		optiuneaSus[1] = 0; // pozizia 1 valoarea 0 indica faptul ca sus nu e posibil
		
		optiuneaJos[0] = 0; // pozitia 0 valoarea 0 indica faptul ca jos nu a fost inca aleasa sau verificata
		optiuneaJos[1] = 0; // pozizia 1 valoarea 0 indica faptul ca jos nu e posibil
		
		optiuneaStanga[0] = 0; // pozitia 0 valoarea 0 indica faptul ca stanga nu a fost inca aleasa sau verificata
		optiuneaStanga[1] = 0; // pozizia 1 valoarea 0 indica faptul ca stanga nu e posibil
		
		optiuneaDreapta[0] = 0; // pozitia 0 valoarea 0 indica faptul ca dreapta nu a fost inca aleasa sau verificata
		optiuneaDreapta[1] = 0; // pozizia 1 valoarea 0 indica faptul ca dreapta nu e posibil
	}
	
	// metoda care verifica daca exista un neuron in lista
	public boolean existaNeuron(int coordX, int coordY, ArrayList<Neuron> listaNeuroni) {
		boolean resultat = false;
		for(int i=0; i < listaNeuroni.size(); i++) {
			if( coordX == listaNeuroni.get(i).coordonataX && coordY == listaNeuroni.get(i).coordonataY) {
				resultat = true;
			}
		}
		return resultat;
	}
	
	//metoda ce reseteaza sau curata sau pune la valorile initiale optiunile Sus, Jos, Stanga, Dreapta
	public void curataNeuronul(int coordX, int coordY, ArrayList<Neuron> listaNeuroni) {
		for(int i=0; i < listaNeuroni.size(); i++) {
			if( coordX == listaNeuroni.get(i).coordonataX && coordY == listaNeuroni.get(i).coordonataY) {
				listaNeuroni.get(i).optiuneaSus[0] = 0; // pozitia 0 valoarea 0 indica faptul ca sus nu a fost inca aleasa sau verificata
				listaNeuroni.get(i).optiuneaJos[0] = 0; // pozitia 0 valoarea 0 indica faptul ca jos nu a fost inca aleasa sau verificata
				listaNeuroni.get(i).optiuneaStanga[0] = 0; // pozitia 0 valoarea 0 indica faptul ca stanga nu a fost inca aleasa sau verificata
				listaNeuroni.get(i).optiuneaDreapta[0] = 0; // pozitia 0 valoarea 0 indica faptul ca dreapta nu a fost inca aleasa sau verificata
				System.out.println("curataNeuronul():"+listaNeuroni.get(i).coordonataX+" "+listaNeuroni.get(i).coordonataY + " de la pozitia " + i);
			}
		}
	}
	
	//metoda ce verifica optiunile deja alese pentru un neuron si alege urmatoarea optiune libera
	public String verificOptiunileNeuronului( int coordX, int coordY, ArrayList<Neuron> listaNeuroni ) {
		int loculDinLista=-1;
		String resultat="nimic";
		for(int i=0; i < listaNeuroni.size(); i++) {
			if( coordX == listaNeuroni.get(i).coordonataX && coordY == listaNeuroni.get(i).coordonataY) {
				loculDinLista=i;
				System.out.println("verificOptiunileNeuronului() Neuron "+ listaNeuroni.get(i).coordonataX +","+listaNeuroni.get(i).coordonataY +" exista la pozitia "+i+ " ***");
				if( listaNeuroni.get(i).optiuneaSus[0]==0 ) {
					listaNeuroni.get(i).optiuneaSus[0] = 1; // marcam optiunea aleasa sau verificata
					if( listaNeuroni.get(i).optiuneaSus[1] == 1 ) {
						resultat = "sus";  
					}
				}
				else if( listaNeuroni.get(i).optiuneaJos[0]==0 ) {
					listaNeuroni.get(i).optiuneaJos[0] = 1; // marcam optiunea aleasa sau verificata
					if( listaNeuroni.get(i).optiuneaJos[1] == 1 ) {
						resultat = "jos";
					}
				}
				else if( listaNeuroni.get(i).optiuneaStanga[0]==0 ) {
					listaNeuroni.get(i).optiuneaStanga[0] = 1; // marcam optiunea aleasa sau verificata
					if( listaNeuroni.get(i).optiuneaStanga[1] == 1 ) {
						resultat = "stanga"; 
					}
				}
				else if( listaNeuroni.get(i).optiuneaDreapta[0]== 0 ) {
					listaNeuroni.get(i).optiuneaDreapta[0] = 1; // marcam optiunea aleasa sau verificata
					if( listaNeuroni.get(i).optiuneaDreapta[1] == 1 ) {
						resultat = "dreapta";
					}
				}else if(listaNeuroni.get(loculDinLista).optiuneaSus[0]==1 && listaNeuroni.get(loculDinLista).optiuneaJos[0]==1 
						&& listaNeuroni.get(loculDinLista).optiuneaStanga[0]==1 && listaNeuroni.get(loculDinLista).optiuneaDreapta[0]==1){
					// daca toate optiunile sunt epuizate dar suntem in acelasi punct
					// punem toate optiunile (de la indicele 0) la valoarea 0 (nefolosite)
					// dar nu atingem optiunile de la indicele 1 care indica daca sunt posibile optiunile
					listaNeuroni.get(loculDinLista).optiuneaSus[0]=0 ; listaNeuroni.get(loculDinLista).optiuneaJos[0]=0 ; 
					listaNeuroni.get(loculDinLista).optiuneaStanga[0]=0 ; listaNeuroni.get(loculDinLista).optiuneaDreapta[0]=0;
					listaNeuroni.get(0).obtineNeuron( coordX, coordY, listaNeuroni );
				}
			}
		} 
		System.out.println("Datele optiunilor dupa ce verificOptiunileNeuronului() ");
		System.out.println("optiuneaSus[0]:"+listaNeuroni.get(loculDinLista).optiuneaSus[0]+ " optiuneaSus[1]:"+listaNeuroni.get(loculDinLista).optiuneaSus[1]);
		System.out.println("optiuneaJos[0]:"+listaNeuroni.get(loculDinLista).optiuneaJos[0]+ " optiuneaJos[1]:"+listaNeuroni.get(loculDinLista).optiuneaJos[1]);
		System.out.println("optiuneaStanga[0]:"+listaNeuroni.get(loculDinLista).optiuneaStanga[0]+ " optiuneaStanga[1]:"+listaNeuroni.get(loculDinLista).optiuneaStanga[1]);
		System.out.println("optiuneaDreapta[0]:"+listaNeuroni.get(loculDinLista).optiuneaDreapta[0]+ " optiuneaDreapta[1]:"+listaNeuroni.get(loculDinLista).optiuneaDreapta[1]);
		System.out.println("_______________________________________________");	
		System.out.println("Rezultatul ales:" + resultat);
		System.out.println("--------------------------------------------");
		return resultat;
	}
	
	//doar DEBUG , nu e necesar sa chemam aceasta metoda care imprima datele unui neuron din lista
	public int obtineNeuron(int coordX, int coordY, ArrayList<Neuron> listaNeuroni) {
		System.out.println("--------------------------------------------");
		int rezultat=-1;
		for(int i=0; i < listaNeuroni.size(); i++) {
			if( coordX == listaNeuroni.get(i).coordonataX && coordY == listaNeuroni.get(i).coordonataY) {
				System.out.println("obtineNeuron() la coordonatele X:" + listaNeuroni.get(i).coordonataX + " Y:" + listaNeuroni.get(i).coordonataY + " ne-am lovit si avem:");
				System.out.println("Optiunea SUS    :" + listaNeuroni.get(i).optiuneaSus[0] + " posibila:" + listaNeuroni.get(i).optiuneaSus[1] );
				System.out.println("Optiunea JOS    :" + listaNeuroni.get(i).optiuneaJos[0] + " posibila:" + listaNeuroni.get(i).optiuneaJos[1] );
				System.out.println("Optiunea STANGA :" + listaNeuroni.get(i).optiuneaStanga[0] + " posibila:" + listaNeuroni.get(i).optiuneaStanga[1] );
				System.out.println("Optiunea DREAPTA:" + listaNeuroni.get(i).optiuneaDreapta[0] + " posibila:" + listaNeuroni.get(i).optiuneaDreapta[1] );
				System.out.println("pozitia coincide la i="+i);
			rezultat=i;
			}
		}
		System.out.println("--------------------------------------------");
		return rezultat;
	}
	
	// impprima toata lista de neuroni cu datele lor
	public void imprimaListaNeuroniNeuron(ArrayList<Neuron> listaNeuroni) {
		System.out.println("--------------------------------------------");
		System.out.println("Creierul (lista) are acum " + listaNeuroni.size() + " neuroni");
		for(int i=0; i < listaNeuroni.size(); i++) {
			System.out.println("La pozitia "+i + " avem neuronul:"+
			"La coordonatele X:" + listaNeuroni.get(i).coordonataX + " Y:" + listaNeuroni.get(i).coordonataY + " cu:");
			System.out.println("Optiunea SUS     = " + listaNeuroni.get(i).optiuneaSus[0] + " ; Posibila=" + listaNeuroni.get(i).optiuneaSus[1] );
			System.out.println("Optiunea JOS     = " + listaNeuroni.get(i).optiuneaJos[0] + " ; Posibila=" + listaNeuroni.get(i).optiuneaJos[1] );
			System.out.println("Optiunea STANGA  = " + listaNeuroni.get(i).optiuneaStanga[0] + " ; Posibila=" + listaNeuroni.get(i).optiuneaStanga[1] );
			System.out.println("Optiunea DREAPTA = " + listaNeuroni.get(i).optiuneaDreapta[0] + " ; Posibila=" + listaNeuroni.get(i).optiuneaDreapta[1] );			
		}
		System.out.println("--------------------------------------------");
	}
}
