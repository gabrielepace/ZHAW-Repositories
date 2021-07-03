import java.util.ArrayList;

/**
 * Die Klasse NotenBerechnungSchlecht dient zum Verwalten, Berechnen und Anzeigen
 * der Semesternote in der Vorlesung Programmieren 1.
 * 
 * Hinweis: Diese Klasse soll als schlechtes Beispiel dienen.
 * 
 * @author meea
 * @version 1.0
 */
public class NotenBerechnungSchlecht {
	
	private Course course = new Course("Programmieren 1", "IT13d", new Lecturer ("Kent Beck", "TD301", "555-12345"));

	/**
	 * @param args Keine Parameter vorgesehen
	 */
	public static void main(String[] args) {
		new NotenBerechnungSchlecht().start();
	}
	
	/**
	 *  Gibt die Studierenden und ihre Noten in der Vorlesung Programmieren1 auf
	 *  der Konsole aus
	 */
	private void start(){
		testdatenProg1();
		
		PrintHelper.printCourse(course);
		
			
	}
	
	/**
	 *  Hilfsmethode zum Erzeugen von Studenten und Noten
	 */
	private void testdatenProg1() {
		// Testdaten
		Student student = new Student("Peter Muster");
		course.getStudenten().add(student);
		
		student.addGrade(new Double(4.5));
		student.addGrade(new Double(4.0));
		student.addGrade(new Double(4.5));
		
		student = new Student("Lara Croft");
		course.getStudenten().add(student);
		
		student.addGrade(new Double(5.5));
		student.addGrade(new Double(6.0));
		student.addGrade(new Double(4.5));
		
		student = new Student("Pipi Langstrumpf");
		course.getStudenten().add(student);
		
		student.addGrade(new Double(5.5));
	}
}
