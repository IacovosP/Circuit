package application;

import javafx.scene.control.Button;


public class Wire extends Button{
	int x, y, node1, node2;
	double scalex, scaley;
	Matrix matrix;
	
	public Wire(int x, int y, double scalex, double scaley) {
		
        this.setScaleX(scalex);
        this.setScaleY(scaley);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setStyle("-fx-background-color: transparent;");
	}
	
	public Wire(int x, int y, double scalex, double scaley, int node1, int node2, Matrix matrix) {
		
		this.setStyle("-fx-background-color: transparent;");
		this.setScaleX(scalex);
        this.setScaleY(scaley);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.node1 = node1;
		this.node2 = node2;
		this.matrix = matrix;
	}
	
	public void toggleConnetion() throws MatrixException {
		if (matrix.getElement(node1,node2) == 0)
			matrix.setElement(node1,node2,1);
		else
			matrix.setElement(node1,node2,0);			
	}
}
