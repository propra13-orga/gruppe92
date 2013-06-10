package newgame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Character extends Movement {
	
	Image image;
    private int width;
    private int height;
    private int x,y;

	ImageIcon u = new ImageIcon("src/Resources/Character.png");			// holt sich die noetigen Grafiken fuer den Charakter
	
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
