package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	private StanzaMagica magicRoomTest;
	private Attrezzo attrezzoTest;
	@Before
	public void setUp() throws Exception {
		this.magicRoomTest = new StanzaMagica("magicaTest", 2);
		this.attrezzoTest = new Attrezzo("attrezzo", 2);
	}

	@Test
	public void testAddAttrezzoSogliaMagicaNonSuperata() {
		this.magicRoomTest.addAttrezzo(attrezzoTest);
		assertFalse(magicRoomTest.hasAttrezzo("ozzertta"));
		assertTrue(magicRoomTest.hasAttrezzo("attrezzo"));
	}
	@Test
	public void testAddAttrezzoSogliaMagicaSuperata() {
		for(int i = 0; i < 3; i++) {
			this.magicRoomTest.addAttrezzo(attrezzoTest);
		}
		assertTrue(magicRoomTest.hasAttrezzo("ozzertta"));
		assertEquals(4, magicRoomTest.getAttrezzo("ozzertta").getPeso());
	}

}
