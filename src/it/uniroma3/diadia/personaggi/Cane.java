package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_CIBO_PREFERITO ="grazie per avermi fatto questo regalo! ecco a te qualcosa di utile";
	private static final String MESSAGGIO_MORSO = "sono un cane molto cattivo, ti mordo!";
	private static final String NOME_CIBO_PREFERITO = "osso";
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() -1 );
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzoInRegalo, Partita partita) {
		if(attrezzoInRegalo.getNome().equals(NOME_CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("collare", 3));
			return MESSAGGIO_CIBO_PREFERITO;
		}
		return this.agisci(partita);
	}

}
