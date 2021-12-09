package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Gabriel Vallejo
 * 
 *Generates a calendar implementing the CalendarInterface.
 */
public class CalendarGenerator implements CalendarInterface {

	public Group root;
	public HashMap<String, Integer> coordinateDictionary = new HashMap<>(100);
	public HashMap<Rectangle, String> courseBoxDictionary = new HashMap<>(15);

	private ArrayList<Integer> xCoordinates = new ArrayList<>();
	private ArrayList<Integer> yCoordinates = new ArrayList<>();

	/**
	 * Constructs the calendar to be used when adding courses.
	 * 
	 * @param root - the Group object to which all methods add the calendar drawings
	 *               to.
	 */
	public CalendarGenerator(Group root) {
		this.root = root;

		addDays(root);
		addTimestamps(root);
		addHorizontalLines(root);
		addVerticalLines(root);
		makeCoordinateDictionary();

	}

	/**
	 * 
	 * @return - allows the calendar to be accessed in the main(ScheduleVisualizer)
	 *         class.
	 */
	public Group getCalendar() {
		return root;
	}

	/**
	 * Adds the days of the week to the calendar.
	 */
	public void addDays(Group root) {
		String[] dayArray = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		int x = 100;
		int y = 10;
		//for every day of the week it creates a text box and puts it in the correct location on the calendar
		for (String item : dayArray) {
			Text text = new Text(item);
			text.setX(x);
			xCoordinates.add(x);
			x += 100;
			text.setY(y);
			text.setFont(Font.font("Verdana", 12));
			root.getChildren().add(text);

		}

	}

	/**
	 * Adds the times of day to the calendar
	 */
	public void addTimestamps(Group root) {

		String[] timeArray = { "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM",

				"4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM", "Other \nCourses: " };

		int x = 10;
		int y = 40;
		//for every time stamp it creates a text box and puts it on the correct location on the calendar
		for (String timestamp : timeArray) {
			Text time = new Text(timestamp);
			time.setX(x);
			time.setY(y);
			yCoordinates.add(y);

			y += 60;
			time.setFont(Font.font("Verdana", 12));
			root.getChildren().add(time);

		}

	}

	/**
	 * Adds the vertical lines to the calendar.
	 */
	public void addVerticalLines(Group root) {

		int x = 75;

		// draws the 8 vertical lines
		for (int i = 0; i < 8; i++) {
			Line verticalLine = new Line();
			verticalLine.setStartX(x);
			verticalLine.setEndX(x + 1);
			verticalLine.setStartY(10);
			verticalLine.setEndY(875);
			x += 100;
			root.getChildren().add(verticalLine);
		}

	}

	/**
	 * Adds horizontal lines to the calendar creating a grid-like appearance for the
	 * section of the calendar where the course boxes will be placed.
	 */
	public void addHorizontalLines(Group root) {

		int y = 35;

		// draws the 15 horizontal lines and stores the line location into dictionary
		// for ease of access
		for (int j = 0; j < 15; j++) {
			Line horizontalLine = new Line();
			horizontalLine.setStartX(75);
			horizontalLine.setEndX(775);
			horizontalLine.setStartY(y);
			horizontalLine.setEndY(y);
			// add time location to dictionary
			y += 60;
			root.getChildren().add(horizontalLine);

		}

	}

	/**
	 * Creates a dictionary storing the x-coordinate locations for each day of the
	 * week and the y-coordinate locations for each time stamp.
	 * 
	 */
	private void makeCoordinateDictionary() {
		ArrayList<String> daysOfWeek = new ArrayList<String>(Arrays.asList("M", "Tu", "W", "Th", "F"));
		ArrayList<String> times = new ArrayList<>(
				Arrays.asList("8AM", "9AM", "10AM", "11AM", "12PM", "1PM", "2PM", "3PM",

						"4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM"));

		// stores x-coordinates for the days of the week
		for (int g = 0; g < 5; g++) {
			coordinateDictionary.put(daysOfWeek.get(g), xCoordinates.get(g));
		}
		// stores y-coordinates for the each time stamp
		for (int g = 0; g < 15; g++) {
			coordinateDictionary.put(times.get(g), yCoordinates.get(g));
		}
	}

	/**
	 * Method allowing the coordinate dictionary to be accessed in the
	 * main(ScheduleVisualizer) class
	 * 
	 * @return - coordinateDictionary
	 */
	public HashMap<String, Integer> getCoordinateDictionary() {
		return coordinateDictionary;
	}

	/**
	 * Creates the courseBox dictionary where the specs of the rectangles are stored
	 * including the coordinate locations.
	 * 
	 * @param courseBox - the course being added to the calendar
	 * @param xCoord    - the x coordinate of the course box
	 * @param yCoord    - the top y coordinate of the course box
	 * @param height    - the bottom y coordinate of the course box
	 */
	public void createBoxDictionary(Rectangle courseBox, int xCoord, int yCoord, double height) {

		String value = Integer.toString(xCoord) + " " + Integer.toString(yCoord) + " " + Double.toString(height);
		courseBoxDictionary.put(courseBox, value);
	}

}
