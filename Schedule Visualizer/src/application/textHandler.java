package application;

import java.util.ArrayList;
import java.util.Arrays;

public class textHandler {
	
	final ArrayList<String> COURSE_CODES = new ArrayList<String>(Arrays.asList("ACCT", "AFSP", "APMA", "ARHI", "ART",
			"ASIA", "ASTR", "AUGIE", "BIOL", "BUSN", "CHEM", "CHNS", "CHST", "CLAS", "COMM", "CORE", "CSC", "CSD",
			"DATA", "ECON", "EDMU", "EDUC", "ENCW", "ENGL", "ENGR", "ENTM", "ENVR", "FOOD", "FREN", "FRST", "FYH",
			"FYI", "GEOG", "GEOL", "GIST", "GRD", "GREK", "GRMN", "GRST", "HEPE", "HIST", "HONR", "ISS", "JPN", "JPST",
			"KINS", "LATN", "LING", "LSC", "LTAM", "MATH", "MJMC", "MSCI", "MUCH", "MUEN", "MUSC", "MULS", "NTGR",
			"PHIL", "PHYS", "POLS", "PSYC", "PUBH", "RELG", "SCAN", "SLP", "SOAN", "SPAN", "SPRI", "SPST", "SWED",
			"THEA", "WGSS", "WLIT", "WLCC"));
	final ArrayList<String> BUILDING_NAMES = new ArrayList<String>(
			Arrays.asList("ABST", "AND", "ANNX", "ARPO", "ARTS", "BERG", "BROD", "BRUN", "CARH", "CARV", "DENK", "EVLD",
					"JDPL", "LIBR", "LIND", "OLDM", "OLIN", "SCIE", "SORN", "SWEN"));
	final ArrayList<String> DAYS = new ArrayList<String>(Arrays.asList("M ", "Tu ", "W ", "Th ", "F ", "M W F ",
			"Tu Th ", "M W ", "M F ", "W F ", "Tu W Th F ", "M Tu W Th F "));
	
	private ArrayList<String> courseCodes = new ArrayList<String>();
	private ArrayList<String> courseName = new ArrayList<String>();
	private ArrayList<String> teacherName = new ArrayList<String>();
	private ArrayList<String> classroom = new ArrayList<String>();
	private ArrayList<String> primaryDay = new ArrayList<String>();
	private ArrayList<String> secondaryDay = new ArrayList<String>();
	private ArrayList<String> primaryTime = new ArrayList<String>();
	private ArrayList<String> secondaryTime = new ArrayList<String>();
	private ArrayList<Course> courseArray = new ArrayList<Course>();
	
	public textHandler(String input) {
		String[] splitInput = input.split("\n");
		for (int i = 0; i < splitInput.length; i++) {
			String current = splitInput[i];
			String[] currentsplit = current.split("-");
			if (COURSE_CODES.contains(currentsplit[0])) {
				courseCodes.add(splitInput[i]);
				courseName.add(splitInput[i + 1]);
				teacherName.add(" ");
				primaryDay.add(" ");
				primaryTime.add(" ");
				secondaryDay.add(" ");
				secondaryTime.add(" ");
				classroom.add(" ");
			} else if (current.contains(",")) {
				teacherName.add(courseCodes.size() - 1, current);
			} else if (BUILDING_NAMES.contains(current)) {
				classroom.add(courseCodes.size() - 1, splitInput[i] + " " + splitInput[i + 1]);
			} else if (DAYS.contains(current)) {
				if (primaryDay.get(courseCodes.size() - 1).equals(" ")) {
					// primaryDay.add(splitInput[i]);
					primaryDay.add(courseCodes.size() - 1, splitInput[i]);
				} else {
					secondaryDay.add(courseCodes.size() - 1, splitInput[i]);
				}
			} else if (current.contains(":")) {
				if (primaryTime.get(courseCodes.size() - 1).equals(" ")) {
					primaryTime.add(courseCodes.size() - 1, splitInput[i]);
				} else {
					secondaryTime.add(courseCodes.size() - 1, splitInput[i]);
				}
			}
		}

		// courseArray contains all of the course objects that were inputed
		
		// loops through and adds each course to the
		for (int n = 0; n < courseCodes.size(); n++) {
			courseArray.add(new Course(courseCodes.get(n), courseName.get(n), teacherName.get(n),
					classroom.get(n), primaryDay.get(n), secondaryDay.get(n), primaryTime.get(n),
					secondaryTime.get(n)));
		}
	}
	
	public ArrayList<Course> getCourses() {
		return courseArray;
	}
	
}
