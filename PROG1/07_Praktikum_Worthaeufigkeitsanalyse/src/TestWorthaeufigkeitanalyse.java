import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class TestWorthaeufigkeitanalyse {

	Worthaeufigkeitsanalyse wort = new Worthaeufigkeitsanalyse();

	@Test
	public void entferneSatzzeichen() {
		assertEquals("N", wort.entferneSatzzeichen("N!"));
	}

	@Test
	public void nichtEntferneSatzzeichen() {
		assertEquals("N.n", wort.entferneSatzzeichen("N.n"));
	}

	@Test
	public void verarbeiteTextGrossKlein() {
		wort.verarbeiteText("test Test");
		HashMap<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("test", 2);
		assertEquals(expected, wort.getWoerterHaeufigkeit());
	}

	@Test
	public void verarbeiteTextNullString() {
		wort.verarbeiteText(null);
		HashMap<String, Integer> expected = new HashMap<String, Integer>();
		assertEquals(expected, wort.getWoerterHaeufigkeit());
	}

	@Test
	public void verarbeiteTextEmptyString() {
		wort.verarbeiteText("");
		HashMap<String, Integer> expected = new HashMap<String, Integer>();
		assertEquals(expected, wort.getWoerterHaeufigkeit());
	}

	@Test
	public void verarbeiteTextWortHaeufigkeit() {
		wort.verarbeiteText("Test hat zwei vorkommen, diese vorkommen sind zwei und zwei des Wortes Test");
		HashMap<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("test", 2);
		expected.put("hat", 1);
		expected.put("zwei", 3);
		expected.put("vorkommen", 2);
		expected.put("diese", 1);
		expected.put("sind", 1);
		expected.put("und", 1);
		expected.put("des", 1);
		expected.put("wortes", 1);
		assertEquals(expected, wort.getWoerterHaeufigkeit());
	}

}
