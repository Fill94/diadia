package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	private final String NOME = "Fine";
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		partita.setFinita();
		partita.getConsole().mostraMessaggio("Grazie di aver giocato!");;
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}
}
