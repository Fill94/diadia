package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	private final String NOME = "Vai";
	private Direzione direzione;

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
		try {
			String direzioneUpperCase = direzione.toUpperCase();
			this.direzione = Enum.valueOf(Direzione.class, direzioneUpperCase);	
		}
		catch (IllegalArgumentException e) {
			this.direzione = null;
		}
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}
	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return this.direzione.toString();
	}

}
