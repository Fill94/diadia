package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	private static final String[] elencoComandi = {"aiuto" , "fine" , "prendi" , "posa" , "guarda" , "vai", "interagisci", "saluta", "regala"};
	private final String NOME = "Aiuto";
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		for(String s : elencoComandi) {
			partita.getConsole().mostraMessaggio(s);
		}
	}
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.NOME;
	}
}
