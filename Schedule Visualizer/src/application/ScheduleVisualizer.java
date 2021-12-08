package application;
//hi
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScheduleVisualizer extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			// gives the window a header and creates the BorderPane
			// which we will be working with
			primaryStage.setTitle("Schedule Visualizer!!");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			root.setPadding(new Insets(10));
			Text scheduleVisualizer = new Text("Augustana Schedule Visualizer!");
			root.setAlignment(scheduleVisualizer, Pos.TOP_CENTER);
			root.setTop(scheduleVisualizer);

			// creates text area
			TextArea textArea = new TextArea();
			root.setMargin(textArea, new Insets(20));
			root.setCenter(textArea);

			// creates submit button
			Button submit = new Button("Submit");
			root.setAlignment(submit, Pos.BOTTOM_CENTER);
			root.setMargin(submit, new Insets(10));
			root.setBottom(submit);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			// initiates action of parsing code once the submit button is clicked
			submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Group root = new Group();
					Scene scene = new Scene(root, 800, 900);
					Stage stage = new Stage();

					// gets text submitted
					String input = textArea.getText();
					TextHandler handledText = new TextHandler(input);
					ArrayList<Course> courseArray = new ArrayList<>();
					courseArray = handledText.getCourses();
					
					
					
					
					//checking if times overlap
					for(int x=0; x<courseArray.size()-1;x++) {
						
						for(int y=0; y<courseArray.size();y++) {
					
							int start1 = courseArray.get(x).primaryMilitaryStart(courseArray.get(x).getPrimaryStartTime());
							int end1 =courseArray.get(x).primaryMilitaryStart(courseArray.get(x).getPrimaryEndTime());
							int start2 = courseArray.get(y).primaryMilitaryStart(courseArray.get(y).getPrimaryStartTime());
							int end2 =courseArray.get(y).primaryMilitaryStart(courseArray.get(y).getPrimaryEndTime());
							Boolean sameDay = false;
							
							for(int i = 0; i<courseArray.get(x).getPrimaryDayList().size(); i++) {
						
								if(courseArray.get(y).getPrimaryDayList().contains(courseArray.get(x).getPrimaryDayList().get(i)) && !courseArray.get(x).equals(courseArray.get(y))) {
									sameDay = true;
								}
							}
					
							if( (start2>start1 && start2 < end1 && !courseArray.get(x).equals(courseArray.get(y)) && sameDay) || start2<start1 && start1 < end2 && !courseArray.get(x).equals(courseArray.get(y)) && sameDay) {
								courseArray.get(x).setConflicting(true);
								courseArray.get(y).setConflicting(true);
								courseArray.get(x).setColor(Color.RED);
								courseArray.get(y).setColor(Color.RED);
								System.out.print("hello");
							}
						}
					}
					
					
					
		
					
					//creates calendar
					CalendarGenerator calendar = new CalendarGenerator(root);					
					calendar.getCalendar();
					HashMap<String, Integer> dictionary = calendar.getCoordinateDictionary();
					
					makeCourseBoxes(courseArray , dictionary, root, calendar);
					
					
					stage.setTitle("New Visualized Schedule!!");
					stage.setScene(scene);
					stage.show();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void makeCourseBoxes(ArrayList<Course> courseArray, HashMap<String, Integer> coordinateDictionary, Group root, CalendarGenerator calendar) {

		for(Course course : courseArray) {
			
			String startTime = course.getPrimaryStartTime();
			String endTime = course.getPrimaryEndTime();
			String[] startTimeSplit = startTime.split(":");
			String[] endTimeSplit = endTime.split(":");
			startTimeSplit[0] = startTimeSplit[0] + startTimeSplit[1].substring(2,4);
			startTimeSplit[1] = startTimeSplit[1].substring(0,2);
			endTimeSplit[0] = endTimeSplit[0] + endTimeSplit[1].substring(2,4);
			endTimeSplit[1] = endTimeSplit[1].substring(0,2);
			String[] primaryDays = course.getPrimaryDay().split(" ");
			
			
			
			if(course.getSecondaryDay() != " ") {
				//System.out.println(course.getSecondaryTime());
				String secondaryStartTime = course.getSecondaryStartTime();
				String secondaryEndTime = course.getSecondaryEndTime();
				String[] secondaryStartTimeSplit = secondaryStartTime.split(":");
				String[] secondaryEndTimeSplit = secondaryEndTime.split(":");
				secondaryStartTimeSplit[0] = secondaryStartTimeSplit[0] + secondaryStartTimeSplit[1].substring(2,4);
				secondaryStartTimeSplit[1] = secondaryStartTimeSplit[1].substring(0,2);
				secondaryEndTimeSplit[0] = secondaryEndTimeSplit[0] + secondaryEndTimeSplit[1].substring(2,4);
				secondaryEndTimeSplit[1] = secondaryEndTimeSplit[1].substring(0,2);
				String[] SecondaryDays = course.getSecondaryDay().split(" ");
				
				for(String day : SecondaryDays) {
					int xCoord = coordinateDictionary.get(day) - 24;
					int yCoord = coordinateDictionary.get(secondaryStartTimeSplit[0]) + Integer.parseInt(secondaryStartTimeSplit[1]) - 5;
					Rectangle rectangle = new Rectangle();
					rectangle.setX(xCoord);
					rectangle.setY(yCoord);
					rectangle.setWidth(98);
					double height = (coordinateDictionary.get(secondaryEndTimeSplit[0]) + Integer.parseInt(secondaryEndTimeSplit[1])) - rectangle.getY() - 5;
					rectangle.setHeight(height);
					rectangle.setArcWidth(30.0); 
					rectangle.setArcHeight(20.0);  
					rectangle.setFill(course.getColor());

					Label text = new Label(course.toString("secondary"));
					text.setMaxWidth(85);
					text.setWrapText(true);
					text.setTranslateX(rectangle.getX() + 10);
					text.setTranslateY(rectangle.getY() + 10);
					text.setFont(Font.font("Verdana", 10));
					root.getChildren().add(rectangle);
					root.getChildren().add(text);
					calendar.createBoxDictionary(rectangle, xCoord, yCoord, height);

				}
			}
		
			
			for(String day : primaryDays) {
				Rectangle rectangle = new Rectangle();

				int xCoord1 = coordinateDictionary.get(day) - 24;
				int yCoord1 = coordinateDictionary.get(startTimeSplit[0]) + Integer.parseInt(startTimeSplit[1]) - 5;
				rectangle.setX(xCoord1);
				rectangle.setY(yCoord1);
				rectangle.setWidth(98);
					
				double height1 = (coordinateDictionary.get(endTimeSplit[0]) + Integer.parseInt(endTimeSplit[1])) - rectangle.getY() - 5;
				rectangle.setHeight(height1);
				rectangle.setArcWidth(30.0); 
				rectangle.setArcHeight(20.0);  
				rectangle.setFill(course.getColor());
	
				Label text = new Label(course.toString("primary"));
				text.setMaxWidth(85);
				text.setWrapText(true);
				text.setTranslateX(rectangle.getX() + 10);
				text.setTranslateY(rectangle.getY() + 10);
				text.setFont(Font.font("Verdana", 10));
				root.getChildren().add(rectangle);
				root.getChildren().add(text);
					
				calendar.createBoxDictionary(rectangle, xCoord1, yCoord1, height1);
				
			}
			
//			if(calendar.checkOverLap()) {
//				Label overlapWarning = new Label("You have conflicting class times in your schedule!");
//				overlapWarning.setTranslateX(150);
//				overlapWarning.setTranslateY(950);
//				overlapWarning.setFont(Font.font("Verdana", 10));
//
//				root.getChildren().add(overlapWarning);
//				//System.out.println(overlapWarning + "hi");
//
//			}
			
			
		}
		
		
	}

	
}
