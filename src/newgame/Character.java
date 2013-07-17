package newgame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Charakters fest
 * @author Tobias
 * Die Klasse muss "Serializable" implementieren, damit sie speicherbar wird
 *
 */
public class Character extends Movement implements Serializable {
	
	private static final long serialVersionUID = 1L; // weist der Character eine ID zu damit er geladen werden kann
	static Image image; // Bilder sind static, damit sie nicht neu geladen werden m�ssen
    private int width;
    private int height;
    private int x,y;

	static ImageIcon u = new ImageIcon("src/Resources/Character.png");			// holt sich die noetigen Grafiken fuer den Charakter
	static ImageIcon dr = new ImageIcon("src/Resources/digright.png");
	static ImageIcon dl = new ImageIcon("src/Resources/digleft.png");
	static ImageIcon du = new ImageIcon("src/Resources/digup.png");
	static ImageIcon db = new ImageIcon("src/Resources/digb.png");
	public Character(int x, int y){
		super(x,y);
		image = u.getImage();
		this.setImage(image);
	    width = image.getWidth(null);									// fuer die Kollision des Bildes mit Schuss
	    height = image.getHeight(null);
	}
	
	public void move(int x, int y){										// fuehrt die Berechnung zb. fuer rechts aus(BLOCK,0)
		int nx = this.getX() + x;										// legt die Bewegungsbedingungen fest
		int ny = this.getY() + y;
		this.setX(nx);
		this.setY(ny);
	}
	
		public Rectangle getBounds() {
	        return new Rectangle(x, y, width, height);
	    }
}
