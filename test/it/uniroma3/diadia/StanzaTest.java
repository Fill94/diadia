package it.uniroma3.diadia;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	private Stanza stanzatest;
	private Stanza stanzaAdiacente;
	private Stanza stanzaAdiacente2;
	private Attrezzo attrezzoTest;
	private static final String NORD = "nord";
	private static final String SUD = "sud";
	
	@Before
	public void setUp() {
		this.stanzatest = new Stanza("stanzaTest");
		this.stanzaAdiacente = new Stanza("stanzaAdiacenteTest");
		this.stanzaAdiacente2 = new Stanza("stanzaAdiacenteTest2");
		this.attrezzoTest = new Attrezzo("attrezzoTest", 1);
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
	public void testGetStanzaAdiacenteNessunaStanzaAdiacente() {
		assertNull(this.stanzatest.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testGetStanzaAdiacenteEsisteStanzaAdiacente() {
		this.stanzatest.impostaStanzaAdiacente(NORD, stanzaAdiacente);
		assertSame(stanzaAdiacente, this.stanzatest.getStanzaAdiacente(NORD)); 
	}

	@Test
	public void testAddAttrezzoNessunAttrezzo() {
		assertTrue(this.stanzatest.addAttrezzo(attrezzoTest));
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
	}
	
	@Test
	public void testAddAttrezzoUnAttrezzo() {
		assertTrue(this.stanzatest.addAttrezzo(attrezzoTest));
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
		assertTrue(this.stanzatest.addAttrezzo(attrezzoTest));
		assertEquals(2, this.stanzatest.getNumeroAttrezzi());
	}
	
	
	@Test
	public void testAddAttrezzoNumeroMassimoAttrezzi() {
		for(int i = 0 ; i < 10 ; i++ )
			this.stanzatest.addAttrezzo(new Attrezzo("attrezzoTest"+i , 1));
		assertEquals(10, this.stanzatest.getNumeroAttrezzi());
		assertFalse(this.stanzatest.addAttrezzo(attrezzoTest));
	}

	@Test
	public void testHasAttrezzoNonEsistente() {
		assertFalse(this.stanzatest.hasAttrezzo("attrezzoTest"));
	}
	
	@Test
	public void testHasAttrezzoEsistente() {
		this.stanzatest.addAttrezzo(attrezzoTest);
		assertTrue(this.stanzatest.hasAttrezzo("attrezzoTest"));
	}

	@Test
	public void testGetAttrezzoInesistente() {
		assertNull(this.stanzatest.getAttrezzo("attrezzoTest"));
	}
	
	@Test
	public void testGetAttrezzoEsistente() {
		this.stanzatest.addAttrezzo(attrezzoTest);
		assertSame(this.attrezzoTest, this.stanzatest.getAttrezzo("attrezzoTest"));
	}
	@Test
	public void testRemoveAttrezzo() {
		fail("Not yet implemented");
	}

}
