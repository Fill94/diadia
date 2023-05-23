package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class DiaDiaTest {
	private DiaDia diaDiaTest;
	private IOSimulator IOtest;
	static private final String MESSAGGIO_DI_BENVENUTO = "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
														"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
														"I locali sono popolati da strani personaggi, " +
														"alcuni amici, altri... chissa!\n"+
														"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
														"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
														"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
														"Per conoscere le istruzioni usa il comando 'aiuto'.";
	@Before
	public void setUp() throws Exception {
		this.IOtest = new IOSimulator();
	}
	private IO impostaIO(String ...comandi ) {
		for(String s : comandi)
			IOtest.addComando(s);
		return this.IOtest;
	}
	private Labirinto bilocaleVuoto(String nomeStanzaIniziale, Direzione direzioneAdiacenzaStanzaFinaleRispettoStanzaIniziale, String nomeStanzaFinale, Direzione direzioneAdiacenzaStanzaInizialeRispettoStanzaFinale) {
		Labirinto labirinto = new LabirintoBuilder()
								.addStanzaIniziale(nomeStanzaIniziale)
								.addStanzaVincente(nomeStanzaFinale)
								.addAdiacenza(nomeStanzaIniziale, nomeStanzaFinale, direzioneAdiacenzaStanzaFinaleRispettoStanzaIniziale)
								.addAdiacenza(nomeStanzaFinale, nomeStanzaIniziale, direzioneAdiacenzaStanzaInizialeRispettoStanzaFinale)
								.getLabirinto();
		return labirinto;
	}
	@Test
	public void partitaVintaBilocaleVuoto() {
		this.diaDiaTest = new DiaDia(IOtest, bilocaleVuoto("atrio", Direzione.NORD, "biblioteca", Direzione.SUD));
		this.impostaIO("vai nord");
		this.diaDiaTest.gioca();
		assertTrue(this.IOtest.getMessaggi(null).contains(MESSAGGIO_DI_BENVENUTO));
		assertTrue(this.IOtest.getMessaggi("vai nord").contains("Hai vinto!"));
	}
	@Test
	public void paritaPersa() {
		Labirinto trilocale = new LabirintoBuilder()
								.addStanzaIniziale("Atrio")
								.addStanzaVincente("Biblioteca")
								.addStanza("N10")
								.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
								.addAdiacenza("Atrio", "N10", Direzione.EST)
								.addAdiacenza("N10", "Atrio", Direzione.OVEST)
								.getLabirinto();
		this.diaDiaTest = new DiaDia(IOtest, trilocale);
		String[] comandi = new String[20];
		for(int i = 0 ; i < 20; i++) {
			if(i%2 == 0)
				comandi[i]= "vai est";
			else
				comandi[i]= "vai ovest";
		}
		this.impostaIO(comandi);
		diaDiaTest.gioca();
		assertTrue(this.IOtest.getMessaggi("vai ovest").contains("CFU esauriti, Sei morto!"));
	}
	
	@Test
	public void partitaConStanzaMagicaEstanzaBloccata() {
		Labirinto trilocale = new LabirintoBuilder()
				.addStanzaBloccata("Atrio", "chiave", Direzione.NORD)
				.addStanzaIniziale("Atrio")
				.addAttrezzo("evaihc", 1)//chiave
				.addStanzaVincente("Biblioteca")
				.addStanzaMagica("N10", 2)
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addAdiacenza("Atrio", "N10", Direzione.EST)
				.addAdiacenza("N10", "Atrio", Direzione.OVEST)
				.getLabirinto();
		this.impostaIO("vai nord", "prendi evaihc", "vai est", "posa evaihc", "prendi evaihc", "posa evaihc", "prendi evaihc", "posa evaihc", "prendi chiave", "vai ovest","posa chiave", "vai nord");
		diaDiaTest = new DiaDia(IOtest, trilocale);
		diaDiaTest.gioca();
		assertTrue(this.IOtest.getMessaggi("vai nord").contains("Hai vinto!"));
	}
	
	/*
	@Test
	public void testParitaVinta() {
		this.impostaIO("vai est", "vai sud", "prendi chiave", "vai nord", "vai ovest", "vai nord", "posa chiave", "vai nord");
		diaDiaTest.gioca();
		assertTrue(this.IOtest.getLastMessaggio().contains("Complimenti, hai vinto!"));
	}
	@Test
	public void testParitaPersa() {
		String[] comandi = new String[21];
		for(int i = 0 ; i < 21; i++) {
			if(i%2 == 0)
				comandi[i]="vai sud";
			else
				comandi[i]="vai nord";
		}
		this.impostaIO(comandi);
		diaDiaTest.gioca();
		assertTrue(this.IOtest.getLastMessaggio().contains("CFU esauriti"));
	}
	*/

}
