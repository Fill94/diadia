package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.*;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
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
		return a ;
	}
	public int getPeso() {
		int peso = 0;
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
		if(this.hasAttrezzo(nomeAttrezzo)) {
			a = this.attrezzi.remove(nomeAttrezzo);
		}
		return a;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo attrezzo : this.attrezzi.values())
				s.append(attrezzo.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> listaAttrezzi = new ArrayList<>(this.attrezzi.values());
		Collections.sort(listaAttrezzi, new ComparatoreAttrezziPerPeso());
		return listaAttrezzi;
	}
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> insiemeOrdinatoPerNome = new TreeSet<>(this.attrezzi.values());
		return insiemeOrdinatoPerNome;
	}
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> peso2Attrezzi = new HashMap<>();
		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		Set<Attrezzo> tmp = null;
		for(Attrezzo attrezzo : attrezzi) {
			if(peso2Attrezzi.containsKey(attrezzo.getPeso())) {
				tmp = peso2Attrezzi.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				tmp = new HashSet<>();
				tmp.add(attrezzo);
			}
			peso2Attrezzi.put(attrezzo.getPeso(), tmp);
		}
		return peso2Attrezzi;
	}
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> setAttrezzi = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		setAttrezzi.addAll(this.attrezzi.values());
		return setAttrezzi;
	}
}
