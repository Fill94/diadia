package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	private String attrezzoSbloccante;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
		super(nome);
		this.attrezzoSbloccante = attrezzoSbloccante;
		this.direzioneBloccata = direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(direzioneBloccata) && this.hasAttrezzo(attrezzoSbloccante)) {
				return super.getStanzaAdiacente(direzione);
		}
		if(!direzione.equals(direzioneBloccata))
			return super.getStanzaAdiacente(direzione);
		return this;
	}
	@Override
	public String toString() {
		if(this.hasAttrezzo(attrezzoSbloccante))
			return "Hai trovato quello che serviva per sbloccare la direzione!\n" + super.toString();
		StringBuilder risultato = new StringBuilder();
		risultato.append("in questa stanza vi sta una porta bloccata, servirebbe qualcosa per aprirla se vuoi andare dall'altra parte!\n");
		risultato.append("\nDESCRIZIONE STANZA :");
		risultato.append("\nNome: "+this.getNome());
		risultato.append("\nUscite: ");
		for (String direzione : this.getDirezioni())
			if (direzione!=null && direzione != this.direzioneBloccata)
				risultato.append(" " + direzione);
		risultato.append("\nUscita bloccate: ");
		risultato.append(this.direzioneBloccata);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.getAttrezzi()) {
			if(attrezzo != null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}
//	@Override
//	public String getDescrizione() {
//		// TODO Auto-generated method stub
//		if(this.hasAttrezzo(attrezzoSbloccante))
//			return "Hai trovato quello che serviva per sbloccare la direzione!\n" + super.getDescrizione();
//		else
//			return this.toString();
//	}

}
