package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	private static final String NORD = "nord";
	private ComandoVai vaiTest;
	private Stanza stanzaCorrenteTest;
	private Stanza stanzaAdiacenteTest;
	private Partita partita;
	@Before
	public void setUp() throws Exception {
		vaiTest = new ComandoVai();
		partita = new Partita(new IOconsole(new Scanner(System.in)));
		stanzaCorrenteTest = new Stanza("stanzaTest");
		stanzaAdiacenteTest = new Stanza("stanzaAdiacenteTest");
		
		stanzaCorrenteTest.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacenteTest);
		partita.setStanzaCorrente(stanzaCorrenteTest);
	}
	@Test
	public void eseguiTestDirezioneNull() {
		vaiTest.esegui(partita);
		assertSame(stanzaCorrenteTest, partita.getStanzaCorrente());
	}
	@Test
	public void eseguiTestDirezioneSbagliata() {
		vaiTest.setParametro("sud");
		vaiTest.esegui(partita);
		assertSame(stanzaCorrenteTest, partita.getStanzaCorrente());
	}
	@Test
	public void eseguiTestDirezioneEsistente() {
		vaiTest.setParametro(NORD);
		vaiTest.esegui(partita);
		assertSame(this.stanzaAdiacenteTest, partita.getStanzaCorrente());
	}
	@Test
	public void ComandoVaiTrilocale() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Stanza iniziale")
				.addStanzaVincente("Stanza finale")
				.addStanza("Stanza adiacente")
				.addAdiacenza("Stanza iniziale", "Stanza adiacente", Direzione.EST)
				.addAdiacenza("Stanza adiacente", "Stanza iniziale", Direzione.OVEST)
				.addAdiacenza("Stanza iniziale", "Stanza finale", Direzione.NORD)
				.addAdiacenza("Stanza finale", "Stanza iniziale", Direzione.SUD)
				.getLabirinto();
		this.partita = new Partita(new IOconsole(new Scanner(System.in)), labirinto);
		vaiTest.setParametro("est");
		vaiTest.esegui(partita);
		assertEquals(new Stanza("Stanza adiacente") , this.partita.getStanzaCorrente());
		vaiTest.setParametro("ovest");
		vaiTest.esegui(partita);
		assertEquals(new Stanza("Stanza iniziale") , this.partita.getStanzaCorrente());
		vaiTest.setParametro("nord");
		vaiTest.esegui(partita);
		assertEquals(new Stanza("Stanza finale") , this.partita.getStanzaCorrente());
	}
}
