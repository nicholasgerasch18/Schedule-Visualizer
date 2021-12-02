package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {

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
					textHandler handledText = new textHandler(input);
					ArrayList<Course> courseArray = new ArrayList<>();
					courseArray = handledText.getCourses();

					for (int i = 0; i < courseArray.size(); i++) {
						System.out.println(courseArray.get(i).displayClass());
					}

					// creates calendar
					CalendarGenerator calendar = new CalendarGenerator(root);
					calendar.getCalendar();
					HashMap<String, Integer> dictionary = calendar.getCoordinateDictionary();

					makeCourseBoxes(courseArray, dictionary, root);

					stage.setTitle("New Visualized Schedule!!");
					stage.setScene(scene);
					stage.show();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void makeCourseBoxes(ArrayList<Course> courseArray, HashMap<String, Integer> coordinateDictionary,
			Group root) {

		for (Course course : courseArray) {
			String courseName = course.getCourseName();
			String courseCode = course.getCourseCode();
			String startTime = course.getPrimaryStartTime();
			String endTime = course.getPrimaryEndTime();

			String[] startTimeSplit = startTime.split(":");
			String[] endTimeSplit = endTime.split(":");

			startTimeSplit[0] = startTimeSplit[0] + startTimeSplit[1].substring(2, 4);
			startTimeSplit[1] = startTimeSplit[1].substring(0, 2);
			endTimeSplit[0] = endTimeSplit[0] + endTimeSplit[1].substring(2, 4);
			endTimeSplit[1] = endTimeSplit[1].substring(0, 2);

			String[] primaryDays = course.getPrimaryDay().split(" ");
//			if(course.getSecondaryDay() != null) {
//				String[] secondaryDays = course.getPrimaryDay().split(" ");
//
//			}

//			System.out.println(primaryDays);
//			System.out.println(Integer.parseInt(getChars(0, 1, startTimeSplit[1], 0)));
			String info = courseCode + " " + courseName + "\n" + startTime + " - " +  endTime;
			for (String day : primaryDays) {
				Label label = new Label();
				label.setWrapText(true);
				label.setText(info);
				label.setTranslateX(coordinateDictionary.get(day) - 24);
				label.setTranslateY(
						coordinateDictionary.get(startTimeSplit[0]) + Integer.parseInt(startTimeSplit[1]) - 5);
				label.setMinWidth(98);
				label.setMaxWidth(98);
				label.setMinHeight((coordinateDictionary.get(endTimeSplit[0]) + Integer.parseInt(endTimeSplit[1]))
						- (coordinateDictionary.get(startTimeSplit[0]) + Integer.parseInt(startTimeSplit[1]) - 5) - 5);
				label.setMaxHeight((coordinateDictionary.get(endTimeSplit[0]) + Integer.parseInt(endTimeSplit[1]))
						- (coordinateDictionary.get(startTimeSplit[0]) + Integer.parseInt(startTimeSplit[1]) - 5) - 5);
				label.setBackground(new Background(
						new BackgroundFill(Color.rgb(100,100,100), new CornerRadii(5.0), new Insets(0))));
				root.getChildren().add(label);
//				Rectangle rectangle = new Rectangle();
//				rectangle.setX(coordinateDictionary.get(day) - 24);
//				rectangle.setY(coordinateDictionary.get(startTimeSplit[0]) + Integer.parseInt(startTimeSplit[1]) - 5);
//				rectangle.setWidth(98);
//				System.out.println(coordinateDictionary.get(startTimeSplit[0]));
//				rectangle.setHeight((coordinateDictionary.get(endTimeSplit[0]) + Integer.parseInt(endTimeSplit[1])) - rectangle.getY() - 5);
//				rectangle.setFill(Color.rgb(100, 100, 100));
//				root.getChildren().add(rectangle);
			}
		}

	}

	private static String getChars(int i, int j, String string, int k) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void checkOverLap(){
//}
}
