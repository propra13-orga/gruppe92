package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Manatrankes fest
 * @author Tobias
 *
 */
public class Mana extends Movement {

private Image image;

	public Mana(int x, int y){	
		
		super(x,y);

		ImageIcon i = new ImageIcon("src/Resources/trank5030.png");					// holt sich die Grafiken fuer den Manatrank, wird im Board aufgerufen
		image = i.getImage();
		this.setImage(image);

	}

}
