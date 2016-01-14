package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Target extends ImageView {
	
	int x,y;
	Image image;
	
	public Target(int x, int y, Image image) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setImage(image);
	}

}