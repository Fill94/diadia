package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
		this.personaggio = null;
	}
	public Stanza(String nome, AbstractPersonaggio personaggio) {
		this(nome);
		this.personaggio = personaggio;
	}
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if(this.stanzeAdiacenti.keySet().size() < NUMERO_MASSIMO_DIREZIONI) {
			this.stanzeAdiacenti.put(direzione, stanza);
		}
		return;
	}
	/**
	 * Utility method per il testing 
	 * @return
	 */
	public int  getNumeroStanzeAdiacenti() {
		return this.stanzeAdiacenti.values().size();
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;		
		stanza = this.stanzeAdiacenti.get(direzione);
		return stanza;
	}
	public Map<String, Stanza> getMapStanzeAdiacenti(){
		return this.stanzeAdiacenti;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.attrezzi.containsKey(attrezzo.getNome()))
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	/**
	 * utility method per il testing di addAttrezzo
	 * @return
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.values().size();
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\nDESCRIZIONE STANZA :");
		risultato.append("\nNome: "+this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.getDirezioni())
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.getAttrezzi()) {
			if(attrezzo != null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato = null;
		if(this.hasAttrezzo(nomeAttrezzo))
			attrezzoCercato = this.attrezzi.get(nomeAttrezzo);
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(this.hasAttrezzo(attrezzo.getNome())) {
			this.attrezzi.remove(attrezzo.getNome());
			return true;
		}	
		return false;
	}


	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getNome().hashCode();
	}

	public AbstractPersonaggio getPersonaggio() {
		// TODO Auto-generated method stub
		return this.personaggio;
	}
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

}