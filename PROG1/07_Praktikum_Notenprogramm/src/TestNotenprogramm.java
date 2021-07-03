import static org.junit.Assert.*;
import org.junit.Test;

public class TestNotenprogramm {

	Pruefungsverwaltung note = new Pruefungsverwaltung();

	ZufaelligeNotengebung notengebung = new ZufaelligeNotengebung();

	/*
	 * JUnit Tests for Pruefungsverwaltung class
	 */
	@Test
	public void rundeHalbeNoten() {
		assertTrue(note.rundeAufHalbeNote(3.24) == 3.0); // Abrunden (approx. down / per difetto)
		assertTrue(note.rundeAufHalbeNote(4.25) == 4.5); // Aufrunden (approx. by excess / per eccesso)
	}

	@Test
	public void generiereText() {
		String ExpectedText1 = "Student Test 1, Sie haben an der Pruefung eine 2.5 (zwei punkt fuenf) erzielt und sind somit durchgefallen!";
		String ExpectedText2 = "Herzliche Gratulation Student Test 2! Sie haben an der Pruefung eine 4.0 (vier punkt null) erzielt und somit bestanden!";

		assertEquals(ExpectedText1, note.generiereText("Student Test 1", 2.5)); // Durchgefallen Text
		assertEquals(ExpectedText2, note.generiereText("Student Test 2", 4.0)); // Bestanden Text
	}

	/*
	 * JUnit Tests for ZufaelligeNotengebung class
	 */
	@Test
	public void generiereZufaelligePruefungsnote() {

		for (int i = 0; i < 10; i++) {
			double randomNote = notengebung.generiereZufaelligePruefungsnote();
			assertTrue(randomNote >= 1 && randomNote <= 6);
		}
	}
}
