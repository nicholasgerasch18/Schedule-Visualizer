package application;

//test
public interface CourseInterface {
	public String getCourseCode();

	public String getCourseName();

	public String getteacherName();

	public String getClassroom();

	public String getPrimaryDay();

	public String getPrimaryTime();

	public String getSecondaryDay();

	public String getSecondaryTime();

	public String toString(String dayDescription);
	
	public String getPrimaryStartTime();
	
	public String getPrimaryEndTime();
	
	public String getSecondaryStartTime();
	
	public String getSecondaryEndTime();
	
	public boolean hasTime();
	
	public int primaryMilitaryStart(String Time);
	
	public int primaryMilitaryEnd();
	
	public void setConflicting(Boolean conflict);
	
	public Boolean isConflicting();
	
}
