package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static String MESSAGGIO_NESSUN_PERSONAGGIO ="con chi dovrei interagire? Nella stanza non vi sta nessuno";
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null) {
			partita.getConsole().mostraMessaggio(MESSAGGIO_NESSUN_PERSONAGGIO);
		}
		else {
			partita.getConsole().mostraMessaggio(personaggio.agisci(partita));
		}
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

}
