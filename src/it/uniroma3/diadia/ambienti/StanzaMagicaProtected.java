package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	private static final int SOGLIA_MAGICA_DEFAULT = 10;
	private int sogliaMagica;
	private int contatoreAttrezziPosati;
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome, int soglia) {
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
		if(contatoreAttrezziPosati >= sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}

}
