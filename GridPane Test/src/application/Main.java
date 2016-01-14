package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	public int batteryVal = 9, resistorVal = 10;
	public float currentVal;
	
	@Override
	public void start(Stage stage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,680,640);
			
			//Buttons to submit/edit da components
			

	        // Change Battery Voltage
	        final Label label1 = new Label("Voltage");
	        label1.setStyle("-fx-font: 15 'Times New Roman'; -fx-font-weight: bold;");
	        label1.setLayoutX(30);
			label1.setLayoutY(510);
	        
	        final Label label2 = new Label("9V");
	        label2.setStyle("-fx-font: 15 'Times New Roman';");
	        label2.setLayoutX(100);
			label2.setLayoutY(70);
	        
	        
	        TextField textField = new TextField ("9");
	        textField.setMaxWidth(80);
	        textField.setLayoutX(30);
			textField.setLayoutY(530);
			
			Button batterySbmt = new Button("Submit V");
		    batterySbmt.setStyle("-fx-font: 15 'Times New Roman';");
		    batterySbmt.setMaxWidth(80);
		    batterySbmt.setMaxHeight(12);
		    batterySbmt.setLayoutX(30);
		    batterySbmt.setLayoutY(560);
		    
		    batterySbmt.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent e) {
		            if ((textField.getText() != null && !textField.getText().isEmpty())) {
		                try{
		                batteryVal = Integer.parseInt(textField.getText());
		                label2.setText(String.valueOf(batteryVal) + "V");
		                currentVal = (float)batteryVal/resistorVal;
		            
		                }
		                catch (NumberFormatException nfe){
		                textField.setText("Invalid input!");
		                batteryVal = 9;
		                }    
		                System.out.println(batteryVal);
		            } else {
		                textField.setText("Invalid input!");
		            }
		     }
		 });
			
		    //Change resistors
		    final Label label3 = new Label("Resistance");
	        label3.setStyle("-fx-font: 15 'Times New Roman'; -fx-font-weight: bold;");
	        label3.setLayoutX(150);
			label3.setLayoutY(510);
	        
	        final Label label4 = new Label("10Ω");
	        label4.setStyle("-fx-font: 15 'Times New Roman';");
	        label4.setLayoutX(100);
			label4.setLayoutY(130);
	        
	        
	        TextField textField2 = new TextField ("10");
	        textField2.setMaxWidth(80);
	        textField2.setLayoutX(150);
			textField2.setLayoutY(530);
			
			Button resistorSbmt = new Button("Submit Ω");
		    resistorSbmt.setStyle("-fx-font: 15 'Times New Roman';");
		    resistorSbmt.setMaxWidth(80);
		    resistorSbmt.setMaxHeight(12);
		    resistorSbmt.setLayoutX(150);
		    resistorSbmt.setLayoutY(560);
		    
		    resistorSbmt.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent e) {
		            if ((textField2.getText() != null && !textField2.getText().isEmpty())) {
		                try{
		                resistorVal = Integer.parseInt(textField2.getText());
		                label4.setText(String.valueOf(resistorVal) + "Ω");
		                currentVal = (float)batteryVal/resistorVal;
		            
		                }
		                catch (NumberFormatException nfe){
		                textField2.setText("Invalid input!");
		                resistorVal = 10;
		                }    
		                System.out.println(resistorVal);
		            } else {
		                textField2.setText("Invalid input!");
		            }
		     }
		 });
			
			
			
			
			ArrayList<Wire> wireArrayList = new ArrayList<Wire>();
			//horizontal
			wireArrayList.add(new Wire(247, 37, 3.1, 0.3));
			wireArrayList.add(new Wire(317, 37, 3.1, 0.3));
			wireArrayList.add(new Wire(247, 107, 3.1, 0.3));
			wireArrayList.add(new Wire(317, 107, 3.1, 0.3));
			wireArrayList.add(new Wire(247, 177, 3.1, 0.3));
			wireArrayList.add(new Wire(317, 177, 3.1, 0.3));
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
						wire.toBack();
						
						
						//wire.setStyle("-fx-base: #b6e7c9;");
						
					}
				});
			}
			

			for (Wire wire : wireArrayList) {
				wire.setOnMouseEntered(new EventHandler<MouseEvent>(){
					@Override public void handle(MouseEvent e) {
						currentVal = (float)batteryVal/resistorVal;
						if (wire.getStyle()=="-fx-background-color: green;"){
							Tooltip.install(
								    wire,
								    new Tooltip((batteryVal)+ "V" + "\n"+ resistorVal +"Ω" + "\n"+ currentVal + "A")
								);	
						}
						
						//wire.setStyle("-fx-base: #b6e7c9;");
						
					}
				});
			}
			
			//set images here
			Image image = new Image("application/battery.png", 60, 60, false, false);
			Image resistor=new Image("application/resistor.jpg",60,60,false,false);
			Image square = new Image("application/Empty+sqaure.jpg", 20, 20, false, false);
			Image lamp=new Image("application/lamp.jpg",60,60,false,false);
			Image voltmeter=new Image("application/Voltmeter.jpg",60,60,false,false);
			Image ampermeter=new Image("application/IEC-Ampere-Meter-Symbol.jpg",60,60,false,false);
			
			ArrayList<Component> componentArrayList = new ArrayList<Component>();
			//add component here
			componentArrayList.add(new Component(30, 40, image));
			componentArrayList.add(new Component(30, 120, resistor));
			componentArrayList.add(new Component(30, 190, lamp));
			componentArrayList.add(new Component(30, 270, voltmeter));
			componentArrayList.add(new Component(30, 350, ampermeter));
			
			
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
			int GRID_SIZE = 6;
			int X = 210;
			int Y = 42;
			int buffer = 70;
			
			for (int counterX = 0; counterX < GRID_SIZE ; counterX++) {
				for(int counterY = 0; counterY < GRID_SIZE ; counterY++){
					
					targetArrayList.add(new Target(X+buffer*counterY, Y+buffer*counterX, square));	 
			}	
			}
			
			
					
		   /* targetArrayList.add(new Target(210, 42, square));
			targetArrayList.add(new Target(280, 42, square));
			targetArrayList.add(new Target(350, 42, square));
			targetArrayList.add(new Target(210, 112, square));
			targetArrayList.add(new Target(280, 112, square));
			targetArrayList.add(new Target(350, 112, square));
			targetArrayList.add(new Target(210, 182, square));
			targetArrayList.add(new Target(280, 182, square));
			targetArrayList.add(new Target(350, 182, square)); */
			
			
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
	        root.getChildren().add(batterySbmt);
	        root.getChildren().add(textField);
	        root.getChildren().add(label2);
	        root.getChildren().add(label1);
	        root.getChildren().add(resistorSbmt);
	        root.getChildren().add(textField2);
	        root.getChildren().add(label3);
	        root.getChildren().add(label4);
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
