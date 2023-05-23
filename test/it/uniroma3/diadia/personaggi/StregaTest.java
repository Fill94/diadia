package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class StregaTest {
	Partita partitaTest;
	Labirinto labirintoTest;
	Strega stregaTest;
	@Before
	public void setUp() throws Exception {
		String stanzaIniziale = "stanzaIniziale";
		String stanzaFinale = "stanzaFinale";
		String stanzaCasuale = "stanzaCasuale";
		this.labirintoTest = new LabirintoBuilder()
				.addStanzaIniziale(stanzaIniziale)
				.addStanza(stanzaCasuale)
				.addAttrezzo("attrezzo1", 1)
				.addAttrezzo("attrezzo2", 2)
				.addStanzaVincente(stanzaFinale)
				.addAttrezzo("attrezzo3", 3)
				.addAdiacenza(stanzaIniziale, stanzaCasuale, Direzione.NORD)
				.addAdiacenza(stanzaCasuale, stanzaIniziale, Direzione.SUD)
				.addAdiacenza(stanzaIniziale, stanzaFinale, Direzione.EST)
				.addAdiacenza(stanzaFinale, stanzaCasuale, Direzione.OVEST)
				.getLabirinto();
		this.partitaTest = new Partita(new IOconsole(), labirintoTest);
		this.stregaTest = new Strega("Strega", "sono una strega cattiva");
	}
	
	@Test
	public void testAgisciStanzaMenoAttrezzi() {
		assertEquals(new Stanza("stanzaIniziale") , this.partitaTest.getStanzaCorrente());
		//this.stregaTest.saluta();
		this.stregaTest.agisci(partitaTest);
		assertEquals(new Stanza("stanzaFinale"), this.partitaTest.getStanzaCorrente());
	}
	@Test
	public void testAgisciStanzaMenPiuAttrezzi() {
		assertEquals(new Stanza("stanzaIniziale") , this.partitaTest.getStanzaCorrente());
		this.stregaTest.saluta();
		this.stregaTest.agisci(partitaTest);
		assertEquals(new Stanza("stanzaCasuale"), this.partitaTest.getStanzaCorrente());
	}
	

}
