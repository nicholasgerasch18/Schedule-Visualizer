package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarGenerator implements CalendarInterface {

	public Group root;
	public HashMap<String, Integer> coordinateDictionary = new HashMap<>(100);
	public HashMap<Rectangle, String> courseBoxDictionary = new HashMap(15);
	
	private ArrayList<Integer> xCoordinates = new ArrayList<>(); 
	private ArrayList<Integer> yCoordinates = new ArrayList<>(); 


	
	public CalendarGenerator(Group root) {
		this.root = root;

		addDays(root);
		addTimestamps(root);
		addHorizontalLines(root);
		addVerticalLines(root);
		makeCoordinateDictionary();
		

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
		
		String[] timeArray = { "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM",
				
				"4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM" };

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
		ArrayList<String> times = new ArrayList<>(Arrays.asList( "8AM", "9AM", "10AM", "11AM", "12PM", "1PM", "2PM", "3PM",
				
				"4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM" ));

		
		for (int g = 0; g < 5; g++) {
			coordinateDictionary.put(daysOfWeek.get(g), xCoordinates.get(g));
		}
		for (int g = 0; g < 15; g++) {
			coordinateDictionary.put(times.get(g), yCoordinates.get(g));
		}
		//System.out.println(coordinateDictionary);
	}
	
	public HashMap<String, Integer> getCoordinateDictionary(){
		return coordinateDictionary;
	}
	
	public void createBoxDictionary(Rectangle courseBox, int xCoord, int yCoord, double height) {
		
		String value = Integer.toString(xCoord) + " " + Integer.toString(yCoord) + " " + Double.toString(height);
		courseBoxDictionary.put(courseBox, value);
	}
	
	public boolean checkOverLap() {
		boolean overlapping = false;
		ArrayList<String> rectangleSpecsAsString = new ArrayList<>();
		ArrayList<Integer> xCoordList = new ArrayList<>();
		ArrayList<Integer> minYCoordList = new ArrayList<>();
		ArrayList<Double> maxYCoordList = new ArrayList<>();
		
		for( Rectangle rectangle : courseBoxDictionary.keySet()) {
			rectangleSpecsAsString.add(courseBoxDictionary.get(rectangle));
		}
		
		for(int i = 0 ; i < rectangleSpecsAsString.size(); i++) {
			String specString = rectangleSpecsAsString.get(i);
			String[] specArray = specString.split(" ");
			xCoordList.add(Integer.parseInt(specArray[0]));
			minYCoordList.add(Integer.parseInt(specArray[1]));
			maxYCoordList.add(Double.parseDouble(specArray[2]));
		}
		
		
		for(int i = 0; i < xCoordList.size(); i++) {
			if(xCoordList.contains(xCoordList.get(i))){
				for(int k = 0; k < xCoordList.size(); k++) {
					if(xCoordList.get(i) == xCoordList.get(k)) {
						for(int j = 0; j < xCoordList.size(); j++) {
							if(minYCoordList.get(i) > minYCoordList.get(j) &&  minYCoordList.get(i) < maxYCoordList.get(j)) {
								overlapping = true;
							}
						}
					}
				}
			}
		}
		
		return overlapping;
	}
}	











