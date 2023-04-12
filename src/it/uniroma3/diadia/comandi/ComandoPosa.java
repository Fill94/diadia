package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements ComandoInterface {
	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		if(attrezzo == null)
			partita.getConsole().mostraMessaggio("cosa vuoi posare?");
		Attrezzo attrezzoPosato = partita.getGiocatore().getInventario().removeAttrezzo(attrezzo);
		if(attrezzoPosato == null)
			partita.getConsole().mostraMessaggio("attrezzo inesistente");
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzoPosato);
		}
		partita.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		this.attrezzo = parametro;
	}

}
