package newgame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Setzte die Bedingungen des Eisschwertes fest
 * So zB das Bild und die return-Variablen
 * @author Tobias
 *
 */
public class Icesword{

	private int x,y;
	Image img;
	Image image;
	boolean visible;
	private int width, height;
	private int sword_die = 50;
	
	public Icesword(int x, int y){
		ImageIcon newSword = new ImageIcon("src/Resources/swordb.png");
		img = newSword.getImage();
		visible = true;
		this.x = x;
		this.y = y;
        width = img.getWidth(null);
        height = img.getHeight(null);
    }
	
	public int getX() {
		return x;
	}

	public int getY() {
	    return y;
	}
	
	public boolean isVisible() {
	        return visible;
	}

	public void setVisible(Boolean visible) {								// setzt Kollision im Board fest
	        this.visible = visible;
	}

	public Rectangle getBounds() {											// Erkennung der Kollision
	        return new Rectangle(x, y, width, height);
	}

	public Image getImage(){
		return img;
	}
	
	public void move_r_sword(){
		x += sword_die;
		shot_limit_x1();					
	}											
	
	public void move_l_sword(){	
		x -= sword_die;
		shot_limit_x2();
	}
	
	public void move_u_sword(){								 
		y -= sword_die;
		shot_limit_y1();
	}
	
	public void move_d_sword(){								
		y += sword_die;
		shot_limit_y2();	
	}
	
	public void shot_limit_x1(){
		if (x >= 100)
	         visible = false;
	}
	
	public void shot_limit_y1(){
		 if (y>=100)
	         visible = false;						
	
	}
	
	public void shot_limit_x2(){
		 if (x >=100)
	         visible = false;						
	}
	
	public void shot_limit_y2(){
		 if (y<=100)
	         visible = false;						
	}
}
