package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Main extends Application {
	
	final int COLUMN_NUM = 12;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			System.out.print("hi");
			primaryStage.setTitle("Schedule Visualizer");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			root.setPadding(new Insets(10));

			// creating window elements
			Text scheduleVisualizer = new Text("Augustana Schedule Visualizer");
			root.setAlignment(scheduleVisualizer, Pos.TOP_CENTER);
			root.setTop(scheduleVisualizer);

			TextArea textArea = new TextArea();
			root.setMargin(textArea, new Insets(20));
			root.setCenter(textArea);

			Button submit = new Button("Submit");
			root.setAlignment(submit, Pos.BOTTOM_CENTER);
			root.setMargin(submit, new Insets(10));
			root.setBottom(submit);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					GridPane newRoot = new GridPane();

					String input = textArea.getText();
					String[] splitInput = input.split("\n");
					int length = splitInput.length;
					int size = ((splitInput.length)/COLUMN_NUM);
					System.out.println(size);
					
			
					int count = COLUMN_NUM;
					
					
					for (int j = count; j < length; j++) {
						
						
						String[] courseInfo = new String[COLUMN_NUM];
						for (int i = 0; i < COLUMN_NUM; i++) {
							
							count++;
						}
						Course course = new Course(courseInfo);
					}
					
//					for (int i = 0; i < splitInput.length; i++) {
//						System.out.println(splitInput[i]);
//					}

//			         String[][] classMatrix = new String[size][COLUMN_NUM];
//			         int count = 0;
//			         for(int i = 0; i<size;i++) {
//			        	 for(int n = 0; i<COLUMN_NUM;n++) {
//			        		 String current = splitInput[count];
//			        		 classMatrix[i][n]="hi";
//			        		 count++;
//			        	 }
//			         }
//			         		         

					
			         
			         //adds days of the week to the calendar
			         Text monday = new Text("		Monday		");		
			         Text tuesday = new Text("		Tuesday		");			         
			         Text wednesday = new Text("		Wednesday		");			         
			         Text thursday = new Text("		Thursday		");			         
			         Text friday = new Text("		Friday		");			         
			         Text saturday = new Text("		Saturday		");			         
			         Text sunday = new Text("		Sunday		");			         

			         newRoot.add(monday, 1, 0);
			         newRoot.add(tuesday, 2, 0);
			         newRoot.add(wednesday, 3, 0);
			         newRoot.add(thursday, 4, 0);
			         newRoot.add(friday, 5, 0);
			         newRoot.add(saturday, 6, 0);
			         newRoot.add(sunday, 7, 0);
			         
			         //adds times to the calendar
			         Text eightAM = new Text("8:00am");
			         Text nineAM = new Text("9:00am");
			         Text tenAM = new Text("10:00am");
			         Text elevenAM = new Text("11:00am");
			         Text noon = new Text("12:00pm");
			         Text onePM = new Text("1:00pm");
			         Text twoPM = new Text("2:00pm");
			         Text threePM = new Text("3:00pm");
			         Text fourPM = new Text("4:00pm");
			         Text fivePM = new Text("5:00pm");
			         Text sixPM = new Text("6:00pm");
			         Text sevenPM = new Text("7:00pm");
			         Text eightPM = new Text("8:00pm");
			         Text ninePM = new Text("9:00pm");
			         Text tenPM = new Text("10:00pm");
			         
			         // adds the hours to the gridPane
			         newRoot.add(eightAM, 0, 0);
			         newRoot.add(nineAM, 0, 1);
			         newRoot.add(tenAM, 0, 2);
			         newRoot.add(elevenAM, 0, 3);
			         newRoot.add(noon, 0, 4);
			         newRoot.add(onePM, 0, 5);
			         newRoot.add(twoPM, 0, 6);
			         newRoot.add(threePM, 0, 7);
			         newRoot.add(fourPM, 0, 8);
			         newRoot.add(fivePM, 0, 9);
			         newRoot.add(sixPM, 0, 10);
			         newRoot.add(sevenPM, 0, 11);
			         newRoot.add(eightPM, 0, 12);
			         newRoot.add(ninePM, 0, 13);
			         newRoot.add(tenPM, 0, 14);
			         


			         newRoot.setGridLinesVisible(true);
			         newRoot.setPadding(new Insets(10));
			         newRoot.setVgap(40);
			         newRoot.setHgap(15);
			         newRoot.setAlignment(Pos.BASELINE_CENTER);
			         Stage stage = new Stage();
			         stage.setTitle("New Visualized Schedule!!");
			         stage.setScene(new Scene(newRoot,1150,850));
			         stage.show();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Course {
	
		private String courseCode;
		private String professor;
		private String weekdays1;
		private String time1;
		private String weekdays2;
		private String time2;

		public Course(String[] course) {

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
