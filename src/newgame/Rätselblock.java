package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Rätselblock fest
 *
 */
public class Rätselblock extends Movement {

private Image image;

  public Rätselblock(int x, int y){	
		
		super(x,y);

		ImageIcon i = new ImageIcon("src/Resources/wand1.png");					// holt sich die Grafiken fuer den Rätselblock, wird im Board aufgerufen
		image = i.getImage();
		this.setImage(image);
	}

}
