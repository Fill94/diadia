package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	private Partita partitaTest;
	private Stanza stanzaTest;
	@Before 
	public void setUp() {
		this.partitaTest = new Partita(new IOconsole(new Scanner(System.in)));
		this.stanzaTest = new Stanza("stanzaTest");
	}
	@Test
	public void testSetStanzaCorrente() {
		this.partitaTest.setStanzaCorrente(stanzaTest);
		assertSame(stanzaTest, partitaTest.getStanzaCorrente());
	}
	/*
	 * a match is won if the current room is the winning room
	 * */
	@Test
	public void testVintaStanzaCorrenteNonVincente() {
		assertFalse(this.partitaTest.vinta());
	}
	@Test
	public void testVintaStanzaCorrenteVincente() {
		Stanza stanzaVincente =this.partitaTest.getLabirinto().getStanzaVincente();
		this.partitaTest.setStanzaCorrente(stanzaVincente);
		assertTrue(this.partitaTest.vinta());
	}
	/*
	 * a match is finished if the current room is the winning room, the user prompted the "fine" command or 
	 * all the cfu have been consumed by the player
	 */
	@Test
	public void testIsFinitaVinta() {
		this.partitaTest.setStanzaCorrente(this.partitaTest.getLabirinto().getStanzaVincente());
		assertTrue(this.partitaTest.isFinita());
	}
	
	@Test
	public void testIsFinitaCfuEsauriti() {
		this.partitaTest.getGiocatore().setCfu(0);
		assertTrue(this.partitaTest.isFinita()); 
	}
	@Test
	public void testIsFinitaCfuComandoFine() {
		this.partitaTest.setFinita();
		assertTrue(this.partitaTest.isFinita());
	}
}
