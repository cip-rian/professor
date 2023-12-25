package principal;

import java.io.IOException;

import javax.swing.JFrame;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class PrimulJoc {
	
	public static void main(String[] args) {
		JFrame fereastra = new JFrame();
		fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fereastra.setResizable(false);
		fereastra.setTitle("Professor joc");
		
		Panou panoulJocului = new Panou();	
		fereastra.add(panoulJocului);
		fereastra.pack();		
		fereastra.setLocationRelativeTo(null);
		fereastra.setVisible(true);
		
		panoulJocului.pornesteFirulJocului();
		
//		Configuration configuratie = new Configuration();
//		configuratie.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
//		configuratie.setDictionaryPath("resurse\\resurse\\professor.dic");
//		configuratie.setLanguageModelPath("resurse\\resurse\\professor.lm");
//		try {
//			LiveSpeechRecognizer vorbirea = new LiveSpeechRecognizer(configuratie);
//			vorbirea.startRecognition(true);
//			SpeechResult rezultatulComenzii = null;
//			while( (rezultatulComenzii = vorbirea.getResult()) != null ) {
//				String comandaData = rezultatulComenzii.getHypothesis();
//				System.out.println("Ai dat comanda:" + comandaData);
//				
//				if(comandaData.equalsIgnoreCase("Left")) {
//					panoulJocului.gestiuneTaste.stangaApasat = true;
//					panoulJocului.gestiuneTaste.dreaptaApasat = false;
//					panoulJocului.gestiuneTaste.susApasat = false;
//					panoulJocului.gestiuneTaste.josApasat = false;
//				}else if(comandaData.equalsIgnoreCase("Right")) {
//					panoulJocului.gestiuneTaste.dreaptaApasat = true;
//					panoulJocului.gestiuneTaste.stangaApasat = false;
//					panoulJocului.gestiuneTaste.susApasat = false;
//					panoulJocului.gestiuneTaste.josApasat = false;
//				}else if(comandaData.equalsIgnoreCase("Up")) {
//					panoulJocului.gestiuneTaste.susApasat = true;
//					panoulJocului.gestiuneTaste.dreaptaApasat = false;
//					panoulJocului.gestiuneTaste.stangaApasat = false;
//					panoulJocului.gestiuneTaste.josApasat = false;
//				}else if(comandaData.equalsIgnoreCase("Down")) {
//					panoulJocului.gestiuneTaste.josApasat = true;
//					panoulJocului.gestiuneTaste.dreaptaApasat = false;
//					panoulJocului.gestiuneTaste.stangaApasat = false;
//					panoulJocului.gestiuneTaste.susApasat = false;
//				}
//				
//			}
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		
	}

}
