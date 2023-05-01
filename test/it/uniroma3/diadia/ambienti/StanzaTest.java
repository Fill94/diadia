package it.uniroma3.diadia.ambienti;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private static final String ATTREZZO_TEST = "attrezzoTest";
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
		this.attrezzoTest = new Attrezzo(ATTREZZO_TEST, 1);
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
	public void testImpostaStanzaAdiacenteNumeroMassimoDirezioniRaggiunto() {
		this.stanzatest.impostaStanzaAdiacente(NORD, stanzaAdiacente);
		this.stanzatest.impostaStanzaAdiacente(SUD, stanzaAdiacente2);
		this.stanzatest.impostaStanzaAdiacente("EST", new Stanza("stanza a est"));
		this.stanzatest.impostaStanzaAdiacente("OVEST", new Stanza("stanza a ovest"));
		assertEquals(4, this.stanzatest.getNumeroStanzeAdiacenti());
		this.stanzatest.impostaStanzaAdiacente("DIREZIONE IN ECCESSO", new Stanza("stanza di troppo"));
		assertEquals(4, this.stanzatest.getNumeroStanzeAdiacenti());
		assertFalse(this.stanzatest.getDirezioni().contains("DIREZIONE IN ECCESSO"));
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
	public void testAddAttrezzoNessunAttrezzoNellaStanza() {
		assertTrue(this.stanzatest.addAttrezzo(attrezzoTest));
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
	}
	
	@Test
	public void testAddAttrezzoUnAttrezzoNellaStanza() {
		assertTrue(this.stanzatest.addAttrezzo(attrezzoTest));
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
		assertFalse(this.stanzatest.addAttrezzo(attrezzoTest));
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
	}
	
//	------ TEST DEPRECATO, NON VI STA PIù UN LIMITE DI ATTREZZI
//	@Test
//	public void testAddAttrezzoNumeroMassimoAttrezziNellaStanza() {
//		for(int i = 0 ; i < 10 ; i++ )
//			this.stanzatest.addAttrezzo(new Attrezzo(ATTREZZO_TEST+i , 1));
//		assertEquals(10, this.stanzatest.getNumeroAttrezzi());
//		assertFalse(this.stanzatest.addAttrezzo(attrezzoTest));
//	}

	@Test
	public void testHasAttrezzoNonEsistente() {
		assertFalse(this.stanzatest.hasAttrezzo(ATTREZZO_TEST));
	}
	
	@Test
	public void testHasAttrezzoEsistente() {
		this.stanzatest.addAttrezzo(attrezzoTest);
		assertTrue(this.stanzatest.hasAttrezzo(ATTREZZO_TEST));
	}

	@Test
	public void testGetAttrezzoInesistente() {
		assertNull(this.stanzatest.getAttrezzo(ATTREZZO_TEST));
	}
	
	@Test
	public void testGetAttrezzoEsistente() {
		this.stanzatest.addAttrezzo(attrezzoTest);
		assertSame(attrezzoTest, this.stanzatest.getAttrezzo(ATTREZZO_TEST));
	}
	@Test
	public void testRemoveAttrezzoUnAttrezzoNellaStanza() {
		this.stanzatest.addAttrezzo(attrezzoTest);
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
		assertTrue(this.stanzatest.removeAttrezzo(attrezzoTest));
		assertEquals(0, this.stanzatest.getNumeroAttrezzi());
		assertFalse(this.stanzatest.hasAttrezzo(ATTREZZO_TEST));
	}
	@Test
	public void testRemovePrimoAttrezzoDueAttrezzoNellaStanza() {
		this.stanzatest.addAttrezzo(attrezzoTest);
		Attrezzo attrezzoTest2 = new Attrezzo("attrezzoTest2", 1);
		this.stanzatest.addAttrezzo(attrezzoTest2);
		assertEquals(2, this.stanzatest.getNumeroAttrezzi());
		assertTrue(this.stanzatest.removeAttrezzo(attrezzoTest2));
		assertEquals(1, this.stanzatest.getNumeroAttrezzi());
		assertFalse(this.stanzatest.hasAttrezzo("attrezzoTest2"));
	}
	

}
