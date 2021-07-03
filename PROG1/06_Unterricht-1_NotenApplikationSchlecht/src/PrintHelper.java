
public class PrintHelper {
	private static void printCourse() {
		// Noten ausgeben
				System.out.println("Programmieren 1");
				System.out.println("Semesternote Klasse "+course.getKlasse());
				System.out.println("Dozent "+ course.getDozent().getName());
				System.out.println("*************************");
				
				for(Student s : course.getStudenten()){
					System.out.println(s.getName()+" "+ GradeHelper.computeAverage(s));
				}
	}
}
