package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarGenerator implements CalendarInterface {

	public Group root;
	public HashMap<String, Integer> coordinateDictionary = new HashMap<>(100);

	public String[] timeArray = { "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM",
			
			"4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM" };
	
	private ArrayList<Integer> xCoordinates = new ArrayList<>(); 
	private ArrayList<Integer> yCoordinates = new ArrayList<>(); 


	
	public CalendarGenerator(Group root) {
		this.root = root;

		addDays(root);
		addTimestamps(root);
		addHorizontalLines(root);
		addVerticalLines(root);
		makeCoordinateDictionary();
		
		System.out.println(coordinateDictionary);
		System.out.println(xCoordinates);
		System.out.println(yCoordinates);

	}

	public void addDays(Group root) {
		String[] dayArray = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		int x = 100;
		int y = 10;
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

	public void addTimestamps(Group root) {
		
		int x = 10;
		int y = 40;

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

	public void addVerticalLines(Group root) {

		int x = 75;

		
		for (int i = 0; i < 8; i++) {
			Line verticalLine = new Line();
			verticalLine.setStartX(x);
			verticalLine.setEndX(x+1);
			verticalLine.setStartY(10);
			verticalLine.setEndY(875);
			x += 100;
			root.getChildren().add(verticalLine);
		}

	}

	public void addHorizontalLines(Group root) {
		
		int y = 35;
		
		//draws the horizontal lines and stores the line location
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
	
	public Group getCalendar() {
		return root;
	}
	
	/**
	 * 
	 */
	private void makeCoordinateDictionary() {
		ArrayList<String> daysOfWeek = new ArrayList<String>(Arrays.asList("M", "Tu", "W", "Th", "F"));
		
		for (int g = 0; g < 5; g++) {
			coordinateDictionary.put(daysOfWeek.get(g), xCoordinates.get(g));
		}
		for (int g = 0; g < 15; g++) {
			coordinateDictionary.put(timeArray[g], yCoordinates.get(g));
		}

	}
	
	public HashMap<String, Integer> getCoordinateDictionary(){
		return coordinateDictionary;
	}
}