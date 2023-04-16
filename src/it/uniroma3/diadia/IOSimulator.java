package it.uniroma3.diadia;

public class IOSimulator implements IO {
	private static final int DIMENSIONE_ARRAY = 50;
	private String[] comandi;
	private String[] messaggi;
	private int numeroComandi;
	private int numeroMessaggi;
	private int indiceComandi;
	private int indiceMessaggi;
	
	public IOSimulator() {
		this.comandi = new String[DIMENSIONE_ARRAY];
		this.messaggi = new String[DIMENSIONE_ARRAY];
	}
	@Override
	public void mostraMessaggio(String msg) {
		this.messaggi[numeroMessaggi] = msg;
		numeroMessaggi++;
		//System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		String comandoCorrente = this.comandi[indiceComandi];
		indiceComandi++;
		return comandoCorrente;
	}
	
	public void addComando(String comando) {
		this.comandi[numeroComandi] = comando;
		numeroComandi++;
	}
	public String getLastMessaggio() {
		// TODO Auto-generated method stub
		return this.messaggi[numeroMessaggi-1];
	}
}
