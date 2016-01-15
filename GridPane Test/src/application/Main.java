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
			int GRID_SIZE = 6;
			Group root = new Group();
			Scene scene = new Scene(root,680,640);
			Matrix matrix = new Matrix(GRID_SIZE, GRID_SIZE);
	

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
	        
	        final Label label4 = new Label("10 Ohm");
	        label4.setStyle("-fx-font: 15 'Times New Roman';");
	        label4.setLayoutX(100);
			label4.setLayoutY(130);
	        
	        
	        TextField textField2 = new TextField ("10");
	        textField2.setMaxWidth(80);
	        textField2.setLayoutX(150);
			textField2.setLayoutY(530);
			
			Button resistorSbmt = new Button("Submit R");
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
		                label4.setText(String.valueOf(resistorVal) + " Ohm");
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
			
		    /////Load
		    
		    Button load = new Button("load");
		    
		    load.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent e) {
		            
		        }
		    });
		    
		    ////Save
		    
		    Button save = new Button("save");
		    save.setLayoutX(50);
		    save.setOnAction(new EventHandler<ActionEvent>() {
		    	
		        @Override
		        public void handle(ActionEvent e) {
		            
		        }
		    });
		    
		    
		    
		    
		    
			ArrayList<Line> lines = new ArrayList<Line>();
			
			lines.add(new Line(220, 50, 220, 410));
			lines.add(new Line(290, 50, 290, 410));
			lines.add(new Line(360, 50, 360, 410));
			lines.add(new Line(430, 50, 430, 410));
			lines.add(new Line(500, 50, 500, 410));
			lines.add(new Line(570, 50, 570, 410));
			lines.add(new Line(220, 51.5, 580, 51.5));
			lines.add(new Line(220, 121.5, 580, 121.5));
			lines.add(new Line(220, 191.5, 580, 191.5));
			lines.add(new Line(220, 261.5, 580, 261.5));
			lines.add(new Line(220, 331.5, 580, 331.5));
			lines.add(new Line(220, 401.5, 580, 401.5));
			
			
			ArrayList<Wire> wireArrayList = new ArrayList<Wire>();
			int startHX = 247, startHY = 38, startVX = 211, startVY = 74, wireDist = 70;
			
			for (int i = 0; i < GRID_SIZE; i ++) 
				for (int j = 0; j < GRID_SIZE; j ++) {
					wireArrayList.add(new Wire(startHX+wireDist*i,startHY+wireDist*j,3.1,0.3,(i-1)*GRID_SIZE+j+1,(i-1)*GRID_SIZE+j,matrix)); //horizontal lines
					wireArrayList.add(new Wire(startVX+wireDist*i,startVY+wireDist*j,0.4,2.0,i*GRID_SIZE+j,(i-1)*GRID_SIZE+j,matrix)); //vertical lines
				} 

			for (Wire wire : wireArrayList) {
				wire.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						
						if (wire.getStyle()=="-fx-background-color: transparent;")
							wire.setStyle("-fx-background-color: green;");
						else wire.setStyle("-fx-background-color: transparent;");
						
						try {
							wire.toggleConnetion();
						} catch (MatrixException e1) {
							e1.printStackTrace();
						}
						wire.toFront();
								
					}
				});
				
				wire.setOnMouseEntered(new EventHandler<MouseEvent>(){
					@Override public void handle(MouseEvent e) {
						currentVal = (float)batteryVal/resistorVal;
						if (wire.getStyle()==("-fx-background-color: green;")){
						Tooltip.install(
							    wire,
							    new Tooltip((batteryVal)+ "V" + "\n"+ currentVal + "A")
						);
						}
					
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
		                
		                /* put an image on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putImage(component.getImage());
		               
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });
	        }
			
			ArrayList<Target> targetArrayList = new ArrayList<Target>();
			//add component here
			int X = 210, Y = 42, buffer = 70;
			
			for (int counterX = 0; counterX < GRID_SIZE ; counterX++) {
				for (int counterY = 0; counterY < GRID_SIZE ; counterY++) {
					
					targetArrayList.add(new Target(X+buffer*counterY, Y+buffer*counterX, square));	 
				}	
			}
			
			
			
			for (Target target : targetArrayList) {
				target.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		                /* drag was detected, start drag-and-drop gesture*/

		                /* allow any transfer mode */
		                Dragboard db = target.startDragAndDrop(TransferMode.ANY);
		                
		                /* put an image on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putImage(target.getImage());
		          
		                db.setContent(content);
		                
		                if (target.getImage()!=square){
		                	target.setImage(square);
							target.setFitHeight(20);
							target.setFitWidth(20);
							target.setLayoutX(target.getLayoutX()+5);
							target.setLayoutY(target.getLayoutY()+5);
	                	}
		                
		                event.consume();
		            }
		        });
				target.setOnDragOver(new EventHandler <DragEvent>() {
	            	public void handle(DragEvent event) {
	                	/* data is dragged over the target */
	                	System.out.println("onDragOver");
	                
	                	/* accept it only if it is  not dragged from the same node 
	                 	* and if it has a image */
	                	if (event.getGestureSource() != target &&
	                        	event.getDragboard().hasImage()) {
	                    	/* allow for both copying and moving, whatever user chooses */
	                    	event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                	}
	                	

	                	event.consume();
	            	}
	        	});
				target.setOnDragDropped(new EventHandler <DragEvent>() {
					public void handle(DragEvent event) {
						/* data dropped */
						System.out.println("onDragDropped");
						/* if there is an image on dragboard, read it and use it */
						Dragboard db = event.getDragboard();
						boolean success = false;
						if (target.getImage()!=square){
							target.setLayoutX(target.getLayoutX()+5);
							target.setLayoutY(target.getLayoutY()+5);
						}
						if (db.hasImage()) {
							target.setImage(db.getImage());
							target.setFitHeight(30);
							target.setFitWidth(30);
							target.setLayoutX(target.getLayoutX()-5);
							target.setLayoutY(target.getLayoutY()-5);
							success = true;
						}
						/* let the source know whether was successfully 
						 * transferred and used */
						event.setDropCompleted(success);
	                
						event.consume();
					}
				});
	        }
			
	       
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
	        root.getChildren().add(load);
	        root.getChildren().add(save);
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
