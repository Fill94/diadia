package it.uniroma3.diadia.comandi;

import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda extends AbstractComando{
	private final String NOME = "Guarda";
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		partita.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getConsole().mostraMessaggio("---stato della partita---");
		partita.getConsole().mostraMessaggio("CFU rimanenti : " + partita.getGiocatore().getCfu());
		StringBuilder sb = new StringBuilder("Attrezzi contenuti nell'inventario : ");
		Set<Attrezzo> insiemeAttrezziPerNome = partita.getGiocatore().getInventario().getContenutoOrdinatoPerNome();
		if(insiemeAttrezziPerNome.size() > 0) {
			sb.append("{");
			for(Attrezzo a : insiemeAttrezziPerNome) {
				sb.append(a.toString()+", ");
			}
			sb.append("}");
		}
		else {
			sb.append("nessun attrezzo");
		}
		partita.getConsole().mostraMessaggio(sb.toString());
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}
}
