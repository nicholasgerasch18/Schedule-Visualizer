package application;

//test
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTest {

	private Course normalCourse = new Course("BIOL-362-01", "Human Physiology", "Gehler, Scott R.", "SCIE 102",
			"M W F ", " ", "12:00PM - 1:15PM", " ");

	private Course secondaryTimeCourse = new Course("MUEN-209-NC", "Symphonic Band", "Lambrecht, James M", "BERG ER",
			"M W F ", "Tu Th ", "4:30PM - 5:30PM", "4:15PM - 5:30PM");

	private Course noDaysCourse = new Course("CORE-INTR-BT", "Core Internship", "Tidball, Roberta", " ", " ", " ", " ",
			" ");

	@Test
	public void normalCourseTest() {

		assertEquals("BIOL-362-01", normalCourse.getCourseCode());
		assertEquals("Human Physiology", normalCourse.getCourseName());
		assertEquals("Gehler, Scott R.", normalCourse.getteacherName());
		assertEquals("SCIE 102", normalCourse.getClassroom());
		assertEquals("M W F ", normalCourse.getPrimaryDay());
		assertEquals("12:00PM - 1:15PM", normalCourse.getPrimaryTime());
		assertEquals(" ", normalCourse.getSecondaryDay());
		assertEquals(" ", normalCourse.getSecondaryTime());

	}

	@Test
	public void secondaryTimeCourseTest() {

		assertEquals("MUEN-209-NC", secondaryTimeCourse.getCourseCode());
		assertEquals("Symphonic Band", secondaryTimeCourse.getCourseName());
		assertEquals("Lambrecht, James M", secondaryTimeCourse.getteacherName());
		assertEquals("BERG ER", secondaryTimeCourse.getClassroom());
		assertEquals("M W F ", secondaryTimeCourse.getPrimaryDay());
		assertEquals("4:30PM - 5:30PM", secondaryTimeCourse.getPrimaryTime());
		assertEquals("Tu Th ", secondaryTimeCourse.getSecondaryDay());
		assertEquals("4:15PM - 5:30PM", secondaryTimeCourse.getSecondaryTime());

	}

	@Test
	public void noDaysCourseTest() {

		assertEquals("CORE-INTR-BT", noDaysCourse.getCourseCode());
		assertEquals("Core Internship", noDaysCourse.getCourseName());
		assertEquals("Tidball, Roberta", noDaysCourse.getteacherName());
		assertEquals(" ", noDaysCourse.getClassroom());
		assertEquals("None", noDaysCourse.getPrimaryDay());
		assertEquals(" ", noDaysCourse.getPrimaryTime());
		assertEquals(" ", noDaysCourse.getSecondaryDay());
		assertEquals(" ", noDaysCourse.getSecondaryTime());

	}

}
