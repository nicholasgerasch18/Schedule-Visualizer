package application;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
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
			BorderPane.setAlignment(scheduleVisualizer, Pos.TOP_CENTER);
			root.setTop(scheduleVisualizer);

			// creates text area
			TextArea textArea = new TextArea();
			BorderPane.setMargin(textArea, new Insets(20));
			root.setCenter(textArea);

			// creates submit button
			Button submit = new Button("Submit");
			extracted(root, submit);
			BorderPane.setMargin(submit, new Insets(10));
			root.setBottom(submit);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			// initiates action of parsing code once the submit button is clicked
			submit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Group root = new Group();
					Scene scene = new Scene(root, 800, 1000);
					scene.setFill(Color.AZURE);
					Stage stage = new Stage();
					stage.getIcons().add(new Image("AugieA.jpeg"));

					// gets text submitted
					String input = textArea.getText();
					TextHandler handledText = new TextHandler(input);
					ArrayList<Course> courseArray = new ArrayList<>();
					courseArray = handledText.getCourses();

					// checking if times overlap
					for (int x = 0; x < courseArray.size() - 1; x++) {

						for (int y = 0; y < courseArray.size(); y++) {

							// checks if two courses primary times overlap
							courseArray.get(x).checkOverlap(courseArray.get(y), "primary", "primary");
							// checks if the primary Courses's secondary time overlaps with the secondary
							// courses primary time
							if (!courseArray.get(x).getSecondaryDay().equals(" ")) {
								courseArray.get(x).checkOverlap(courseArray.get(y), "secondary", "primary");
							}
							// checks if the primary Courses's primary time overlaps with the secondary
							// courses secondary time
							if (!courseArray.get(y).getSecondaryDay().equals(" ")) {
								courseArray.get(x).checkOverlap(courseArray.get(y), "primary", "secondary");
							}
							// checks if the primary Courses's secondary time overlaps with the secondary
							// courses secondary time
							if (!courseArray.get(x).getSecondaryDay().equals(" ")
									&& !courseArray.get(y).getSecondaryDay().equals(" ")) {
								courseArray.get(x).checkOverlap(courseArray.get(y), "secondary", "secondary");
							}

						}
					}

					// creates calendar
					CalendarGenerator calendar = new CalendarGenerator(root);
					calendar.getCalendar();
					HashMap<String, Integer> dictionary = calendar.getCoordinateDictionary();

					// creates course boxes and places on calendar
					makeCourseBoxes(courseArray, dictionary, root, calendar);

					// checks if there were any conflicting course
					for (int i = 0; i < courseArray.size(); i++) {
						if (courseArray.get(i).isConflicting()) {
							Text warningText = new Text("You have conflicting class times!");
							warningText.setFont(Font.font("Verdana", 18));
							warningText.setFill(Color.RED);
							warningText.setX(280);
							warningText.setY(900);
							root.getChildren().add(warningText);
							i = courseArray.size();
						}
					}

					stage.setTitle("New Visualized Schedule!!");
					stage.getTitle();
					stage.setScene(scene);
					stage.show();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets alignment of the BorderPane (auto-generated)
	 * 
	 * @param root   - the Group object that stores all other objects to be drawn
	 *               onto window
	 * @param submit - the submit button
	 */
	private void extracted(BorderPane root, Button submit) {
		BorderPane.setAlignment(submit, Pos.BOTTOM_CENTER);
	}

	/**
	 * Creates the course boxes for every course submitted. Sets the box location,
	 * box size, box color, box height, and the course information text onto the
	 * box.
	 * 
	 * @param courseArray          - the list of courses submitted
	 * @param coordinateDictionary - the mapped coordinate locations for days of the
	 *                             week and hours of the day
	 * @param root                 - where all boxes are drawn onto
	 * @param calendar             - the generated calendar
	 */
	public static void makeCourseBoxes(ArrayList<Course> courseArray, HashMap<String, Integer> coordinateDictionary,
			Group root, CalendarGenerator calendar) {
		int count = 0;

		// Iterates through every submitted course
		for (Course course : courseArray) {

			String startTime = course.getPrimaryStartTime();
			String endTime = course.getPrimaryEndTime();
			String[] startTimeSplit = startTime.split(":");
			String[] endTimeSplit = endTime.split(":");
			startTimeSplit[0] = startTimeSplit[0] + startTimeSplit[1].substring(2, 4);
			startTimeSplit[1] = startTimeSplit[1].substring(0, 2);
			endTimeSplit[0] = endTimeSplit[0] + endTimeSplit[1].substring(2, 4);
			endTimeSplit[1] = endTimeSplit[1].substring(0, 2);
			String[] primaryDays = course.getPrimaryDay().split(" ");

			// Checks to see if course has a secondary meeting day
			if (course.getSecondaryDay() != " ") {
				String secondaryStartTime = course.getSecondaryStartTime();
				String secondaryEndTime = course.getSecondaryEndTime();
				String[] secondaryStartTimeSplit = secondaryStartTime.split(":");
				String[] secondaryEndTimeSplit = secondaryEndTime.split(":");
				secondaryStartTimeSplit[0] = secondaryStartTimeSplit[0] + secondaryStartTimeSplit[1].substring(2, 4);
				secondaryStartTimeSplit[1] = secondaryStartTimeSplit[1].substring(0, 2);
				secondaryEndTimeSplit[0] = secondaryEndTimeSplit[0] + secondaryEndTimeSplit[1].substring(2, 4);
				secondaryEndTimeSplit[1] = secondaryEndTimeSplit[1].substring(0, 2);
				String[] SecondaryDays = course.getSecondaryDay().split(" ");

				// Iterates through every second meeting day in in the array SecondaryDays
				for (String day : SecondaryDays) {
					int xCoord = coordinateDictionary.get(day) - 24;
					int yCoord = coordinateDictionary.get(secondaryStartTimeSplit[0])
							+ Integer.parseInt(secondaryStartTimeSplit[1]) - 5;
					Rectangle rectangle = new Rectangle();
					rectangle.setX(xCoord);
					rectangle.setY(yCoord);
					rectangle.setWidth(98);
					double height = (coordinateDictionary.get(secondaryEndTimeSplit[0])
							+ Integer.parseInt(secondaryEndTimeSplit[1])) - rectangle.getY() - 5;
					rectangle.setHeight(height);
					rectangle.setArcWidth(30.0);
					rectangle.setArcHeight(20.0);
					rectangle.setFill(course.getColor());

					Label text = new Label(course.toString("secondary"));
					text.setMaxWidth(85);
					text.setWrapText(true);
					text.setTranslateX(rectangle.getX() + 10);
					text.setTranslateY(rectangle.getY() + 10);
					text.setFont(Font.font("Serif", 10));
					root.getChildren().add(rectangle);
					root.getChildren().add(text);
					calendar.createBoxDictionary(rectangle, xCoord, yCoord, height);

				}
			}

			// Iterates through every primary meeting day
			for (String day : primaryDays) {
				Rectangle rectangle = new Rectangle();
				int xCoord1;
				int yCoord1;
				double height1;

				// checks to see if the course has no meeting day
				if (day.equals("None")) {

					xCoord1 = 75 + (count);
					count += 100;
					yCoord1 = 910;
					height1 = 60;
					rectangle.setX(xCoord1);
					rectangle.setY(yCoord1);
				} else {
					xCoord1 = coordinateDictionary.get(day) - 24;
					yCoord1 = coordinateDictionary.get(startTimeSplit[0]) + Integer.parseInt(startTimeSplit[1]) - 5;
					rectangle.setX(xCoord1);
					rectangle.setY(yCoord1);
					height1 = (coordinateDictionary.get(endTimeSplit[0]) + Integer.parseInt(endTimeSplit[1]))
							- rectangle.getY() - 5;
				}

				rectangle.setWidth(98);
				rectangle.setHeight(height1);
				rectangle.setArcWidth(30.0);
				rectangle.setArcHeight(20.0);
				rectangle.setFill(course.getColor());

				Label text = new Label(course.toString("primary"));
				text.setMaxWidth(85);
				text.setWrapText(true);
				text.setTranslateX(rectangle.getX() + 10);
				text.setTranslateY(rectangle.getY() + 10);
				text.setFont(Font.font("Serif", 10));

				root.getChildren().add(rectangle);
				root.getChildren().add(text);

				calendar.createBoxDictionary(rectangle, xCoord1, yCoord1, height1);

			}

		}

	}

}
