package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale;
	private String nomeStanzaFinale;
	@Before
	public void setUp() throws Exception {
		this.labirintoBuilder = new LabirintoBuilder();
		this.nomeStanzaFinale = "Atrio";
		this.nomeStanzaIniziale = "Biblioteca";
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
	}
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("lanterna", 2)
				.addStanzaVincente(nomeStanzaIniziale)
				.addAttrezzo("chiave", 1)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
		assertEquals("chiave", monolocale.getStanzaIniziale().getAttrezzo("chiave").getNome());
		assertEquals("lanterna", monolocale.getStanzaIniziale().getAttrezzo("lanterna").getNome());
	}
	@Test
	public void testMonolocaleConAttrezzoDuplicato() {
		Labirinto monolocale = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("lanterna", 2)
				.addStanzaVincente(nomeStanzaIniziale)
				.addAttrezzo("lanterna", 2)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertEquals("lanterna", monolocale.getStanzaIniziale().getAttrezzo("lanterna").getNome());
		assertTrue(size == 1);
	}
	@Test
	public void testBilocale() {
		Labirinto bilocale = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaFinale)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaFinale, "nord")
				.addAdiacenza(nomeStanzaFinale, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(2, this.labirintoBuilder.listaStanze().size());
		assertEquals(bilocale.getStanzaVincente(), bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(Collections.singleton("nord"), bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singleton("sud"), bilocale.getStanzaVincente().getDirezioni());
	}
	@Test
	public void testTrilocale() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanza("stanzaCasuale")
		.addStanzaVincente(nomeStanzaFinale)
		.addAdiacenza(nomeStanzaIniziale, "stanzaCasuale", "nord")
		.addAdiacenza("stanzaCasuale", nomeStanzaIniziale, "sud")
		.addAdiacenza("stanzaCasuale", nomeStanzaFinale, "nord")
		.addAdiacenza(nomeStanzaFinale, "stanzaCasuale", "sud")
		.getLabirinto();
		assertEquals(3, this.labirintoBuilder.listaStanze().size());
	}
	@Test
	public void testCreazioneTrilocaleConStanzeDuplicate() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanza("stanzaCasuale")
		.addStanzaVincente(nomeStanzaFinale)
		.addStanza("stanzaCasuale")
		.getLabirinto();
		assertEquals(3, this.labirintoBuilder.listaStanze().size());
	}
	@Test
	public void testAddAdiacenzaOltreIlMaxNumeroDiDirezioni() {
		this.labirintoBuilder
			.addStanza(nomeStanzaIniziale)
			.addStanza("stanzaAnord")
			.addAdiacenza(nomeStanzaIniziale, "stanzaAnord", "nord")
			.addAdiacenza("stanzaAnord", nomeStanzaIniziale, "sud")
			.addStanza("stanzaAest")
			.addAdiacenza(nomeStanzaIniziale, "stanzaAest", "est")
			.addAdiacenza("stanzaAest", nomeStanzaIniziale, "ovest")
			.addStanza("stanzaAovest")
			.addAdiacenza(nomeStanzaIniziale, "stanzaAovest", "ovest")
			.addAdiacenza("stanzaAovest", nomeStanzaIniziale, "est")
			.addStanza(nomeStanzaFinale)
			.addAdiacenza(nomeStanzaIniziale, nomeStanzaFinale, "sud")
			.addAdiacenza(nomeStanzaFinale, nomeStanzaIniziale, "nord");
		assertEquals(5, this.labirintoBuilder.listaStanze().size());
		assertEquals(4, this.labirintoBuilder.getLabirinto().getStanzaIniziale().getDirezioni().size());
		this.labirintoBuilder.addStanza("stanzaAnordEst").addAdiacenza(nomeStanzaIniziale, "stanzaAnordEst", "nordEst");
		assertEquals(6, this.labirintoBuilder.listaStanze().size());
		assertEquals(4, this.labirintoBuilder.getLabirinto().getStanzaIniziale().getDirezioni().size());
	}
	
}
