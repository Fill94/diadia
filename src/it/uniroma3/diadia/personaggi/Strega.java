package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
/**
 * Strega: se interagiamo con una strega questa ci trasferisce in una stanza tra quelle adiacenti.
 *	Siccome è permalosa:
 *	- se non l’abbiamo ancora salutata, ci «trasferisce» nella stanza adiacente che contiene meno attrezzi
 * 	- altrimenti in quella che contiene più attrezzi
 */
public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_PIU_ATTREZZI = "Visto che sei stato educato ti trasferisco nella stanza con maggior"
											+ "numero di attrezzi";
	private static final String MESSAGGIO_MENO_ATTREZZI = "Visto che non mi hai salutato ti trasferisco nella stanza con minor"
											+ "numero di attrezzi";
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		SortedMap<Integer, List<Stanza>> attrezzi2StanzeAdiacenti = attrezzi2Stanze(partita.getStanzaCorrente());
		if(this.getHaSalutato()) {
			Stanza stanzaConPiuAttrezzi = attrezzi2StanzeAdiacenti.get(attrezzi2StanzeAdiacenti.lastKey()).get(0); //scelgo come stanza la prima della lista
			partita.setStanzaCorrente(stanzaConPiuAttrezzi);
			return MESSAGGIO_PIU_ATTREZZI;
		}
		Stanza stanzaConMenoAttrezzi = attrezzi2StanzeAdiacenti.get(attrezzi2StanzeAdiacenti.firstKey()).get(0);
		partita.setStanzaCorrente(stanzaConMenoAttrezzi);
		return MESSAGGIO_MENO_ATTREZZI;
	}
	/**
	 * il metodo ritorna una mappa con chiave il numero di attrezzi presenti nella stanza
	 * e come valore le stanze adiacenti con tale numero di attrezzi al loro interno
	 * @param stanza
	 * @return mappa attrezziToStanze
	 */
	private static SortedMap<Integer, List<Stanza>> attrezzi2Stanze(Stanza stanza){
		SortedMap<Integer, List<Stanza>> attrezzi2Adiacenti = new TreeMap<>();
		Collection<Stanza> adiacenti = stanza.getMapStanzeAdiacenti().values();
		List<Stanza> stanze = null;
		for(Stanza s : adiacenti) {
			int numeroAttrezziNellaStanza = s.getNumeroAttrezzi();
			if(!attrezzi2Adiacenti.containsKey(numeroAttrezziNellaStanza)) {
				stanze  = new ArrayList<>();
			}
			else {
				stanze = attrezzi2Adiacenti.get(s.getNumeroAttrezzi());
			}
			stanze.add(s);
			attrezzi2Adiacenti.put(numeroAttrezziNellaStanza, stanze);
		}
		return attrezzi2Adiacenti;
	}
}
