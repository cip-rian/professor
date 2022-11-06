package sunet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sunet {

		public Clip clip;
		AudioInputStream audioInputStream;
		
		public Sunet(String sunetDorit) {
			try {
				this.audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sunetDorit));
				this.clip = AudioSystem.getClip();
				this.clip.open(this.audioInputStream);
				
			}catch(Exception e) {
				System.out.println("Eroare de sunet:" + e);
			}
		}
}
