package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {
	private static final int DIMENSIONE_ARRAY = 50;
	//private String[] comandi;
	private List<String> comandi;
	//private String[] messaggi;
	private Map<String, List<String>> comandi2Messaggi;
	private int numeroComandi;
	private int numeroMessaggi;
	private int indiceComandi;
	private int indiceMessaggi;
	private String ultimoComandoLetto;
	
	public IOSimulator() {
//		this.comandi = new String[DIMENSIONE_ARRAY];
//		this.messaggi = new String[DIMENSIONE_ARRAY];
		this.comandi = new ArrayList<>();
		this.comandi2Messaggi = new HashMap<>();
	}
	@Override
	public void mostraMessaggio(String msg) {
		List<String> tmp = null;
		if(comandi2Messaggi.containsKey(this.ultimoComandoLetto)) {
			tmp = comandi2Messaggi.get(this.ultimoComandoLetto);
		}
		else {
			tmp = new ArrayList<>();
		}
		tmp.add(msg);
		comandi2Messaggi.put(this.ultimoComandoLetto, tmp);
		//System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		String comandoCorrente = this.comandi.get(indiceComandi);
		indiceComandi++;
		this.ultimoComandoLetto = comandoCorrente;
		return comandoCorrente;
	}
	
	public void addComando(String comando) {
		this.comandi.add(comando);
	}
	public List<String> getMessaggi(String comando) {
		// TODO Auto-generated method stub
		List<String> messaggi = this.comandi2Messaggi.get(comando);
		return messaggi;
	}
}
