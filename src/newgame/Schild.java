package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Schildes fest
 *
 */
public class Schild extends Movement {

private Image image;

  public Schild(int x, int y){	
		
		super(x,y);

		ImageIcon i = new ImageIcon("src/Resources/schild.png");					// holt sich die Grafiken fuer das Schild, wird im Board aufgerufen
		image = i.getImage();
		this.setImage(image);

	}

}
