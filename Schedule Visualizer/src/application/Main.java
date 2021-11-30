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
					
					// adds days of the week to the calendar
					Text monday = new Text("Monday");
					monday.setX(100);
					monday.setY(10);
					monday.setFont(Font.font("Verdana", 12));
					Text tuesday = new Text("Tuesday");
					tuesday.setX(200);
					tuesday.setY(10);
					tuesday.setFont(Font.font("Verdana", 12));
					Text wednesday = new Text("Wednesday");
					wednesday.setX(300);
					wednesday.setY(10);
					wednesday.setFont(Font.font("Verdana", 12));
					Text thursday = new Text("Thursday");
					thursday.setX(400);
					thursday.setY(10);
					thursday.setFont(Font.font("Verdana", 12));
					Text friday = new Text("Friday");
					friday.setX(500);
					friday.setY(10);
					friday.setFont(Font.font("Verdana", 12));
					Text saturday = new Text("Saturday");
					saturday.setX(600);
					saturday.setY(10);
					saturday.setFont(Font.font("Verdana", 12));
					Text sunday = new Text("Sunday");
					sunday.setX(700);
					sunday.setY(10);
					sunday.setFont(Font.font("Verdana", 12));
					//////////////////////
					root.getChildren().add(monday);
					root.getChildren().add(tuesday);
					root.getChildren().add(wednesday);
					root.getChildren().add(thursday);
					root.getChildren().add(friday);
					root.getChildren().add(saturday);
					root.getChildren().add(sunday);

					// adds times to the calendar
					Text eightAM = new Text("8:00am");
					eightAM.setX(0);
					eightAM.setY(40);
					eightAM.setFont(Font.font("Verdana", 12));
					Text nineAM = new Text("9:00am");
					nineAM.setX(0);
					nineAM.setY(100);
					nineAM.setFont(Font.font("Verdana", 12));
					Text tenAM = new Text("10:00am");
					tenAM.setX(0);
					tenAM.setY(160);
					tenAM.setFont(Font.font("Verdana", 12));
					Text elevenAM = new Text("11:00am");
					elevenAM.setX(0);
					elevenAM.setY(220);
					elevenAM.setFont(Font.font("Verdana", 12));
					Text noon = new Text("12:00pm");
					noon.setX(0);
					noon.setY(280);
					noon.setFont(Font.font("Verdana", 12));
					Text onePM = new Text("1:00pm");
					onePM.setX(0);
					onePM.setY(340);
					onePM.setFont(Font.font("Verdana", 12));
					Text twoPM = new Text("2:00pm");
					twoPM.setX(0);
					twoPM.setY(400);
					twoPM.setFont(Font.font("Verdana", 12));
					Text threePM = new Text("3:00pm");
					threePM.setX(0);
					threePM.setY(460);
					threePM.setFont(Font.font("Verdana", 12));
					Text fourPM = new Text("4:00pm");
					fourPM.setX(0);
					fourPM.setY(520);
					fourPM.setFont(Font.font("Verdana", 12));
					Text fivePM = new Text("5:00pm");
					fivePM.setX(0);
					fivePM.setY(580);
					fivePM.setFont(Font.font("Verdana", 12));
					Text sixPM = new Text("6:00pm");
					sixPM.setX(0);
					sixPM.setY(640);
					sixPM.setFont(Font.font("Verdana", 12));
					Text sevenPM = new Text("7:00pm");
					sevenPM.setX(0);
					sevenPM.setY(700);
					sevenPM.setFont(Font.font("Verdana", 12));
					Text eightPM = new Text("8:00pm");
					eightPM.setX(0);
					eightPM.setY(760);
					eightPM.setFont(Font.font("Verdana", 12));
					Text ninePM = new Text("9:00pm");
					ninePM.setX(0);
					ninePM.setY(820);
					ninePM.setFont(Font.font("Verdana", 12));
					Text tenPM = new Text("10:00pm");
					tenPM.setX(0);
					tenPM.setY(880);
					tenPM.setFont(Font.font("Verdana", 12));
					///////////////////////////
					root.getChildren().add(eightAM);
					root.getChildren().add(nineAM);
					root.getChildren().add(tenAM);
					root.getChildren().add(elevenAM);
					root.getChildren().add(noon);
					root.getChildren().add(onePM);
					root.getChildren().add(twoPM);
					root.getChildren().add(threePM);
					root.getChildren().add(fourPM);
					root.getChildren().add(fivePM);
					root.getChildren().add(sixPM);
					root.getChildren().add(sevenPM);
					root.getChildren().add(eightPM);
					root.getChildren().add(ninePM);
					root.getChildren().add(tenPM);
					
					// add vertical lines
					Line line = new Line();
					line.setStartX(75);
					line.setEndX(75);
					line.setStartY(0);
					line.setEndY(1000);
					Line line1 = new Line();
					line1.setStartX(175);
					line1.setEndX(175);
					line1.setStartY(0);
					line1.setEndY(1000);
					Line line2 = new Line();
					line2.setStartX(275);
					line2.setEndX(275);
					line2.setStartY(0);
					line2.setEndY(1000);
					Line line3 = new Line();
					line3.setStartX(375);
					line3.setEndX(375);
					line3.setStartY(0);
					line3.setEndY(1000);
					Line line4 = new Line();
					line4.setStartX(475);
					line4.setEndX(475);
					line4.setStartY(0);
					line4.setEndY(1000);
					Line line5 = new Line();
					line5.setStartX(575);
					line5.setEndX(575);
					line5.setStartY(0);
					line5.setEndY(1000);
					Line line6 = new Line();
					line6.setStartX(675);
					line6.setEndX(675);
					line6.setStartY(0);
					line6.setEndY(1000);
					Line line7 = new Line();
					line7.setStartX(775);
					line7.setEndX(775);
					line7.setStartY(0);
					line7.setEndY(1000);
					/////////////////////////////
					root.getChildren().add(line);
					root.getChildren().add(line1);
					root.getChildren().add(line2);
					root.getChildren().add(line3);
					root.getChildren().add(line4);
					root.getChildren().add(line5);
					root.getChildren().add(line6);
					root.getChildren().add(line7);	
					
					// add horizontal lines
					Line horizentalLine = new Line();
					horizentalLine.setStartX(75);
					horizentalLine.setEndX(775);
					horizentalLine.setStartY(35);
					horizentalLine.setEndY(35);
					Line horizentalLine1 = new Line();
					horizentalLine1.setStartX(75);
					horizentalLine1.setEndX(775);
					horizentalLine1.setStartY(95);
					horizentalLine1.setEndY(95);
					Line horizentalLine2 = new Line();
					horizentalLine2.setStartX(75);
					horizentalLine2.setEndX(775);
					horizentalLine2.setStartY(155);
					horizentalLine2.setEndY(155);
					Line horizentalLine3 = new Line();
					horizentalLine3.setStartX(75);
					horizentalLine3.setEndX(775);
					horizentalLine3.setStartY(215);
					horizentalLine3.setEndY(215);
					Line horizentalLine4 = new Line();
					horizentalLine4.setStartX(75);
					horizentalLine4.setEndX(775);
					horizentalLine4.setStartY(275);
					horizentalLine4.setEndY(275);
					Line horizentalLine5 = new Line();
					horizentalLine5.setStartX(75);
					horizentalLine5.setEndX(775);
					horizentalLine5.setStartY(335);
					horizentalLine5.setEndY(335);
					Line horizentalLine6 = new Line();
					horizentalLine6.setStartX(75);
					horizentalLine6.setEndX(775);
					horizentalLine6.setStartY(395);
					horizentalLine6.setEndY(395);
					Line horizentalLine7 = new Line();
					horizentalLine7.setStartX(75);
					horizentalLine7.setEndX(775);
					horizentalLine7.setStartY(455);
					horizentalLine7.setEndY(455);
					Line horizentalLine8 = new Line();
					horizentalLine8.setStartX(75);
					horizentalLine8.setEndX(775);
					horizentalLine8.setStartY(515);
					horizentalLine8.setEndY(515);
					Line horizentalLine9 = new Line();
					horizentalLine9.setStartX(75);
					horizentalLine9.setEndX(775);
					horizentalLine9.setStartY(575);
					horizentalLine9.setEndY(575);
					Line horizentalLine10 = new Line();
					horizentalLine10.setStartX(75);
					horizentalLine10.setEndX(775);
					horizentalLine10.setStartY(635);
					horizentalLine10.setEndY(635);
					Line horizentalLine11 = new Line();
					horizentalLine11.setStartX(75);
					horizentalLine11.setEndX(775);
					horizentalLine11.setStartY(695);
					horizentalLine11.setEndY(695);
					Line horizentalLine12 = new Line();
					horizentalLine12.setStartX(75);
					horizentalLine12.setEndX(775);
					horizentalLine12.setStartY(755);
					horizentalLine12.setEndY(755);
					Line horizentalLine13 = new Line();
					horizentalLine13.setStartX(75);
					horizentalLine13.setEndX(775);
					horizentalLine13.setStartY(815);
					horizentalLine13.setEndY(815);
					Line horizentalLine14 = new Line();
					horizentalLine14.setStartX(75);
					horizentalLine14.setEndX(775);
					horizentalLine14.setStartY(875);
					horizentalLine14.setEndY(875);
					///////////////////////////////
					root.getChildren().add(horizentalLine);
					root.getChildren().add(horizentalLine1);
					root.getChildren().add(horizentalLine2);
					root.getChildren().add(horizentalLine3);
					root.getChildren().add(horizentalLine4);
					root.getChildren().add(horizentalLine5);
					root.getChildren().add(horizentalLine6);
					root.getChildren().add(horizentalLine7);
					root.getChildren().add(horizentalLine8);
					root.getChildren().add(horizentalLine9);
					root.getChildren().add(horizentalLine10);
					root.getChildren().add(horizentalLine11);
					root.getChildren().add(horizentalLine12);
					root.getChildren().add(horizentalLine13);
					root.getChildren().add(horizentalLine14);
					
					
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
