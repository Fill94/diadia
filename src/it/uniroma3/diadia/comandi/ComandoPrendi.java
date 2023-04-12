package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private final String NOME = "Prendi";
	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		if(attrezzo == null) {
			partita.getConsole().mostraMessaggio("cosa vuoi prendere?");
			return;
		}	
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(attrezzo);
		if(attrezzoDaPrendere == null) {
			partita.getConsole().mostraMessaggio("attrezzo inesistente");
			return;
		}
		else {
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			partita.getGiocatore().getInventario().addAttrezzo(attrezzoDaPrendere);
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
