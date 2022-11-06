package principal;

import entitati.Entitate;

public class VerificColiziune {

	Panou panoulJocului;
	
	public VerificColiziune(Panou panouJoc) {
		this.panoulJocului = panouJoc;	
	}
	
	public void verificDala(Entitate entitate) {
		int entitateStangaWorldX = entitate.worldX + entitate.zonaSolida.x;
		int entitateDreaptaWorldX = entitate.worldX + entitate.zonaSolida.x + entitate.zonaSolida.width;
		int entitateSusWorldY = entitate.worldY + entitate.zonaSolida.y;
		int entitateJosWorldY = entitate.worldY + entitate.zonaSolida.y + entitate.zonaSolida.height;
		
		int entitateColoanaStanga = entitateStangaWorldX / panoulJocului.marimeaDale;
		int entitateDreaptaColoana = entitateDreaptaWorldX / panoulJocului.marimeaDale;
		int entitateRandSus = entitateSusWorldY / panoulJocului.marimeaDale;
		int entitateRandJos = entitateJosWorldY / panoulJocului.marimeaDale;
		
		int dalaNum1, dalaNum2;
		
		switch(entitate.directia) {
			case "sus":
				entitateRandSus= (entitateSusWorldY - entitate.viteza) / panoulJocului.marimeaDale;
				dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandSus];
				dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandSus];
				if(panoulJocului.gestiuneDale.dale[dalaNum1].coliziune == true || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune == true) {
					entitate.coliziunePornita = true;
				}
				break;
			case "jos":
				entitateRandJos = (entitateJosWorldY + entitate.viteza) / panoulJocului.marimeaDale;
				dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandJos];
				dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandJos]; 
				if(panoulJocului.gestiuneDale.dale[dalaNum1].coliziune == true || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune == true) {
					entitate.coliziunePornita = true;
				}
				break;
			case "stanga":
				entitateColoanaStanga = (entitateStangaWorldX - entitate.viteza) / panoulJocului.marimeaDale;
				dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandSus];
				dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandJos]; 
				if(panoulJocului.gestiuneDale.dale[dalaNum1].coliziune == true || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune == true) {
					entitate.coliziunePornita = true;
				}
				break;
			case"dreapta":
				entitateDreaptaColoana = (entitateDreaptaWorldX + entitate.viteza) / panoulJocului.marimeaDale;
				dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandSus];
				dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandJos]; 
				if(panoulJocului.gestiuneDale.dale[dalaNum1].coliziune == true || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune == true) {
					entitate.coliziunePornita = true;
				}
				break;			
		}
	}	
}
