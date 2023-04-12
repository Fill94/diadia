package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements ComandoInterface {
	private String direzione;
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(direzione == null) {
			partita.getConsole().mostraMessaggio("dove vuoi andare? specifica una direzione");
			return;
		}
		Stanza stanzaAdiacente = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(stanzaAdiacente == null) {
			//direzione sbagliata
			partita.getConsole().mostraMessaggio("direzione inesistente");
			return;
		}
		else {
			partita.setStanzaCorrente(stanzaAdiacente);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu - 1);
			partita.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}
	@Override
	public void setParametro(String direzione) {
		this.direzione = direzione;
	}

}
