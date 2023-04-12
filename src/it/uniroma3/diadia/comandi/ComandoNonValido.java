package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private final String NOME = "NonValido";
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		partita.getConsole().mostraMessaggio("comando inesistente!");
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
