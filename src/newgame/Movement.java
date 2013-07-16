package newgame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * Setzt die Bedingungen fuer die Bewegung fest
 * @author Tobias
 *
 */
public class Movement implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private transient Image image;
	protected boolean visible;
	private int width;
	private int height;
	private int x,y;
	
	public Movement(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setVisible(boolean visible) {
	        this.visible = visible;
	}

	public boolean isVisible() {
	        return visible;
	}
	
	public Image getImage(){
		return this.image;
	}
	
	public void setImage(Image img){
		image = img;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX (int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public Rectangle getBounds() {
			return new Rectangle(x, y, width, height);
	    }
}

