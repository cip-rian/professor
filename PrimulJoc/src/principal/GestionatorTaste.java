package principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestionatorTaste implements KeyListener{

	public boolean susApasat, josApasat, stangaApasat, dreaptaApasat;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP) {
			susApasat = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			josApasat = true;
		}
		if(code == KeyEvent.VK_LEFT) {
			stangaApasat = true;
		}
		if(code == KeyEvent.VK_RIGHT) {
			dreaptaApasat = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP) {
			susApasat = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			josApasat = false;
		}
		if(code == KeyEvent.VK_LEFT) {
			stangaApasat = false;
		}
		if(code == KeyEvent.VK_RIGHT) {
			dreaptaApasat = false;
		}
	}
}
