import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MusicJukeBoxTest {

	private JukeBox jukeBox = null;

	@Before
	public void setUp() throws Exception {
		jukeBox = new MusicJukeBox();
	}

	@Test(expected = JukeBoxException.class)
	public void testPlayOfNoneExistingSong() throws JukeBoxException {
		// Aufgabe 1a
		jukeBox.playTitle("NoneExistingTitle");
		fail("should no be executed");
	}

	@Test
	public void testGetPlayList() {
		// Aufgabe 1b
		Song mock = mock(Song.class);
		when(mock.getTitle()).thenReturn("d");

		jukeBox.addSong(mock);

		assertEquals(1, jukeBox.getPlayList().size());
		verify(mock, times(1)).getTitle();

		jukeBox.addSong(mock);
		jukeBox.addSong(mock);
		jukeBox.addSong(mock);
		verify(mock, times(4)).getTitle();
	}

	@Test
	public void testPlay() {
		jukeBox.addSong(new SongStub("d"));
		jukeBox.playTitle("d");

		Song song = jukeBox.getActualSong();
		assertEquals("d", song.getTitle());

		assertTrue(song.isPlaying());
	}

	@Test(expected = JukeBoxException.class)
	public void testPlayOfAlreadyPlayingSong() {
		// Aufgabe 1c
		Song mock = mock(Song.class);
		
		JukeBox jukebox = new MusicJukeBox();
		
		doThrow(JukeBoxException.class).when(mock).start();
		
		jukebox.addSong(new SongStub("d"));
		jukebox.playTitle("d");
		jukebox.playTitle("d");
		mock.start();
	}

	@Test
	public void testPlayMock() {
		// Aufgabe 1d
		Song mock = mock(Song.class);
		
		JukeBox jukebox = new MusicJukeBox();

		jukebox.addSong(mock);
		jukebox.playTitle("d");
		mock.start();
		mock.isPlaying();
		

		InOrder inOrder = inOrder(mock);
		inOrder.verify(jukebox).addSong(mock);
		inOrder.verify(jukebox).playTitle("d");
		inOrder.verify(mock).start();
		inOrder.verify(mock).isPlaying();

		jukebox.getActualSong();

		verify(jukebox, times(1)).addSong(mock);
		verify(jukebox, times(1)).playTitle("d");
	}

	@Test
	public void aufgabe2a() {
		Song mock = mock(Song.class);

		Answer<String> answer = new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return "Callback";
			}
		};

		when(mock.getTitle()).thenAnswer(answer);
		doAnswer(answer).when(mock).getTitle();
	}

	// Aufgabe 2b
	@Mock
	LinkedList<String> mockList; // --> Übernimmt nur Funktionsdefinition (keine
									// Logik)
	@Spy
	LinkedList<String> spyList; // --> Übernimmt Funktionsdefinition und Logik

	@Before
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		when(spyList.size()).thenReturn(100);
	}

	@Test
	public void aufgabe2b() {

		assertEquals(0, mockList.size());
		assertEquals(100, spyList.size());

		spyList.add("1");
		spyList.add("2");
		assertEquals(0, mockList.size());
		assertEquals(100, spyList.size());
		assertEquals("1", spyList.get(0));
		assertEquals("2", spyList.get(1));

		mockList.add("1");
		mockList.add("2");
		assertEquals(0, mockList.size());
		assertEquals(100, spyList.size());
		// da keine funktionalität implementeirt wird das obj auch nicht
		// abgelegt
		assertEquals(null, mockList.get(0));
		assertEquals(null, mockList.get(1));

	}

	@Test
	public void aufgabe2c() {
		// Egal welcher Index angesprochen wird, es kommt elementValue zurück
		LinkedList mockedList = mock(LinkedList.class);
		// stubbing using anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("elementValue");
		// Returns "elementValue" for any index in the list
		assertEquals(mockedList.get(0), "elementValue");
		assertEquals(mockedList.get(1), "elementValue");
		assertEquals(mockedList.get(2), "elementValue");

	}

	@Test
	public void aufgabe3() {
		// 3a)
		// BlackBox (Junit)
		// --> Prüfung der Richtigen Nutzung und Ergebnisse
		// WhiteBox (Mock, JUnit)
		// --> Prüfung der richtigen Abfolge und Aufrufe

		// 3b)
		// http://www.vogella.com/tutorials/Hamcrest/article.html
		List<Integer> list = Arrays.asList(5, 2, 4);

		assertThat(list, hasSize(3));

		// ensure the order is correct
		assertThat(list, contains(5, 2, 4));

		assertThat(list, containsInAnyOrder(2, 4, 5));

		assertThat(list, everyItem(greaterThan(1)));

		/*
		 * The following are the most important Hamcrest matchers: allOf - matches if
		 * all matchers match (short circuits) anyOf - matches if any matchers match
		 * (short circuits) not - matches if the wrapped matcher doesn’t match and vice
		 * equalTo - test object equality using the equals method is - decorator for
		 * equalTo to improve readability hasToString - test Object.toString instanceOf,
		 * isCompatibleType - test type notNullValue, nullValue - test for null
		 * sameInstance - test object identity hasEntry, hasKey, hasValue - test a map
		 * contains an entry, key or value hasItem, hasItems - test a collection
		 * contains elements hasItemInArray - test an array contains an element closeTo
		 * - test floating point values are close to a given value greaterThan,
		 * greaterThanOrEqualTo, lessThan, lessThanOrEqualTo equalToIgnoringCase - test
		 * string equality ignoring case equalToIgnoringWhiteSpace - test string
		 * equality ignoring differences in runs of whitespace
		 */

		// 3c) Other Frameworks:
		// https://blog.idrsolutions.com/2015/02/8-useful-java-testing-tools-frameworks-programmers-developers-coders/

	}

}
