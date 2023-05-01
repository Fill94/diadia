package it.uniroma3.diadia.comandi;

import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda implements Comando{
	private final String NOME = "Guarda";
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		partita.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getConsole().mostraMessaggio("---stato della partita---");
		partita.getConsole().mostraMessaggio("CFU rimanenti : " + partita.getGiocatore().getCfu());
		StringBuilder sb = new StringBuilder("Attrezzi contenuti nell'inventario : {");
		Set<Attrezzo> insiemeAttrezziPerNome = partita.getGiocatore().getInventario().getContenutoOrdinatoPerNome();
		for(Attrezzo a : insiemeAttrezziPerNome) {
			sb.append(a.toString()+", ");
		}
		sb.append("}");
		partita.getConsole().mostraMessaggio(sb.toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
