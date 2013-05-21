package newgame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy extends Movement {

private Image image;

	public Enemy(int x, int y){
		super(x,y);

		ImageIcon i = new ImageIcon("src/Resources/fire.png");					// holt sich die Grafiken f�r das Feuer, die im Board eingebunden werden
		image = i.getImage();
		this.setImage(image);

	}

}