package application;

import java.util.ArrayList;
import java.util.Arrays;

public class Course implements CourseInterface {

	private String courseCode;
	private String courseName;
	private String professorName;
	private String classroom;
	private String primaryDay;
	private String primaryTime;
	private String secondaryDay;
	private String secondaryTime;
	private ArrayList<String> courseInfo;

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
		this.primaryTime = primaryTime;
		this.secondaryDay = secondaryDay;
		this.secondaryTime = secondaryTime;
		courseInfo = new ArrayList<String>(Arrays.asList(classroom, teacherName, courseCode, courseName, primaryDay,
				primaryTime, secondaryDay, secondaryTime));
	}

	/**
	 * accesses the course code of the Course
	 * 
	 * returns - the course code (String)
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * accesses the name of the Course
	 * 
	 * returns - the course name (String)
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * accesses the professor's name for the Course
	 * 
	 * returns - the professor's name (String)
	 */
	public String getteacherName() {
		return professorName;
	}

	/**
	 * accesses the building name and classroom number/name where the Course is
	 * located
	 * 
	 * returns - the classroom's building and number/name (String)
	 */
	public String getClassroom() {
		return classroom;
	}

	/**
	 * accesses the primary days of the Course
	 * 
	 * returns - the primary days (String)
	 */
	public String getPrimaryDay() {
		return primaryDay;
	}

	/**
	 * accesses the primary time frame of the Course
	 * 
	 * returns - the primary time frame (String)
	 */
	public String getPrimaryTime() {
		return primaryTime;
	}

	/**
	 * accesses the secondary days of the Course
	 * 
	 * returns - the secondary days (String)
	 */
	public String getSecondaryDay() {
		return secondaryDay;
	}

	/**
	 * accesses the secondary time frame of the Course
	 * 
	 * returns - the secondary time frame (String)
	 */
	public String getSecondaryTime() {
		return secondaryTime;
	}

	/**
	 * displays the Course as a String with its attributes
	 * 
	 * returns - Course as a String (String)
	 */
	public String displayClass() {

		String output = "";
		for (int k = 0; k < courseInfo.size(); k++) {
			if (!(courseInfo.get(k).equals("") || courseInfo.get(k).equals(" "))) {
				output += courseInfo.get(k) + "\n";

			}
		}
		return output;
	}

}
