package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Rätselblock fest
 *
 */
public class Raetselblock extends Movement {

private Image image;

  public Raetselblock(int x, int y){	
		
		super(x,y);

		ImageIcon i = new ImageIcon("src/Resources/wand1.png");					// holt sich die Grafiken fuer den Rätselblock, wird im Board aufgerufen
		image = i.getImage();
		this.setImage(image);
	}

}
