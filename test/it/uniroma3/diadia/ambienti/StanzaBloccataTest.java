package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private StanzaBloccata bloccataANord;
	private Stanza stanzaAdiecenteANord;
	private Attrezzo attrezzoSbloccante;
	@Before
	public void setUp() throws Exception {
		this.bloccataANord = new StanzaBloccata("bloccata", "chiave", Direzione.NORD);
		this.attrezzoSbloccante = new Attrezzo("chiave", 1);
		this.stanzaAdiecenteANord = new Stanza("adiacenteNord");
		bloccataANord.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiecenteANord);
	}

	@Test
	public void testGetStanzaAdiacenteNoAttrezzoSbloccante() {
		assertSame(bloccataANord, bloccataANord.getStanzaAdiacente(Direzione.NORD));
	}
	@Test
	public void testGetStanzaAdiacenteConAttrezzoSbloccante() {
		bloccataANord.addAttrezzo(attrezzoSbloccante);
		assertSame(stanzaAdiecenteANord, bloccataANord.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzoSbloccante() {
		assertEquals("in questa stanza vi sta una porta bloccata, servirebbe qualcosa per aprirla se vuoi andare dall'altra parte!\n" + "\nDESCRIZIONE STANZA :\nNome: "+
				bloccataANord.getNome() + "\nUscite: " + "\nUscita bloccate: " + "NORD" + "\nAttrezzi nella stanza: ", bloccataANord.getDescrizione());
	}
	@Test
	public void testGetDescrizioneConAttrezzoSbloccante() {
		bloccataANord.addAttrezzo(attrezzoSbloccante);
		assertEquals("Hai trovato quello che serviva per sbloccare la direzione!\n" + "\nDESCRIZIONE STANZA :\nNome: "+
					bloccataANord.getNome() + "\nUscite: " + " " + "NORD" + "\nAttrezzi nella stanza: " + attrezzoSbloccante.toString() 
					+ " "+"\nPersonaggio nella stanza: ", bloccataANord.getDescrizione());
	}


}
