package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Todesfeld fest
 *
 */
public class Todesfeld extends Movement {

  Image image;

	ImageIcon u = new ImageIcon("src/Resources/wand1.png");			// holt sich die noetigen Grafiken fuer das Todesfeld
	
	public Todesfeld(int x, int y){
		super(x,y);
		image = u.getImage();
		this.setImage(image);
	}

	
	
}
