package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_REGALO = "Grazie del dono, te lo restituisco modificato";
	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if(this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DONO;
		}
		return MESSAGGIO_SCUSE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzoDaRegalare, Partita partita) {
		// TODO Auto-generated method stub
		Attrezzo attrezzoModificato = modificaAttrezzo(attrezzoDaRegalare);
		partita.getStanzaCorrente().addAttrezzo(attrezzoModificato);
		return MESSAGGIO_REGALO;
	}
	private static Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		Attrezzo attrezzoModificato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2);
		return attrezzoModificato;
	}

}
