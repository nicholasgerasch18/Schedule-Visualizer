package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTest {
	
	private Course testCourse = new Course("BIOL-362-01", "Human Physiology", "Gehler, Scott R.", "SCIE 102", "M W F ", "", "12:00PM - 1:15PM", "");
	
	
	@Test
	public void test() {
		assertEquals("BIOL-362-01", testCourse.getCourseCode());
		assertEquals("Human Physiology", testCourse.getCourseName());
		assertEquals("Gehler, Scott R.", testCourse.getteacherName());
		assertEquals("SCIE 102", testCourse.getClassroom());
		assertEquals("M W F ", testCourse.getPrimaryDay());
		assertEquals("12:00PM - 1:15PM", testCourse.getPrimaryTime());
		assertEquals("", testCourse.getSecondaryDay());
		assertEquals("", testCourse.getSecondaryTime());

	}
	
	
	
}
