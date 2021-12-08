package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.scene.paint.Color;

public class Course implements CourseInterface {

	private String courseCode;
	private String courseName;
	private String professorName;
	private String classroom;
	private String primaryDay;
	private String primaryTime;
	private String primaryStartTime;
	private String primaryEndTime;
	private String secondaryDay;
	private String secondaryTime;
	private String secondaryStartTime;
	private String secondaryEndTime;
	private Boolean isConflicting;
	private ArrayList<String> courseInfo;
	private Color courseColor;

	private ArrayList<String> primaryDayList;
	private ArrayList<String> secondaryDayList;
	
	/**
	 * creates a course object based on the inputed info
	 * 
	 * @param courseCode    - the course code for the Course
	 * @param courseName    - the name of the Course
	 * @param professorName - the name of the professor teaching the Course
	 * @param classroom     - the building and room number/name where the Course is
	 *                      taught
	 * @param primaryDay    - the days of the primary time frame for the Course
	 * @param secondaryDay  - the days of the secondary time frame for the Course
	 * @param primaryTime   - the primary time frame for the Course
	 * @param secondaryTime - the secondary time frame for the Course
	 */
	public Course(String courseCode, String courseName, String teacherName, String classroom, String primaryDay,
			String secondaryDay, String primaryTime, String secondaryTime) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.professorName = teacherName;
		this.classroom = classroom;
		this.primaryDay = primaryDay;
		primaryDayList = new ArrayList<String>();
		secondaryDayList = new ArrayList<String>();
		isConflicting = false;
		
		//problematic
		if(primaryDay.equals(" ")) {
			this.primaryDay = "M"; 
		}
		
		String[] primaryDaySplit = primaryDay.split(" ");
		for(int i = 0; i<primaryDaySplit.length; i++) {
			primaryDayList.add(primaryDaySplit[i]);
		}
		this.primaryTime = primaryTime;
		if (!primaryTime.equals(" ")) {
			String[] primaryTimeArray = primaryTime.split("-");
			primaryStartTime = primaryTimeArray[0].trim();
			primaryEndTime = primaryTimeArray[1].trim();
		}else {
			this.primaryStartTime = "9:00PM";
			this.primaryEndTime="10:00PM";
			
		}

		this.secondaryDay = secondaryDay;
		this.secondaryTime = secondaryTime;
		if (!secondaryTime.equals(" ")) {
			String[] secondaryTimeArray = secondaryTime.split(" - ");
			secondaryStartTime = secondaryTimeArray[0].trim();
			secondaryEndTime = secondaryTimeArray[1].trim();
			String[] secondaryDaySplit = secondaryDay.split(" ");
			for(int x = 0; x<secondaryDaySplit.length; x++) {
				secondaryDayList.add(secondaryDaySplit[x]);
			}
		}
		
		
		Random rn = new Random();
		int num = rn.nextInt(255) + 1;
		int num2 = rn.nextInt(255) + 1;
		int num3 = rn.nextInt(255) + 1;
		Color color = Color.rgb(num,num2,num3);
		
		courseColor = color;
		
		
		
		
		
		courseInfo = new ArrayList<String>(Arrays.asList(classroom, teacherName, courseCode, courseName, primaryDay,
				primaryTime, secondaryDay, secondaryTime));
	}

	/**
	 * accesses the course code of the Course
	 * 
	 * returns - the course code (String)
	 */
	@Override
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * accesses the name of the Course
	 * 
	 * returns - the course name (String)
	 */
	@Override
	public String getCourseName() {
		return courseName;
	}

	/**
	 * accesses the professor's name for the Course
	 * 
	 * returns - the professor's name (String)
	 */
	@Override
	public String getteacherName() {
		return professorName;
	}

	/**
	 * accesses the building name and classroom number/name where the Course is
	 * located
	 * 
	 * returns - the classroom's building and number/name (String)
	 */
	@Override
	public String getClassroom() {
		return classroom;
	}

	/**
	 * accesses the primary days of the Course
	 * 
	 * returns - the primary days (String)
	 */
	@Override
	public String getPrimaryDay() {
		return primaryDay;
	}

	/**
	 * accesses the primary time frame of the Course
	 * 
	 * returns - the primary time frame (String)
	 */
	@Override
	public String getPrimaryTime() {
		return primaryTime;
	}

	/**
	 * accesses the primary start time of the Course
	 * 
	 * returns - the primary start time (String)
	 */
	@Override
	public String getPrimaryStartTime() {

		return primaryStartTime;
	}

	/**
	 * accesses the primary end time of the Course
	 * 
	 * returns - the primary end time (String)
	 */
	@Override
	public String getPrimaryEndTime() {

		return primaryEndTime;
	}

	/**
	 * accesses the secondary days of the Course
	 * 
	 * returns - the secondary days (String)
	 */
	@Override
	public String getSecondaryDay() {
		return secondaryDay;
	}

	/**
	 * accesses the secondary time frame of the Course
	 * 
	 * returns - the secondary time frame (String)
	 */
	@Override
	public String getSecondaryTime() {
		return secondaryTime;
	}

	/**
	 * accesses the secondary start time of the Course
	 * 
	 * returns - the secondary start time (String)
	 */
	@Override
	public String getSecondaryStartTime() {

		return secondaryStartTime;
	}

	/**
	 * accesses the secondary end time of the Course
	 * 
	 * returns - the secondary end time (String)
	 */
	@Override
	public String getSecondaryEndTime() {

		return secondaryEndTime;
	}

	/**
	 * displays the Course as a String with its attributes
	 * 
	 * returns - Course as a String (String)
	 */
	@Override
	public String toString(String whichDay) {
		String courseDisplay = "";
		if (whichDay.equals("secondary")) {
			 courseDisplay = courseCode + " " + courseName + " \n" + secondaryTime;
		}else {
			courseDisplay = courseCode + " " + courseName + " \n" + primaryTime;
		}
		return courseDisplay;
				
	}
	/**
	 * Checks to see if time is determined by instructor
	 */
	@Override
	public boolean hasTime() {
		if (!primaryDay.equals(" ")) return true;
		return false;
	}

	@Override
	public int primaryMilitaryStart(String Time) {
		// TODO Auto-generated method stub
		int time=0;
		String[] primaryStartTimeSplit = Time.split(":");
		//String[] primaryEndTimeSplit = primaryEndTime.split(":");
		if(primaryStartTimeSplit[1].substring(2,4).equals("PM")) {
			time+=1200;
		}
		if( Integer.parseInt(primaryStartTimeSplit[0])!=12) {
			time+= Integer.parseInt(primaryStartTimeSplit[0]) * 100;
		}
		time+=  Integer.parseInt(primaryStartTimeSplit[1].substring(0,2));
		return time;
	}

	@Override
	public int primaryMilitaryEnd() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setConflicting(Boolean conflict) {
		isConflicting = conflict;
		
	}

	@Override
	public Boolean isConflicting() {
		// TODO Auto-generated method stub
		return isConflicting;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return courseColor;
	}

	@Override
	public void setColor(Color newColor) {
		// TODO Auto-generated method stub
		courseColor = newColor;
	}

	@Override
	public ArrayList<String> getPrimaryDayList() {
		// TODO Auto-generated method stub
		return primaryDayList;
	}

	@Override
	public ArrayList<String> getSecondaryDayList() {
		// TODO Auto-generated method stub
		return secondaryDayList;
	}

}
