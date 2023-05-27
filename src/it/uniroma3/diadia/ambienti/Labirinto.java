package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//	public Labirinto(String nomeFileLabirinto) throws FileNotFoundException , FormatoFileNonValidoException{
//		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFileLabirinto);
//		caricatore.carica();
//		this.stanzaIniziale = caricatore.getStanzaIniziale();
//		this.stanzaVincente = caricatore.getStanzaVincente();
//		
//	}
	private Labirinto(Stanza stanzaIniziale, Stanza vincente) {
		this.stanzaIniziale = stanzaIniziale;
		this.stanzaVincente = vincente;
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
	
	public static class LabirintoBuilder {
		//private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Stanza stanzaIniziale;
		private Stanza stanzaVincente;
		private Map<String, Stanza> nomi2Stanze;

		public LabirintoBuilder() {
			//this.labirinto = new Labirinto();
			this.ultimaStanzaAggiunta = null;
			this.nomi2Stanze = new HashMap<>();
		}
		public LabirintoBuilder(String nomeFileLabirinto) throws FormatoFileNonValidoException, FileNotFoundException {
			CaricatoreLabirinto loader = new CaricatoreLabirinto(nomeFileLabirinto);
			loader.carica();
			this.stanzaIniziale = loader.getStanzaIniziale();
			this.stanzaVincente = loader.getStanzaVincente();
			this.ultimaStanzaAggiunta = null;
			this.nomi2Stanze = new HashMap<>();
		}
		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			//stanza già presente nel labirinto?
			if(nomi2Stanze.containsKey(stanzaIniziale)) {
				this.stanzaIniziale = nomi2Stanze.get(stanzaIniziale);
				this.ultimaStanzaAggiunta = nomi2Stanze.get(stanzaIniziale);
			}
			else {
				Stanza stanza = new Stanza(stanzaIniziale);
				this.stanzaIniziale = stanza;
				this.nomi2Stanze.put(stanzaIniziale, stanza);
				this.ultimaStanzaAggiunta = stanza;
			}
			return this;
		}
		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			if(nomi2Stanze.containsKey(stanzaVincente)) {
				this.stanzaVincente = nomi2Stanze.get(stanzaVincente);
				this.ultimaStanzaAggiunta = nomi2Stanze.get(stanzaVincente);
			}
			else {
				Stanza stanza = new Stanza(stanzaVincente);
				this.stanzaVincente = stanza;
				this.nomi2Stanze.put(stanzaVincente, stanza);
				this.ultimaStanzaAggiunta = stanza;
			}
			return this;
		}
		/**
		 * aggiunge un attrezzo all'ultima stanza aggiunta al labirinto
		 * @param nomeAttrezzo il nome dell'attrezzo da aggiungere
		 * @param pesoAttrezzo il peso dell'attrezzo da aggiungere 
		 * @return il labirintoBuilder
		 */
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			return this;
		}
		/**
		 * aggiunge a stanzaAcuiAggiungereAdiacenza in direzioneDiAdicenza la stanzaAdiacente
		 * @param stanzaAcuiAggiungereAdiacenza
		 * @param stanzaAdiacente
		 * @param direzioneDiAdicenza
		 * @return il LabirintoBuilder
		 */
		public LabirintoBuilder addAdiacenza(String stanzaAcuiAggiungereAdiacenza, String stanzaAdiacente, Direzione direzioneDiAdicenza) {
			Stanza stanza1 = this.nomi2Stanze.get(stanzaAcuiAggiungereAdiacenza);
			Stanza stanza2 = this.nomi2Stanze.get(stanzaAdiacente);
			stanza1.impostaStanzaAdiacente(direzioneDiAdicenza, stanza2);
			return this;
		}
		public LabirintoBuilder addStanza(String nomeStanza) {
			if(!this.nomi2Stanze.containsKey(nomeStanza)) {
				Stanza stanza = new Stanza(nomeStanza);
				nomi2Stanze.put(nomeStanza, stanza);
				this.ultimaStanzaAggiunta = stanza;
			}
			return this;
		}
		public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzoIlluminante) {
			if(!this.nomi2Stanze.containsKey(nomeStanza)) {
				StanzaBuia stanzaBuia = new StanzaBuia(nomeStanza, nomeAttrezzoIlluminante);
				nomi2Stanze.put(nomeStanza, stanzaBuia);
				this.ultimaStanzaAggiunta = stanzaBuia;
			}
			return this;
		}
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String nomeAttrezzoSbloccante, Direzione direzioneBloccata) {
			if(!this.nomi2Stanze.containsKey(nomeStanza)) {
				StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanza, nomeAttrezzoSbloccante, direzioneBloccata);
				nomi2Stanze.put(nomeStanza, stanzaBloccata);
				this.ultimaStanzaAggiunta = stanzaBloccata;
			}
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
			if(!this.nomi2Stanze.containsKey(nomeStanza)) {
				StanzaMagica stanzaMagica = new StanzaMagica(nomeStanza, soglia);
				nomi2Stanze.put(nomeStanza, stanzaMagica);
				this.ultimaStanzaAggiunta = stanzaMagica;
			}
			return this;
		}
		public List<Stanza> listaStanze() {
			List<Stanza> listaStanze = new ArrayList<Stanza>(this.nomi2Stanze.values());
			return listaStanze;
		}
		public Map<String, Stanza> getMappaStanze() {
			return this.nomi2Stanze;
		}
		public Labirinto getLabirinto() {
			return new Labirinto(this.stanzaIniziale, this.stanzaVincente);
		}
	}
	
	public static LabirintoBuilder newBuilder(String fileDiConfigLabirinto) throws FormatoFileNonValidoException, FileNotFoundException {
		return new LabirintoBuilder(fileDiConfigLabirinto);
	}
}
