package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Checkpoints fest
 * @author Tobias
 *
 */
public class checkpoint extends Movement {

	Image image;

	ImageIcon u = new ImageIcon("src/Resources/checkpoint.png");			// holt sich die noetigen Grafiken fuer den Checkpoint
	
	public checkpoint(int x, int y){
		super(x,y);
		image = u.getImage();
		this.setImage(image);
	}

	
	
}
