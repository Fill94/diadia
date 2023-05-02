package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private StanzaBuia buiaTest;
	private Attrezzo attrezzoIlluminante;
	@Before
	public void setUp() throws Exception {
		this.buiaTest = new StanzaBuia("stanzaBuia", "lanterna");
		this.attrezzoIlluminante = new Attrezzo("lanterna", 1);
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		assertEquals("qui vi sta un buio pesto, servirebbe qualcosa per fare luce!", this.buiaTest.getDescrizione());
	}
	@Test
	public void testGetDescrizioneConAttrezzo() {
		buiaTest.addAttrezzo(attrezzoIlluminante);
		assertEquals("\nDESCRIZIONE STANZA :"+"\nNome: "+buiaTest.getNome() + "\nUscite: " + "\nAttrezzi nella stanza: " + 
					attrezzoIlluminante.toString() + " ", this.buiaTest.getDescrizione());
	}

}
