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

	final int COLUMN_NUM = 12;
	final ArrayList<String> COURSE_CODES = new ArrayList<String>(Arrays.asList("ACCT", "AFSP", "APMA", "ARHI", "ART",
			"ASIA", "ASTR", "AUGIE", "BIOL", "BUSN", "CHEM", "CHNS", "CHST", "CLAS", "COMM", "CORE", "CSC", "CSD",
			"DATA", "ECON", "EDMU", "EDUC", "ENCW", "ENGL", "ENGR", "ENTM", "ENVR", "FOOD", "FREN", "FRST", "FYH",
			"FYI", "GEOG", "GEOL", "GIST", "GRD", "GREK", "GRMN", "GRST", "HEPE", "HIST", "HONR", "ISS", "JPN", "JPST",
			"KINS", "LATN", "LING", "LSC", "LTAM", "MATH", "MJMC", "MSCI", "MUCH", "MUEN", "MUSC", "MULS", "NTGR",
			"PHIL", "PHYS", "POLS", "PSYC", "PUBH", "RELG", "SCAN", "SLP", "SOAN", "SPAN", "SPRI", "SPST", "SWED",
			"THEA", "WGSS", "WLIT", "WLCC"));
	final ArrayList<String> BUILDING_NAMES = new ArrayList<String>(
			Arrays.asList("ABST", "AND", "ANNX", "ARPO", "ARTS", "BERG", "BROD", "BRUN", "CARH", "CARV", "DENK", "EVLD",
					"JDPL", "LIBR", "LIND", "OLDM", "OLIN", "SCIE", "SORN", "SWEN"));
	final ArrayList<String> DAYS = new ArrayList<String>(Arrays.asList("M ", "Tu ", "W ", "Th ", "F ", "M W F ",
			"Tu Th ", "M W ", "M F ", "W F ", "Tu W Th F ", "M Tu W Th F "));

	@Override
	public void start(Stage primaryStage){
		try {

			primaryStage.setTitle("Schedule Visualizer!!");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			root.setPadding(new Insets(10));

			// creating window elements
			Text scheduleVisualizer = new Text("Augustana Schedule Visualizer!");
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
					Group root = new Group();
					Scene scene = new Scene(root, 800, 900);
					Stage stage = new Stage();

					ArrayList<String> courseCodes = new ArrayList<String>();
					ArrayList<String> courseName = new ArrayList<String>();
					ArrayList<String> teacherName = new ArrayList<String>();
					ArrayList<String> classroom = new ArrayList<String>();
					ArrayList<String> primaryDay = new ArrayList<String>();
					ArrayList<String> secondaryDay = new ArrayList<String>();
					ArrayList<String> primaryTime = new ArrayList<String>();
					ArrayList<String> secondaryTime = new ArrayList<String>();

					String input = textArea.getText();
					String[] splitInput = input.split("\n");
					int length = splitInput.length;
					int size = ((splitInput.length) / COLUMN_NUM);
					int count = 1;
					int count2 = 1;
					for (int i = 0; i < splitInput.length; i++) {
						String current = splitInput[i];
						String[] currentsplit = current.split("-");
						if (COURSE_CODES.contains(currentsplit[0])) {
							courseCodes.add(splitInput[i]);
							courseName.add(splitInput[i + 1]);

							teacherName.add(" ");
							primaryDay.add(" ");
							primaryTime.add(" ");
							secondaryDay.add(" ");
							secondaryTime.add(" ");
							classroom.add(" ");

						} else if (current.contains(",")) {
							teacherName.add(courseCodes.size() - 1, current);
						} else if (BUILDING_NAMES.contains(current)) {
							classroom.add(courseCodes.size() - 1, splitInput[i] + " " + splitInput[i + 1]);
						} else if (DAYS.contains(current)) {
							if (primaryDay.get(courseCodes.size() - 1).equals(" ")) {
								// need to work on parsing second day
								// primaryDay.add(splitInput[i]);
								primaryDay.add(courseCodes.size() - 1, splitInput[i]);

							} else {
								secondaryDay.add(courseCodes.size() - 1, splitInput[i]);

							}
						} else if (current.contains(":")) {
							if (primaryTime.get(courseCodes.size() - 1).equals(" ")) {
								primaryTime.add(courseCodes.size() - 1, splitInput[i]);

							} else {
								secondaryTime.add(courseCodes.size() - 1, splitInput[i]);
							}
						}

					}

					// courseArray contains all of the course objects that were inputed
					ArrayList<Course> courseArray = new ArrayList<Course>();

					// loops through and adds each course to the
					for (int n = 0; n < courseCodes.size(); n++) {
						courseArray.add(new Course(courseCodes.get(n), courseName.get(n), teacherName.get(n),
								classroom.get(n), primaryDay.get(n), secondaryDay.get(n), primaryTime.get(n),
								secondaryTime.get(n)));

					}

					// test to see if courses are displaying correct information
					for (int j = 0; j < courseArray.size(); j++) {
						System.out.println(courseArray.get(j).displayClass());
					}
					
					
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
	
	
	private class Course {

		private String courseCode;
		private String courseName;
		private String teacherName;
		private String classroom;
		private String primaryDay;
		private String primaryTime;
		private String secondaryDay;
		private String secondaryTime;
		private ArrayList<String> courseInfo;

		public Course(String courseCode, String courseName, String teacherName, String classroom, String primaryDay,
				String secondaryDay, String primaryTime, String secondaryTime) {
			this.courseCode = courseCode;
			this.courseName = courseName;
			this.teacherName = teacherName;
			this.classroom = classroom;
			this.primaryDay = primaryDay;
			this.primaryTime = primaryTime;
			this.secondaryDay = secondaryDay;
			this.secondaryTime = secondaryTime;
			courseInfo = new ArrayList<String>(Arrays.asList(classroom, teacherName, courseCode, courseName, primaryDay,
					primaryTime, secondaryDay, secondaryTime));
		}

		private String getCourseCode() {
			return courseCode;
		}

		private String getteacherName() {
			return teacherName;
		}

		private String getClassroom() {
			return classroom;
		}

		private String getPrimaryDay() {
			return primaryDay;
		}

		private String getPrimaryTime() {
			return primaryTime;
		}

		private String getSecondaryDay() {
			return secondaryDay;
		}

		private String getSecondaryTime() {
			return secondaryTime;
		}

		private String displayClass() {

			String output = "";
			// creates a course string based off the course elements that were passed in the
			// constructor for each course
			for (int k = 0; k < courseInfo.size(); k++) {
				if (!(courseInfo.get(k).equals("") || courseInfo.get(k).equals(" "))) {
					output += courseInfo.get(k) + "\n";

				}
			}
			return output;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
