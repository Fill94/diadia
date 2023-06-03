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

public class ComandoPrendiTest {
	private static final String NOME_ATTREZZO = "nomeAttrezzo";
	private ComandoPrendi prendiTest;
	private Giocatore giocatoreTest;
	private Attrezzo attrezzoTest;
	private Stanza stanzaTest;
	private Partita partitaTest;
	
	@Before
	public void setUp() throws Exception {
		prendiTest = new ComandoPrendi();
		giocatoreTest = new Giocatore();
		attrezzoTest = new Attrezzo(NOME_ATTREZZO, 1);
		stanzaTest = new Stanza("stanzaTest");
		partitaTest = new Partita(new IOconsole(new Scanner(System.in)));
		
		stanzaTest.addAttrezzo(attrezzoTest);
		partitaTest.setStanzaCorrente(stanzaTest);
		partitaTest.setGiocatore(giocatoreTest);
	}

	@Test
	public void eseguiTestAttrezzoNull() {
		this.prendiTest.esegui(partitaTest);
		assertTrue(this.stanzaTest.hasAttrezzo(NOME_ATTREZZO));
		assertTrue(this.giocatoreTest.getInventario().isEmpty());
	}
	@Test
	public void eseguiTestAttrezzoInesistente() {
		this.prendiTest.setParametro("attrezzoInesistente");
		this.prendiTest.esegui(partitaTest);
		assertTrue(this.stanzaTest.hasAttrezzo(NOME_ATTREZZO));
		assertTrue(this.giocatoreTest.getInventario().isEmpty());
	}
	@Test
	public void eseguiTestAttrezzoEsistente() {
		this.prendiTest.setParametro(NOME_ATTREZZO);
		this.prendiTest.esegui(partitaTest);
		assertFalse(this.stanzaTest.hasAttrezzo(NOME_ATTREZZO));
		assertFalse(this.giocatoreTest.getInventario().isEmpty());
	}
	@Test
	public void eseguiTestAttrezzoConStessoNomePresenteNellinventario() {
		this.giocatoreTest.getInventario().addAttrezzo(new Attrezzo(NOME_ATTREZZO, 2));
		this.prendiTest.setParametro(NOME_ATTREZZO);
		this.prendiTest.esegui(partitaTest);
		assertTrue(this.stanzaTest.hasAttrezzo(NOME_ATTREZZO));
		assertTrue(this.giocatoreTest.getInventario().getContenutoOrdinatoPerPeso().size() == 1);
	}

}
