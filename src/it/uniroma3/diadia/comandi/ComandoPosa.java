package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private final String NOME = "Posa";
	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		if(attrezzo == null)
			partita.getConsole().mostraMessaggio("cosa vuoi posare?");
		Attrezzo attrezzoPosato = partita.getGiocatore().getInventario().getAttrezzo(attrezzo);
		if(attrezzoPosato == null) {
			partita.getConsole().mostraMessaggio("attrezzo inesistente");
			return;
		}
		if(partita.getStanzaCorrente().addAttrezzo(attrezzoPosato))
			partita.getGiocatore().getInventario().removeAttrezzo(attrezzoPosato.getNome());
		else {
			partita.getConsole().mostraMessaggio("non puoi posare l'attrezzo nella stanza, deve essercene uno con nome uguale");
		}
		partita.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		this.attrezzo = parametro;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return this.attrezzo;
	}

}
