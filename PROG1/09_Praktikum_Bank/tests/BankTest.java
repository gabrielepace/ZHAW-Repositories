import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BankTest {

	/*
	 * Instances from Main Class
	 */
	Bankkonto normaleKonto = new NormaleKonto("Jolly Jumper", 30000.0);
	Bankkonto salaerkonto = new Salaerkonto("Donald Duck", -2000.0, 5000.0);
	Bankkonto nummernkonto = new Nummernkonto("getNumber()", 100000.0);

	List<Double> expected = new ArrayList<Double>();

	/*
	 * JUnit Tests for NormaleKonto
	 */
	@Test
	public void nKgeldEinzahlen() {
		normaleKonto.geldEinbezahlen(1500.0);
		expected.add(1000.0);
		assertEquals(expected, normaleKonto.getKontostand());
		expected.remove(normaleKonto);
	}

	@Test
	public void nKgeldAbheben() {
		normaleKonto.geldAbheben(1000.0);
		expected.add(29000.0);
		assertEquals(expected, normaleKonto.getKontostand());
		expected.remove(normaleKonto);
	}

	/*
	 * JUnit Tests for Salaerkonto
	 */
	@Test
	public void sKgeldEinzahlen() {
		salaerkonto.geldEinbezahlen(1500.0);
		expected.add(1500.0);
		assertEquals(expected, salaerkonto.getKontostand());
		expected.remove(salaerkonto);
	}

	@Test
	public void sKgeldAbheben() {
		salaerkonto.geldAbheben(1000.0);
		expected.add(-3000.0);
		assertEquals(expected, salaerkonto.getKontostand());
		expected.remove(salaerkonto);
	}

	/*
	 * JUnit Tests for Nummernkonto
	 */
	@Test
	public void nrKgeldEinzahlen() {
		nummernkonto.geldEinbezahlen(1500.0);
		expected.add(1500.0);
		assertEquals(expected, nummernkonto.getKontostand());
		expected.remove(nummernkonto);
	}

	@Test
	public void nrKgeldAbheben() {
		nummernkonto.geldAbheben(1000.0);
		expected.add(99000.0);
		assertEquals(expected, nummernkonto.getKontostand());
		expected.remove(nummernkonto);
	}

}
