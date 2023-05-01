package it.uniroma3.diadia.giocatore;
import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.*;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	//private Attrezzo[] attrezzi;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		//this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.attrezzi = new HashMap<>();
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		/*if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;*/
		if(this.attrezzi.containsKey(attrezzo.getNome()))
			return false; //esiste già un attrezzo con il nome uguale a quello passato come parametro
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.attrezzi.get(nomeAttrezzo);
		/*for(Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo.getNome().equals(nomeAttrezzo))
				a = attrezzo;
		}------VERSIONE 1
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;*/
		
		return a ;
	}
	public int getPeso() {
		int peso = 0;
		/*for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;*/
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			peso += attrezzo.getPeso();
		}
		return peso;
	}
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();//this.numeroAttrezzi == 0;
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		/*if(this.hasAttrezzo(nomeAttrezzo)) {
			a = this.getAttrezzo(nomeAttrezzo);
			boolean trovato = false;
			for(int i = 0 ; i < numeroAttrezzi ; i++) {
				if(this.attrezzi[i] == a) 
					trovato = true;
				if(trovato) {
					if(i < numeroAttrezzi-1)
						this.attrezzi[i] = this.attrezzi[i+1];
				}
			}
			this.attrezzi[attrezzi.length -1] = null;
			this.numeroAttrezzi--;
		}
		return a;*/
		if(this.hasAttrezzo(nomeAttrezzo)) {
			a = this.attrezzi.remove(nomeAttrezzo);
		}
		return a;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			/*for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");*/
			for(Attrezzo attrezzo : this.attrezzi.values())
				s.append(attrezzo.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
