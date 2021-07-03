/**
 * 
 */
import java.util.ArrayList;

public class Course {
	private String courseName;
	private ArrayList<Student> studenten;
	private String klasse;
	private Lecturer dozent;

	public Course(String courseName, String klasse, Lecturer dozent) {
		this.courseName = courseName;
		this.studenten = new ArrayList<Student>();
		this.klasse = klasse;
		this.dozent = dozent;
	}
	/**
	 * 
	 * @return
	 */
	public String getCourseName() {
		return courseName;
	}

	public ArrayList<Student> getStudenten() {
		return studenten;
	}

	public String getKlasse() {
		return klasse;
	}

	public Lecturer getDozent() {
		return dozent;
	}
	
}