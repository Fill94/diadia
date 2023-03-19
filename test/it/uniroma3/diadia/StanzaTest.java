package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	private Stanza stanzatest;
	private Stanza stanzaAdiacente;
	private Stanza stanzaAdiacente2;
	private static final String NORD = "nord";
	private static final String SUD = "sud";
	@Before
	public void setUp() {
		this.stanzatest = new Stanza("stanzaTest");
		this.stanzaAdiacente = new Stanza("stanzaAdiacenteTest");
		this.stanzaAdiacente2 = new Stanza("stanzaAdiacenteTest2");
	}
	@Test
	public void testImpostaStanzaAdiacenteNessunaStanzaAdiacente() {
		assertEquals(0, this.stanzatest.getNumeroStanzeAdiacenti());
		this.stanzatest.impostaStanzaAdiacente(NORD, stanzaAdiacente);
		assertEquals(1, this.stanzatest.getNumeroStanzeAdiacenti());
		assertEquals("stanzaAdiacenteTest", this.stanzatest.getStanzaAdiacente(NORD).getNome());
	}
	
	@Test
	public void testImpostaStanzaAdiacenteUnaStanzaAdiacente() {
		this.stanzatest.impostaStanzaAdiacente(NORD, stanzaAdiacente);
		this.stanzatest.impostaStanzaAdiacente(SUD, stanzaAdiacente2);
		assertEquals(2, this.stanzatest.getNumeroStanzeAdiacenti());
		assertEquals("stanzaAdiacenteTest", this.stanzatest.getStanzaAdiacente(NORD).getNome());
		assertEquals("stanzaAdiacenteTest2", this.stanzatest.getStanzaAdiacente(SUD).getNome());
	}
	@Test
	public void testImpostaStanzaAdiacenteSovrascriviStanzaAdiacente() {
		this.stanzatest.impostaStanzaAdiacente(NORD, stanzaAdiacente);
		assertEquals(1, this.stanzatest.getNumeroStanzeAdiacenti());
		this.stanzatest.impostaStanzaAdiacente(NORD, stanzaAdiacente2);
		assertEquals(1, this.stanzatest.getNumeroStanzeAdiacenti());
		assertEquals("stanzaAdiacenteTest2", this.stanzatest.getStanzaAdiacente(NORD).getNome());
	}
	
	@Test
	public void testGetStanzaAdiacente() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAttrezzo() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasAttrezzo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAttrezzo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAttrezzo() {
		fail("Not yet implemented");
	}

}
