package application;

import java.util.ArrayList;
import java.util.Arrays;

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
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {

	// current max number of categories for one class input
	final int COLUMN_NUM = 12;
	// all known course codes
	final ArrayList<String> COURSE_CODES = new ArrayList<String>(Arrays.asList("ACCT", "AFSP", "APMA", "ARHI", "ART",
			"ASIA", "ASTR", "AUGIE", "BIOL", "BUSN", "CHEM", "CHNS", "CHST", "CLAS", "COMM", "CORE", "CSC", "CSD",
			"DATA", "ECON", "EDMU", "EDUC", "ENCW", "ENGL", "ENGR", "ENTM", "ENVR", "FOOD", "FREN", "FRST", "FYH",
			"FYI", "GEOG", "GEOL", "GIST", "GRD", "GREK", "GRMN", "GRST", "HEPE", "HIST", "HONR", "ISS", "JPN", "JPST",
			"KINS", "LATN", "LING", "LSC", "LTAM", "MATH", "MJMC", "MSCI", "MUCH", "MUEN", "MUSC", "MULS", "NTGR",
			"PHIL", "PHYS", "POLS", "PSYC", "PUBH", "RELG", "SCAN", "SLP", "SOAN", "SPAN", "SPRI", "SPST", "SWED",
			"THEA", "WGSS", "WLIT", "WLCC"));
	// all known building codes
	final ArrayList<String> BUILDING_NAMES = new ArrayList<String>(
			Arrays.asList("ABST", "AND", "ANNX", "ARPO", "ARTS", "BERG", "BROD", "BRUN", "CARH", "CARV", "DENK", "EVLD",
					"JDPL", "LIBR", "LIND", "OLDM", "OLIN", "SCIE", "SORN", "SWEN"));
	// all known times for classes
	final ArrayList<String> DAYS = new ArrayList<String>(Arrays.asList("M ", "Tu ", "W ", "Th ", "F ", "M W F ",
			"Tu Th ", "M W ", "M F ", "W F ", "Tu W Th F ", "M Tu W Th F "));

	@Override
	public void start(Stage primaryStage) {
		try {

			// gives the window a header and creates the BorderPane
			// which we will be working with
			primaryStage.setTitle("Schedule Visualizer!!");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			root.setPadding(new Insets(10));

			// creating window elements
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

					// initiates lists for the included variables to be displayed on the calendar
					ArrayList<String> courseCodes = new ArrayList<String>();
					ArrayList<String> courseName = new ArrayList<String>();
					ArrayList<String> professorName = new ArrayList<String>();
					ArrayList<String> classroom = new ArrayList<String>();
					ArrayList<String> primaryDay = new ArrayList<String>();
					ArrayList<String> secondaryDay = new ArrayList<String>();
					ArrayList<String> primaryTime = new ArrayList<String>();
					ArrayList<String> secondaryTime = new ArrayList<String>();

					// gets text submitted
					String input = textArea.getText();
					// splits submitted text into array and splits it at each new line
					String[] splitInput = input.split("\n");

					// parses through each line of text
					for (int i = 0; i < splitInput.length; i++) {
						String current = splitInput[i];
						String[] currentsplit = current.split("-");
						// checks if line contains a course code. If so, it knows it's a new class
						// and then adds course code and name to their respective lists
						if (COURSE_CODES.contains(currentsplit[0])) {
							courseCodes.add(splitInput[i]);
							courseName.add(splitInput[i + 1]);

							// creates a placeholder for the other lists
							professorName.add(" ");
							primaryDay.add(" ");
							primaryTime.add(" ");
							secondaryDay.add(" ");
							secondaryTime.add(" ");
							classroom.add(" ");

							// checks if line is professor name
						} else if (current.contains(",")) {
							professorName.add(courseCodes.size() - 1, current);

							// checks if line is building code
						} else if (BUILDING_NAMES.contains(current)) {
							classroom.add(courseCodes.size() - 1, splitInput[i] + " " + splitInput[i + 1]);

							// checks if line is the days of class
						} else if (DAYS.contains(current)) {

							// checks if the primary days for this class is empty still,
							// if so, we add the days to primary days list
							if (primaryDay.get(courseCodes.size() - 1).equals(" ")) {
								primaryDay.add(courseCodes.size() - 1, splitInput[i]);

								// if it is not empty, then these days are secondary days and
								// are added to the respective list
							} else {
								secondaryDay.add(courseCodes.size() - 1, splitInput[i]);
							}

							// checks if line is a time frame
						} else if (current.contains(":")) {

							// checks if the primary time for this class is empty still,
							// if so, we add the time to primary times list
							if (primaryTime.get(courseCodes.size() - 1).equals(" ")) {
								primaryTime.add(courseCodes.size() - 1, splitInput[i]);

								// if it is not empty, then this time frame is a secondary time and
								// is added to the respective list
							} else {
								secondaryTime.add(courseCodes.size() - 1, splitInput[i]);
							}
						}

					}

					// initiates courseArray which contains all of the course
					// objects that were inputed
					ArrayList<Course> courseArray = new ArrayList<Course>();

					// loops through and adds each course to the courseArray by accessing each
					// array. Each index is a separate class
					for (int n = 0; n < courseCodes.size(); n++) {
						courseArray.add(new Course(courseCodes.get(n), courseName.get(n), professorName.get(n),
								classroom.get(n), primaryDay.get(n), secondaryDay.get(n), primaryTime.get(n),
								secondaryTime.get(n)));

					}

					// test to see if courses are displaying correct information
					for (int j = 0; j < courseArray.size(); j++) {
						System.out.println(courseArray.get(j).displayClass());
					}

					// initiates a calendar
					CalendarInterface calendar = new CalendarGenerator(root);
					calendar.addDays(root);
					calendar.addHorizontalLines(root);
					calendar.addVerticalLines(root);
					calendar.addHorizontalLines(root);

					stage.setTitle("New Visualized Schedule!!");
					stage.setScene(scene);
					stage.show();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
