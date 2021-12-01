package application;

import java.util.ArrayList;
import java.util.Arrays;

public class Course implements CourseInterface{
	

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

		public String getCourseCode() {
			return courseCode;
		}
		public String getCourseName() {
			return courseName;
		}
		public String getteacherName() {
			return teacherName;
		}

		public String getClassroom() {
			return classroom;
		}

		public String getPrimaryDay() {
			return primaryDay;
		}

		public String getPrimaryTime() {
			return primaryTime;
		}

		public String getSecondaryDay() {
			return secondaryDay;
		}

		public String getSecondaryTime() {
			return secondaryTime;
		}

		public String displayClass() {

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


