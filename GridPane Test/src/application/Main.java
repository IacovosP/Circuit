package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,680,440);
			
			ArrayList<Wire> wireArrayList = new ArrayList<Wire>();
			//horizontal
			wireArrayList.add(new Wire(247, 40, 3.1, 0.3));
			wireArrayList.add(new Wire(317, 40, 3.1, 0.3));
			wireArrayList.add(new Wire(247, 110, 3.1, 0.3));
			wireArrayList.add(new Wire(317, 110, 3.1, 0.3));
			wireArrayList.add(new Wire(247, 180, 3.1, 0.3));
			wireArrayList.add(new Wire(317, 180, 3.1, 0.3));
			//vertical
			wireArrayList.add(new Wire(212, 74, 0.4, 2.0));
			wireArrayList.add(new Wire(282, 74, 0.4, 2.0));
			wireArrayList.add(new Wire(352, 74, 0.4, 2.0));
			wireArrayList.add(new Wire(212, 144, 0.4, 2.0));
			wireArrayList.add(new Wire(282, 144, 0.4, 2.0));
			wireArrayList.add(new Wire(352, 144, 0.4, 2.0));

	        

			for (Wire wire : wireArrayList) {
				wire.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						//wire.setVisible(true);
						//if (wire.getStyle()=="-fx-base: #b6e7c9;")
							//wire.setStyle(null);
						//else wire.setStyle("-fx-base: #b6e7c9;");
						if (wire.getStyle()=="-fx-background-color: transparent;")
							wire.setStyle("-fx-background-color: green;");
						else wire.setStyle("-fx-background-color: transparent;");
						//wire.setStyle("-fx-base: #b6e7c9;");
						
					}
				});
			}
			
			//set images here
			Image image = new Image("application/battery.png", 60, 60, false, false);
			Image resistor=new Image("application/resistor.jpg",60,60,false,false);
			Image square = new Image("application/square.png", 20, 20, false, false);
			Image lamp=new Image("application/lamp.jpg",60,60,false,false);
			
			ArrayList<Component> componentArrayList = new ArrayList<Component>();
			//add component here
			componentArrayList.add(new Component(30, 50, image));
			componentArrayList.add(new Component(30, 110, resistor));
			componentArrayList.add(new Component(30, 170, lamp));
			
			
			for (Component component : componentArrayList) {
				component.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		                /* drag was detected, start drag-and-drop gesture*/
		                System.out.println("onDragDetected");
		                
		                /* allow any transfer mode */
		                Dragboard db = component.startDragAndDrop(TransferMode.ANY);
		                
		                /* put a string on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putImage(component.getImage());
		                //content.putString(source.getText());
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });
	        }
			
			ArrayList<Target> targetArrayList = new ArrayList<Target>();
			//add component here
			targetArrayList.add(new Target(210, 42, square));
			targetArrayList.add(new Target(280, 42, square));
			targetArrayList.add(new Target(350, 42, square));
			targetArrayList.add(new Target(210, 112, square));
			targetArrayList.add(new Target(280, 112, square));
			targetArrayList.add(new Target(350, 112, square));
			targetArrayList.add(new Target(210, 182, square));
			targetArrayList.add(new Target(280, 182, square));
			targetArrayList.add(new Target(350, 182, square));
			
			
			for (Target target : targetArrayList) {
				target.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		                /* drag was detected, start drag-and-drop gesture*/

		                /* allow any transfer mode */
		                Dragboard db = target.startDragAndDrop(TransferMode.ANY);
		                
		                /* put a string on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putImage(target.getImage());
		                //content.putString(source.getText());
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });
	        }
			for (Target target : targetArrayList) {
				target.setOnDragOver(new EventHandler <DragEvent>() {
	            	public void handle(DragEvent event) {
	                	/* data is dragged over the target */
	                	System.out.println("onDragOver");
	                
	                	/* accept it only if it is  not dragged from the same node 
	                 	* and if it has a string data */
	                	if (event.getGestureSource() != target &&
	                        	event.getDragboard().hasImage()) {
	                    	/* allow for both copying and moving, whatever user chooses */
	                    	event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                	}
	                	if (target.getImage()!=square){
		                	target.setImage(square);
							target.setFitHeight(20);
							target.setFitWidth(20);
	                	}

	                	event.consume();
	            	}
	        	});
			}
			
			for (Target target : targetArrayList) {
				target.setOnDragDropped(new EventHandler <DragEvent>() {
					public void handle(DragEvent event) {
						/* data dropped */
						System.out.println("onDragDropped");
						/* if there is a string data on dragboard, read it and use it */
						Dragboard db = event.getDragboard();
						boolean success = false;
						if (db.hasImage()) {
							target.setImage(db.getImage());
							target.setFitHeight(30);
							target.setFitWidth(30);
							success = true;
						}
						/* let the source know whether the string was successfully 
						 * transferred and used */
						event.setDropCompleted(success);
	                
						event.consume();
					}
				});
			}
				
			ArrayList<Line> lines = new ArrayList<Line>();
			lines.add(new Line(220, 50, 220, 190));
			lines.add(new Line(290, 50, 290, 190));
			lines.add(new Line(220, 120, 360, 120));
			lines.add(new Line(220, 190, 360, 190));
			lines.add(new Line(220, 50, 360, 50));
			lines.add(new Line(360, 50, 360, 190));
	       
			
	        //Rectangle target = new Rectangle(215,45,15,15);
	        //target.setFill(Color.RED);
	        //target.setStrokeWidth(3);
	        
	        
	        root.getChildren().addAll(componentArrayList);
	        root.getChildren().addAll(lines);
	        root.getChildren().addAll(targetArrayList);
	        root.getChildren().addAll(wireArrayList);
	        stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
