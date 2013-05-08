package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Character extends Movement {
	
	Image image;
	ImageIcon u = new ImageIcon("src/Resources/Character.png");
	
	public Character(int x, int y){
		super(x,y);
		image = u.getImage();
		this.setImage(image);
	}
	
	public void move(int x, int y){						// f�hrt die Berechnung zb. f�r rechts (BLOCK,0)
		int nx = this.getX() + x;
		int ny = this.getY() + y;
		this.setX(nx);
		this.setY(ny);
	}

}
