package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosaTest {
	private static final String NOME_ATTREZZO = "nomeAttrezzo";
	private ComandoPosa posaTest;
	private Giocatore giocatoreTest;
	private Attrezzo attrezzoTest;
	private Stanza stanzaTest;
	private Partita partitaTest;
	@Before
	public void setUp() throws Exception {
		posaTest = new ComandoPosa();
		giocatoreTest = new Giocatore();
		attrezzoTest = new Attrezzo(NOME_ATTREZZO, 1);
		stanzaTest = new Stanza("stanzaTest");
		partitaTest = new Partita(new IOconsole(new Scanner(System.in)));
		
		giocatoreTest.getInventario().addAttrezzo(attrezzoTest);
		partitaTest.setStanzaCorrente(stanzaTest);
		partitaTest.setGiocatore(giocatoreTest);
	}

	@Test
	public void posaTestAttrezzoNull() {
		this.posaTest.esegui(partitaTest);
		assertFalse(this.giocatoreTest.getInventario().isEmpty());
		assertEquals(0 , this.stanzaTest.getNumeroAttrezzi());
	}
	@Test
	public void posaTestAttrezzoInseistente() {
		this.posaTest.setParametro("attrezzoInesistente");
		this.posaTest.esegui(partitaTest);
		assertFalse(this.giocatoreTest.getInventario().isEmpty());
		assertEquals(0 , this.stanzaTest.getNumeroAttrezzi());
	}
	@Test
	public void posaTestAttrezzoEistente() {
		this.posaTest.setParametro(NOME_ATTREZZO);
		this.posaTest.esegui(partitaTest);
		assertTrue(this.giocatoreTest.getInventario().isEmpty());
		assertEquals(1 , this.stanzaTest.getNumeroAttrezzi());
	}
	@Test
	public void posaTestAttrezzoConStessoNomeNellaStanza() {
		this.stanzaTest.addAttrezzo(new Attrezzo(NOME_ATTREZZO, 1));
		this.posaTest.setParametro(NOME_ATTREZZO);
		this.posaTest.esegui(partitaTest);
		assertTrue(this.giocatoreTest.getInventario().hasAttrezzo(NOME_ATTREZZO));
		assertTrue(this.stanzaTest.getNumeroAttrezzi() == 1);
	}

}
