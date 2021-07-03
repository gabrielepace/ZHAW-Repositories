import java.util.ArrayList;

/**
 * Die Klasse Student verwaltet einen Studenten.
 * 
 * Hinweis: Die Klasse ist absichtlich unvollstaendig
 * 
 * @author meea
 * @version 1.0
 */
public class Student {
	private String name;
	private ArrayList<Double> noten = new ArrayList<Double>();
	
	/**
	 * Erzeugt einen Studenten mit dem spezifizierten Namen
	 * @param name Name des Studenten
	 */
	public Student(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Namen des Studenten zurueck
	 * @return Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a grade for a student
	 * @return grade the new grade to be added
	 */
	public void addGrade(Double grade) {
		if(grade < 1) {
			grade = 1.0;
		}else{
			grade = 6.0;
		}
		noten.add(grade);
	}
	
	/**
	 * Get a grade for a student
	 * @return grade of the student
	 */
	public ArrayList<Double> getGrades(){
		return noten;
	}

}
