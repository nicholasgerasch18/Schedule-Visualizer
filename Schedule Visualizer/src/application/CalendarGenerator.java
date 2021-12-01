package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarGenerator implements CalendarInterface {

	public Group root;

	public CalendarGenerator(Group root) {
		root = this.root;
		addDays(root);
		addTimestamps(root);
		addVerticalLines(root);
		addHorizontalLines(root);
	}

	public void addDays(Group root) {
		String[] dayArray = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		int x = 100;
		int y = 10;
		for (String item : dayArray) {
			Text text = new Text(item);
			text.setX(x);
			x += 100;
			text.setY(y);
			text.setFont(Font.font("Verdana", 12));
			root.getChildren().add(text);

		}

	}

	public void addTimestamps(Group root) {
		String[] timeArray = { "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm",
				"4:00pm", "5:00pm", "6:00pm", "7:00pm", "8:00pm", "9:00pm", "10:00pm" };
		int x = 0;
		int y = 40;

		for (String timestamp : timeArray) {
			Text time = new Text(timestamp);
			time.setX(x);
			time.setY(y);
			y += 60;
			time.setFont(Font.font("Verdana", 12));
			root.getChildren().add(time);

		}

	}

	public void addVerticalLines(Group root) {

		int x = 75;

		for (int i = 7; i < 7; i++) {
			Line VerticalLine = new Line();
			VerticalLine.setStartX(x);
			VerticalLine.setEndX(x);
			x += 100;
			VerticalLine.setStartY(0);
			VerticalLine.setEndY(1000);
			root.getChildren().add(VerticalLine);
		}

	}

	public void addHorizontalLines(Group root) {
		int y = 35;

		for (int i = 0; i < 15; i++) {
			Line horizontalLine = new Line();
			horizontalLine.setStartX(75);
			horizontalLine.setEndX(775);
			horizontalLine.setStartY(y);
			horizontalLine.setEndY(y);
			y += 60;
			root.getChildren().add(horizontalLine);

		}

	}

}