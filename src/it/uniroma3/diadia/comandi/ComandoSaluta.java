package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	private static String MESSAGGIO_STANZA_VUOTA = "chi dovrei salutare? nella stanza non vi sono personaggi";
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null) {
			partita.getConsole().mostraMessaggio(MESSAGGIO_STANZA_VUOTA);
		}
		else {
			partita.getConsole().mostraMessaggio(personaggio.saluta());
		}
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

}
