package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {
	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		IO console = partita.getConsole();
		if(this.getParametro() == null) {
			console.mostraMessaggio("cosa vuoi regalare?");
		}
		if(partita.getStanzaCorrente().getPersonaggio() == null) {
			console.mostraMessaggio("nella stanza non vi sono personaggi a cui regalare nulla");
			return;
		}
		if(!partita.getGiocatore().getInventario().hasAttrezzo(this.getParametro())) {
			console.mostraMessaggio("l'attrezzo inserito non è presente nell'inventario");
			return;
		}
		/*
		 * dopo aver verificato che l'utente abbia inserito l'attrezzo da regalare, siano presenti personaggi nella stanza e che l'attrezzo da regalare sia presente nella borsa
		 * si dovrà riumuovere l'attrezzo dall'inventario del giocatore e invocare il metodo AbstractPersonaggio.riceviRegalo() per donarlo al personaggio presente nella stanza
		 */
		Attrezzo attrezzoDaRegalare = partita.getGiocatore().getInventario().getAttrezzo(this.getParametro());
		partita.getGiocatore().getInventario().removeAttrezzo(this.getParametro());
		String messaggioPersonaggio = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoDaRegalare, partita);
		console.mostraMessaggio(messaggioPersonaggio);
	}

	@Override
	public String getNome() {
		return null;
	}
	@Override
	public String getParametro() {
		return this.attrezzo;
	}
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}

}
