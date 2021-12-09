package application;

import java.util.ArrayList;
import java.util.Arrays;

public class TextHandler {
	// all known course codes
	final ArrayList<String> COURSE_CODES = new ArrayList<String>(Arrays.asList("ACCT", "AFSP", "APMA", "ARHI", "ART",
			"ASIA", "ASTR", "AUGIE", "BIOL", "BUSN", "CHEM", "CHNS", "CHST", "CLAS", "COMM", "CORE", "CSC", "CSD",
			"DATA", "ECON", "EDMU", "EDUC", "ENCW", "ENGL", "ENGR", "ENTM", "ENVR", "FOOD", "FREN", "FRST", "FYH",
			"FYI", "GEOG", "GEOL", "GIST", "GRD", "GREK", "GRMN", "GRST", "HEPE", "HIST", "HONR", "ISS", "JPN", "JPST",
			"KINS", "LATN", "LING", "LSC", "LTAM", "MATH", "MJMC", "MSCI", "MUCH", "MUEN", "MUSC", "MULS", "NTGR",
			"PHIL", "PHYS", "POLS", "PSYC", "PUBH", "RELG", "SCAN", "SLP", "SOAN", "SPAN", "SPRI", "SPST", "SWED",
			"THEA", "WGSS", "WLIT", "WLCC"));
	// all known building codes
	final ArrayList<String> BUILDING_NAMES = new ArrayList<String>(
			Arrays.asList("ABST", "AND", "ANNX", "ARPO", "ARTS", "BERG", "BROD", "BRUN", "CARH", "CARV", "DENK", "EVLD",
					"JDPL", "LIBR", "LIND", "OLDM", "OLIN", "SCIE", "SORN", "SWEN"));
	// all known times for classes
	final ArrayList<String> DAYS = new ArrayList<String>(Arrays.asList("M ", "Tu ", "W ", "Th ", "F ", "M W F ",
			"Tu Th ", "M W ", "M F ", "W F ", "Tu W Th F ", "M Tu W Th F "));

	// initiates lists for the included variables to be displayed on the calendar
	private ArrayList<String> courseCodes = new ArrayList<String>();
	private ArrayList<String> courseName = new ArrayList<String>();
	private ArrayList<String> teacherName = new ArrayList<String>();
	private ArrayList<String> classroom = new ArrayList<String>();
	private ArrayList<String> primaryDay = new ArrayList<String>();
	private ArrayList<String> secondaryDay = new ArrayList<String>();
	private ArrayList<String> primaryTime = new ArrayList<String>();
	private ArrayList<String> secondaryTime = new ArrayList<String>();
	// initiates courseArray which contains all of the course
	// objects that were inputed
	private ArrayList<Course> courseArray = new ArrayList<Course>();

	public TextHandler(String input) {
		// splits submitted text into array and splits it at each new line
		String[] splitInput = input.split("\n");
		// parses through each line of text
		for (int i = 0; i < splitInput.length; i++) {
			String current = splitInput[i];
			String[] currentsplit = current.split("-");
			// checks if line contains a course code. If so, it knows it's a new class
			// and then adds course code and name to their respective lists
			if (COURSE_CODES.contains(currentsplit[0])) {
				// creates a placeholder for the other lists
				courseCodes.add(splitInput[i]);
				courseName.add(splitInput[i + 1]);
				teacherName.add(" ");
				primaryDay.add(" ");
				primaryTime.add(" ");
				secondaryDay.add(" ");
				secondaryTime.add(" ");
				classroom.add(" ");
				// checks if line is professor name
			} else if (current.contains(",")) {
				teacherName.add(courseCodes.size() - 1, current);
				// checks if line is building code
			} else if (BUILDING_NAMES.contains(current)) {
				classroom.add(courseCodes.size() - 1, splitInput[i] + " " + splitInput[i + 1]);
				// checks if line is the days of class
			} else if (DAYS.contains(current)) {
				// checks if the primary days for this class is empty still,
				// if so, we add the days to primary days list
				if (primaryDay.get(courseCodes.size() - 1).equals(" ")) {
					primaryDay.add(courseCodes.size() - 1, splitInput[i]);
					// if it is not empty, then these days are secondary days and
					// are added to the respective list
				} else {
					secondaryDay.add(courseCodes.size() - 1, splitInput[i]);
				}
				// checks if line is a time frame
			} else if (current.contains(":") && (current.contains("AM") || current.contains("PM"))) {
				// checks if the primary time for this class is empty still,
				// if so, we add the time to primary times list
				if (primaryTime.get(courseCodes.size() - 1).equals(" ")) {
					primaryTime.add(courseCodes.size() - 1, splitInput[i]);
					// if it is not empty, then this time frame is a secondary time and
					// is added to the respective list
				} else {
					secondaryTime.add(courseCodes.size() - 1, splitInput[i]);
				}
			}
		}

		// loops through and adds each course to the courseArray by accessing each
		// array. Each index is a separate class
		for (int n = 0; n < courseCodes.size(); n++) {
			courseArray.add(new Course(courseCodes.get(n), courseName.get(n), teacherName.get(n), classroom.get(n),
					primaryDay.get(n), secondaryDay.get(n), primaryTime.get(n), secondaryTime.get(n)));

		}
	}

	public ArrayList<Course> getCourses() {
		return courseArray;
	}

}
