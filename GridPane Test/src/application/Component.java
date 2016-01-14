package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Component extends ImageView {
	
	int x,y;
	Image image;
	
	public Component(int x, int y, Image image) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setImage(image);
	}

}
