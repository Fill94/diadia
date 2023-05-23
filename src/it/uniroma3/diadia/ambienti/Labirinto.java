package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;


import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.*;

public class Labirinto {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	public Labirinto() {
		this.init();
	}
	public Labirinto(String nomeFileLabirinto) throws FileNotFoundException , FormatoFileNonValidoException{
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFileLabirinto);
		caricatore.carica();
		this.stanzaIniziale = caricatore.getStanzaIniziale();
		this.stanzaVincente = caricatore.getStanzaVincente();
		
	}
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void init() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		Stanza stanzinoBuio = new StanzaBuia("Stanzino Buio", "lanterna");
		Stanza guardiolaBiblioteca = new StanzaBloccata("Guardiola Biblioteca", "chiave", Direzione.NORD);

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.NORD, guardiolaBiblioteca);
		guardiolaBiblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);
		guardiolaBiblioteca.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.SUD, stanzinoBuio);
		aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
		stanzinoBuio.impostaStanzaAdiacente(Direzione.NORD, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
		laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzione.SUD, guardiolaBiblioteca); 

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		stanzinoBuio.addAttrezzo(chiave);
		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

}
