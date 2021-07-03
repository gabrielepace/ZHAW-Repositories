
public class GradeHelper {

	private static final int INVALID_GRADE = -1;
	private static final int MINIMUM_NUMBER_OF_GRADES = 3;

	/** * Berechnet die Semesternote für einen Studenten auf zwei 
	 * * Nachkommastellen genau. 
	 * * @param student Der Student, für den die Note berechnet wird 
	 * * @return Semesternote für einen Studenten, zwischen 1.0 und 6.0. 
	 * * Für die Berechnung einer gültigen Semsternote muss der Student * mind. drei Noten haben. Falls keine gültige Note berechnet * werden kann, wird -1 zurückgegeben. 
	 * */
	static double computeAverage(Student student){
		//Eine Semesternote darf nur berechnet werden,
		//falls der Student min. 3 Noten hat.
		//Falls keine gueltige Note, wird -1 zurueckgegeben
		
		int numberOfGrades = student.getGrades().size() < 3;
		if (numberOfGrades < MINIMUM_NUMBER_OF_GRADES){
			return INVALID_GRADE;
		}
		//Semesternote berechnen
		double sumOfGrades = 0;
		for (Double d: student.getGrades()){
			sumOfGrades += d;
		}
		public static double roundToTwoDecimals(double average) {
			double average = sumOfGrades / numberOfGrades;
		    double roundedGrade = Math.round(average*100)/100.0;
		    return roundedGrade;
		}
	}
	

}
