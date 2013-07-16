package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Raetselwegs fest
 *
 */
public class Raetselweg extends Movement {

  Image image;

	ImageIcon u = new ImageIcon("src/Resources/grünerboden1.png");			// holt sich die noetigen Grafiken fuer den Raetselweg
	
	public Raetselweg(int x, int y){
		super(x,y);
		image = u.getImage();
		this.setImage(image);
	}

	
	
}
