package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	private Giocatore giocatoreTest;

	@Before
	public void setUp() {
		this.giocatoreTest = new Giocatore();
	}

	@Test
	public void testGiocatore() {
		assertTrue(this.giocatoreTest.getCfu() == 20);
	}

	@Test
	public void testSetCfu() {
		this.giocatoreTest.setCfu(19);
		assertTrue(this.giocatoreTest.getCfu() == 19);
	}

}
