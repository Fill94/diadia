package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOconsole;
import it.uniroma3.diadia.Partita;
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
		partita = new Partita(new IOconsole());
		stanzaCorrenteTest = new Stanza("stanzaTest");
		stanzaAdiacenteTest = new Stanza("stanzaAdiacenteTest");
		
		stanzaCorrenteTest.impostaStanzaAdiacente(NORD, stanzaAdiacenteTest);
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
}
