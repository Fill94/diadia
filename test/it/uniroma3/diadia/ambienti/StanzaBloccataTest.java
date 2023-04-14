package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private StanzaBloccata bloccataANord;
	private Stanza stanzaAdiecenteANord;
	private Attrezzo attrezzoSbloccante;
	@Before
	public void setUp() throws Exception {
		this.bloccataANord = new StanzaBloccata("bloccata", "chiave", "nord");
		this.attrezzoSbloccante = new Attrezzo("chiave", 1);
		this.stanzaAdiecenteANord = new Stanza("adiacenteNord");
		bloccataANord.impostaStanzaAdiacente("nord", stanzaAdiecenteANord);
	}

	@Test
	public void testGetStanzaAdiacenteNoAttrezzoSbloccante() {
		assertSame(bloccataANord, bloccataANord.getStanzaAdiacente("nord"));
	}
	@Test
	public void testGetStanzaAdiacenteConAttrezzoSbloccante() {
		bloccataANord.addAttrezzo(attrezzoSbloccante);
		assertSame(stanzaAdiecenteANord, bloccataANord.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzoSbloccante() {
		assertEquals("in questa stanza vi è un porta bloccata, servirebbe qualcosa per aprirla se vuoi andare dall'altra parte!\n" +
				bloccataANord.getNome() + "\nUscite: " + "\nUscita bloccate: " + "nord" + "\nAttrezzi nella stanza: ", bloccataANord.getDescrizione());
	}
	@Test
	public void testGetDescrizioneConAttrezzoSbloccante() {
		bloccataANord.addAttrezzo(attrezzoSbloccante);
		assertEquals("Hai trovato quello che serviva per sbloccare la direzione!\n" +
					bloccataANord.getNome() + "\nUscite: " + " " + "nord" + "\nAttrezzi nella stanza: " + attrezzoSbloccante.toString() 
					+ " ", bloccataANord.getDescrizione());
	}


}
