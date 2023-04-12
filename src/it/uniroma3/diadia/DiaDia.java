package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOconsole console;
	public DiaDia(IOconsole console) {
		this.partita = new Partita(console);
		this.console = console;
	}

	public void gioca() {
		String istruzione; 
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	//anzichè far tornare true o false cedo la responsabilità di decidere se la partita è finita al metodo Partita.isFInita
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome() == null) {
			this.console.mostraMessaggio("inserire un comando");
			return this.partita.isFinita();
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
		} 
		if(this.partita.getGiocatore().getCfu()== 0) {
			this.console.mostraMessaggio("Cfu esauriti, GAME OVER!");
		}
		return this.partita.isFinita();
	}   

	private void posa(String NomeAttrezzo) {
		if(NomeAttrezzo == null)
			this.console.mostraMessaggio("cosa vuoi posare?");
		Attrezzo attrezzoPosato = this.partita.getGiocatore().getInventario().removeAttrezzo(NomeAttrezzo);
		if(attrezzoPosato == null)
			this.console.mostraMessaggio("attrezzo inesistente");
		else {
			this.partita.getStanzaCorrente().addAttrezzo(attrezzoPosato);
		}
		this.console.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
	}

	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			this.console.mostraMessaggio("cosa vuoi prendere?");
		Attrezzo attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(attrezzoDaPrendere == null)
			this.console.mostraMessaggio("attrezzo inesistente");
		else {
			this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			this.partita.getGiocatore().getInventario().addAttrezzo(attrezzoDaPrendere);
		}
		this.console.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.console.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu()-1;
			this.partita.getGiocatore().setCfu(cfu);
		}
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.partita.setFinita();
		this.console.mostraMessaggio("Grazie di aver giocato!");
	}

	public static void main(String[] argc) {
		IOconsole console = new IOconsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}