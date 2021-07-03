package bean;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class TestNumberGuessBean {

	@Test
	public void testInstanceDefaultValues() {
		NumberGuessBean numberGuessBean = new NumberGuessBean();
		assertEquals("null", numberGuessBean.getHint());
		assertEquals(false, numberGuessBean.getSuccess());
		assertEquals(0, numberGuessBean.getNumGuesses());
	}
	
	@Test
	public void testValuesAfterTwoGuesses() {
		NumberGuessBean numberGuessBean = new NumberGuessBean();
		numberGuessBean.setGuess("0");
		numberGuessBean.setGuess("0");
		assertNotNull(numberGuessBean.getHint());
		assertEquals(false, numberGuessBean.getSuccess());
		assertEquals(2, numberGuessBean.getNumGuesses());
	}
}
