package application;


import javafx.scene.control.Button;


public class Wire extends Button{
	int x,y;
	double scalex,scaley;
	
	public Wire(int x, int y, double scalex, double scaley) {
		
        this.setScaleX(scalex);
        this.setScaleY(scaley);
		//this.setText("Wire");
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setStyle("-fx-background-color: transparent;");
		//this.setVisible(false);

	}
}
