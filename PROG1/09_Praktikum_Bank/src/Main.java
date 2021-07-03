/**
 * @author Gabriele Pace (pacegab1)
 *
 */

public class Main {

	public static void main(String[] args) {
		Bankkonto normaleKonto = new NormaleKonto("Jolly Jumper", 30000.0);
		Bankkonto salaerkonto = new Salaerkonto("Donald Duck", -2000.0, 5000.0);
		Bankkonto nummernkonto = new Nummernkonto("getNumber()", 100000.0);
		Bankkonto nummernkonto1 = new Nummernkonto("getNumber()", 100000.0);

		System.out.println(normaleKonto.toString());
		System.out.println(salaerkonto.toString());
		System.out.println(nummernkonto.toString());
		
		System.out.println("\n" + nummernkonto1.toString());
	}

}
