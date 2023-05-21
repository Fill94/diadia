package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private boolean haSalutato;
	private String presentazione;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+".");
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}

	public String getNome() {
		return this.nome;
	}
	public boolean getHaSalutato() {
		return this.haSalutato;
	}

	@Override
	public String toString() {
		return this.getNome();
	}

	public abstract String agisci(Partita partita);

	public abstract String riceviRegalo(Attrezzo attrezzoDaRegalare, Partita partita);
}
