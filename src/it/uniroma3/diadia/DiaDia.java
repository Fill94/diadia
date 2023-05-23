package it.uniroma3.diadia;
import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

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
	
	private Partita partita;
	private IO console;
	public DiaDia(IO console) {
		this.partita = new Partita(console);
		this.console = console;
	}
	public DiaDia(IO console, Labirinto labirinto) {
		this(console);
		this.partita = new Partita(console, labirinto);
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
//		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		AbstractComando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(partita);
		if(this.partita.getGiocatore().getCfu() == 0)
			console.mostraMessaggio("CFU esauriti, Sei morto!");
		if(this.partita.vinta())
			console.mostraMessaggio("Hai vinto!");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException{
		IO console = new IOconsole();
		//DiaDia gioco = new DiaDia(console);
		Labirinto labirinto = new Labirinto("labirinto5.txt");
		DiaDia gioco = new DiaDia(console, labirinto);
		gioco.gioca();
	}
}