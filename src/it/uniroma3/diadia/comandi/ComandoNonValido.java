package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	private final String NOME = "NonValido";
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		partita.getConsole().mostraMessaggio("Comando inesistente!");
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}
}
