package newgame;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Feuericons fest
 * @author Tobias
 *
 */
public class Feuericon extends Movement {

private Image image;
private int width;
private int height;
private int x, y;

	public Feuericon(int x, int y){
		super(x,y);
		ImageIcon i = new ImageIcon("src/Resources/fireball.png");				// holt sich die Grafik fuer das Feuericon, das in Board aufgerufen wird
		image = i.getImage();
		this.setImage(image);
		   	width = image.getWidth(null);										// fuer Kollsion mit Schuss
	        height = image.getHeight(null);
	        visible = true;
	        this.x = x;
	        this.y = y;
	}
    
	public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {												// Kollision mit Schuss 
        return new Rectangle(x, y, width, height);
    }
 }