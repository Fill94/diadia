package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String nomeAttrezzoIlluminante;
	public StanzaBuia(String nome, String nomeAttrezzoIlluminante) {
		super(nome);
		this.nomeAttrezzoIlluminante = nomeAttrezzoIlluminante;
	}
	@Override
	public String toString() {
		if(this.hasAttrezzo(nomeAttrezzoIlluminante))
			return super.toString();
		return "qui vi sta un buio pesto, servirebbe qualcosa per fare luce!";
	}
	
	
//	@Override
//	public String getDescrizione() {
//		if(this.hasAttrezzo(nomeAttrezzoIlluminante))
//			return super.getDescrizione();
//		return "quì c'è un buio pesto, servirebbe qualcosa per fare luce!";
//	}
}
