package newgame;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Setzte das Bild und die Masse des Luftschwertes fest
 * @author Tobias
 *
 */
public class Airswordicon extends Movement {

private Image image;
private int width;
private int height;
private int x, y;

	public Airswordicon(int x, int y){
		super(x,y);
		ImageIcon i = new ImageIcon("src/Resources/airsword.png");					// holt sich die Grafik fuer das Schwert, das in Board aufgerufen wird
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