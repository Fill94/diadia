package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> nomi2Stanze;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.ultimaStanzaAggiunta = null;
		this.nomi2Stanze = new HashMap<>();
	}
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		//stanza già presente nel labirinto?
		if(nomi2Stanze.containsKey(stanzaIniziale)) {
			this.labirinto.setStanzaIniziale(nomi2Stanze.get(stanzaIniziale));
			this.ultimaStanzaAggiunta = nomi2Stanze.get(stanzaIniziale);
		}
		else {
			Stanza stanza = new Stanza(stanzaIniziale);
			this.labirinto.setStanzaIniziale(stanza);
			this.nomi2Stanze.put(stanzaIniziale, stanza);
			this.ultimaStanzaAggiunta = stanza;
		}
		return this;
	}
	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		if(nomi2Stanze.containsKey(stanzaVincente)) {
			this.labirinto.setStanzaVincente(nomi2Stanze.get(stanzaVincente));
			this.ultimaStanzaAggiunta = nomi2Stanze.get(stanzaVincente);
		}
		else {
			Stanza stanza = new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(stanza);
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
	public LabirintoBuilder addAdiacenza(String stanzaAcuiAggiungereAdiacenza, String stanzaAdiacente, String direzioneDiAdicenza) {
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
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String nomeAttrezzoSbloccante, String direzioneBloccata) {
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
		return this.labirinto;
	}
}
