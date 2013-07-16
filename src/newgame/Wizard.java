package newgame;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des NPC's fest
 * @author Tobias
 *
 */
public class Wizard extends Movement implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Image image;

	public Wizard(int x, int y){	
		
		super(x,y);

		ImageIcon i = new ImageIcon("src/Resources/shopkeeper.png");					// holt sich die Grafiken fuer den NPC, wird im Board aufgerufen
		image = i.getImage();
		this.setImage(image);

	}

}
