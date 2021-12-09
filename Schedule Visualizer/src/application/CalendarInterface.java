package application;

import javafx.scene.Group;

/**
 * 
 * @author Gabriel Vallejo
 *
 */
public interface CalendarInterface {
	public void addDays(Group root);

	public void addTimestamps(Group root);

	public void addVerticalLines(Group root);

	public void addHorizontalLines(Group root);

}
