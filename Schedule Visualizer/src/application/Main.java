package application;

import java.util.ArrayList;
import java.util.Arrays;

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
	final ArrayList<String> COURSE_CODES = new ArrayList<String>(Arrays.asList("ACCT", "AFSP", "ARHI", "ART", "ASTR", "AUGIE",
			"BIOL", "BUSN", "CHEM", "CHNS", "CHST", "CLAS", "COMM", "CORE", "CSC", "CSD", "DATA", "ECON", "EDMU", "EDUC",
			"ENCW", "ENGL", "ENTM", "ENVR", "FREN", "FRST", "FYH", "FYI", "GEOG", "GEOL", "GIST", "GRD", "GREK", "GRMN",
			"GRST", "HEPE", "HIST", "HONR", "ISS", "JPN", "JPST", "KINS", "LATN", "LING", "LSC", "LTAM", "MATH", "MJMC",
			"MUCH", "MUEN", "MUSC", "PHIL", "PHYS", "POLS", "PSYC", "PUBH", "RELG", "SCAN", "SLP", "SOAN", "SPAN",
			"SPST", "SWED", "THEA", "WGSS"));
	final ArrayList<String> BUILDING_NAMES = new ArrayList<String>(
			Arrays.asList("BERG", "CARV", "EVLD", "LIND", "OLIN", "SCIE", "SORN"));
	final ArrayList<String> DAYS = new ArrayList<String>(Arrays.asList("M ", "Tu ", "W ", "Th ", "F ", "M W F ",
			"Tu Th ", "M W ", "M F ", "W F ", "Tu W Th F ", "M Tu W Th F "));

	@Override
	public void start(Stage primaryStage) {
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
					GridPane newRoot = new GridPane();

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
					int count2=1;
					for (int i = 0; i < splitInput.length; i++) {
						String current = splitInput[i];
						String[] currentsplit = current.split("-");
						if (COURSE_CODES.contains(currentsplit[0])) {
							courseCodes.add(splitInput[i]);
							courseName.add(splitInput[i + 1]);
							i++;
							if(currentsplit[0].equals("CORE") || currentsplit[0].equals("AUGIE")) {
								primaryDay.add(" ");
								primaryTime.add(" ");
								secondaryDay.add(" ");
								secondaryTime.add(" ");
								classroom.add(" ");
							}
						} else if (current.contains(",")) {
							teacherName.add(current);
						} else if (BUILDING_NAMES.contains(current)) {
							classroom.add(splitInput[i] + " " + splitInput[i + 1]);
						} else if (DAYS.contains(current)) {
							if (courseCodes.size() == primaryDay.size()) {
								// need to work on parsing second day
								secondaryDay.add(secondaryDay.size() - count, splitInput[i]);
								count++;
							} else {
								primaryDay.add(splitInput[i]);
								secondaryDay.add(" ");
							}
						} else if (current.contains(":")) {
							if (courseCodes.size() == primaryTime.size()) {
								secondaryTime.add(secondaryTime.size() - count2, splitInput[i]);
								count2++;
							} else {
								primaryTime.add(splitInput[i]);
								secondaryTime.add(" ");
							}
						}

					}

					ArrayList<Course> courseArray = new ArrayList<Course>();
					for (int n = 0; n < courseCodes.size(); n++) {
						courseArray.add(new Course(courseCodes.get(n), courseName.get(n), teacherName.get(n),
								classroom.get(n), primaryDay.get(n), secondaryDay.get(n), primaryTime.get(n),
								secondaryTime.get(n)));

					}

					for (int j = 0; j < courseArray.size(); j++) {
						System.out.println(courseArray.get(j).displayClass());
						System.out.println();
					}

					// adds days of the week to the calendar
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

					// adds times to the calendar
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
					stage.setScene(new Scene(newRoot, 1150, 850));
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

		private String SecondaryTime() {
			return secondaryTime;
		}

		private String displayClass() {
			if (secondaryDay == " ") {
				String output = classroom + "\n" + teacherName + "\n" + courseCode + "\n" + courseName + "\n"
						+ primaryDay + "\n" + primaryTime;
				return output;
			} else {
				String output = classroom + "\n" + teacherName + "\n" + courseCode + "\n" + courseName + "\n"
						+ primaryDay + "\n" + primaryTime + "\n" + secondaryDay + "\n" + secondaryTime;
				return output;
			}

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
