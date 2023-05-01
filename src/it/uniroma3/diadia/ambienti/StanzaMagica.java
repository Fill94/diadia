package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {

	private static final int SOGLIA_MAGICA_DEFAULT = 10;
	private int sogliaMagica;
	private int contatoreAttrezziPosati;
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.sogliaMagica = soglia;
		this.contatoreAttrezziPosati = 0;
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		int pesoRaddoppiato = attrezzo.getPeso()*2;
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoRaddoppiato);
		return attrezzo;
	}
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		contatoreAttrezziPosati++;
		if(contatoreAttrezziPosati > sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

	public boolean isMagica() {
		// TODO Auto-generated method stub
		return true;
	}
}
