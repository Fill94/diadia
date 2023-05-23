package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             
	/* prefisso di una riga di testo contenente i nomi delle stanze magiche */
	private static final String MAGICHE_MARKER = "Magiche:";
	private static final String BUIE_MARKER = "Buie:";
	private static final String CHIUESE_MARKER = "Chiuse:";
	private static final String MAGHI_MARKER = "Maghi:";
	private static final String CANI_MARKER = "Cani:";
	private static final String STREGHE_MARKER = "Streghe:";
	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeChiuse();
			this.leggiInizialeEvincente();
			this.leggiECollocaMaghi();
			this.leggiECollocaCani();
			this.leggiECollocaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(MAGICHE_MARKER);
		for(String specificaStanzaMagica : separaStringheAlleVirgole(specificheStanzeMagiche)) {
			try(Scanner scannerDiLinee = new Scanner(specificaStanzaMagica)){
				String nomeStanzaMagica = scannerDiLinee.next();
				StanzaMagica stanzaMagica = new StanzaMagica(nomeStanzaMagica);
				if(scannerDiLinee.hasNext()) {
					int sogliaStanzaMagica;
					try{
						sogliaStanzaMagica = Integer.parseInt(scannerDiLinee.next());
						stanzaMagica.setSoglia(sogliaStanzaMagica);
					}
					catch (NumberFormatException e) {
						check(false, "soglia magica "+nomeStanzaMagica+" non valida");
					}
				}
				this.nome2stanza.put(nomeStanzaMagica, stanzaMagica);
			}
		}
	}
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(BUIE_MARKER);
		for(String specificaStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			try(Scanner scannerDiLinee = new Scanner(specificaStanzaBuia)){
				String nomeAttrezzoIlluminante = null;
				String nomeStanzaBuia = null;
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome di una stanza buia"));
				nomeStanzaBuia = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome dell' attrezzo illuminante di una stanza buia"));
				nomeAttrezzoIlluminante = scannerDiLinee.next();
				StanzaBuia stanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoIlluminante);
				this.nome2stanza.put(nomeStanzaBuia, stanzaBuia);
			}
		}
	}
	private void leggiECreaStanzeChiuse() throws FormatoFileNonValidoException  {
		String specificheStanzeChiuse = this.leggiRigaCheCominciaPer(CHIUESE_MARKER);
		for(String specificaStanzaChiusa : separaStringheAlleVirgole(specificheStanzeChiuse)) {
			try(Scanner scannerDiLinee = new Scanner(specificaStanzaChiusa)){
				String nomeAttrezzoilluminante = null;
				String nomeStanzaChiusa = null;
				String direzioneBloccata = null;
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome di una stanza chiusa"));
				nomeStanzaChiusa = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome dell' attrezzo sbloccante di una stanza buia"));
				nomeAttrezzoilluminante = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("la direzione bloccata di una stanza chiusa"));
				direzioneBloccata = scannerDiLinee.next();
				try {
					Direzione direzione = Direzione.valueOf(direzioneBloccata);
					StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanzaChiusa, nomeAttrezzoilluminante, direzione);
					this.nome2stanza.put(nomeStanzaChiusa, stanzaBloccata);
				} catch (IllegalArgumentException e) {
					check(false, "Direzione bloccata: "+direzioneBloccata+" invalida per la stanza "+nomeStanzaChiusa);
				}
				
				
			}
		}
	}
	

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scanner.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}
	
	private void leggiECollocaMaghi() throws FormatoFileNonValidoException{
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String presentazioneMago = null;
			String nomaAttrezzoDono = null;
			String pesoAttrezzoDono = null;
			String nomeStanzaMago = null;
			try(Scanner scannerDiLinee = new Scanner(specificaMago)){
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome di un mago"));
				nomeMago = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("la presentazione di un mago"));
				presentazioneMago = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome del dono dono di un mago"));
				nomaAttrezzoDono = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il peso di un dono di un mago"));
				pesoAttrezzoDono = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("la stanza in cui collocare un mago"));
				nomeStanzaMago = scannerDiLinee.next();
			}
			this.collocaMago(nomeMago, presentazioneMago, nomaAttrezzoDono, pesoAttrezzoDono, nomeStanzaMago);
		}
		
	}
	
	private void collocaMago(String nomeMago, String presentazioneMago, String nomaAttrezzoDono, String pesoAttrezzoDono, String nomeStanzaMago) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzoDono);
			Attrezzo attrezzoDono = new Attrezzo(nomaAttrezzoDono, peso);
			Mago mago = new Mago(nomeMago, presentazioneMago, attrezzoDono);
			check(isStanzaValida(nomeStanzaMago), "stanza mago "+nomeMago+" inesistente");
			nome2stanza.get(nomeStanzaMago).setPersonaggio(mago);
		}
		catch (NumberFormatException e) {
			check(false, "peso attrezzo dono "+nomaAttrezzoDono+ " del mago "+nomeMago+" non valido ");
		}	
	}
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String presentazione = null;
			String nomeStanzaCane = null;
			try(Scanner scannerDiLinee = new Scanner(specificaCane)){
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome di un cane"));
				nomeCane = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("la presentazione di un cane"));
				presentazione = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome della stanza del cane"));
				nomeStanzaCane = scannerDiLinee.next();
			}
			this.collocaCane(nomeCane, presentazione, nomeStanzaCane);
		}
	}
	private void collocaCane(String nomeCane, String presentazione, String nomeStanzaCane) throws FormatoFileNonValidoException {
		Cane cane = new Cane(nomeCane, presentazione); 
		check(isStanzaValida(nomeStanzaCane), "Cane: "+nomeCane+"non collocabile: "+nomeStanzaCane+" inesistente");
		this.nome2stanza.get(nomeStanzaCane).setPersonaggio(cane);
	}
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = leggiRigaCheCominciaPer(STREGHE_MARKER);
		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String presentazioneStrega = null;
			String nomeStanzaStrega = null;
			try(Scanner scannerDiLinee = new Scanner(specificaStrega)){
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("il nome di una strega"));
				nomeStrega = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("la presentazione della strga:"+nomeStrega));
				presentazioneStrega = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("la stanza della strega: "+nomeStrega));
				nomeStanzaStrega = scannerDiLinee.next();
			}
			this.collocaStrega(nomeStrega, presentazioneStrega, nomeStanzaStrega);
		}
	}
	private void collocaStrega(String nomeStrega, String presentazioneStrega, String nomeStanzaStrega) throws FormatoFileNonValidoException {
		Strega strega = new Strega(nomeStrega, presentazioneStrega);
		check(isStanzaValida(nomeStanzaStrega), "la stanza della strega: "+nomeStrega+" inesistente");
		this.nome2stanza.get(nomeStanzaStrega).setPersonaggio(strega);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		boolean condizione = this.nome2stanza.containsKey(nomeStanza);
		return condizione;
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		List<String> listaSpecificheUscite = this.separaStringheAlleVirgole(specificheUscite);
		for(String s : listaSpecificheUscite) {
			try (Scanner scannerDiLinea = new Scanner(s)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					//scannerDiLinea.useDelimiter(",");
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		try {
			String direzioneUpperCase = dir.toUpperCase();
			Direzione direzione = Direzione.valueOf(direzioneUpperCase);
			partenzaDa.impostaStanzaAdiacente(direzione, arrivoA);
		} catch (IllegalArgumentException e) {
			check(false, "direzione: "+dir+"  non permessa per la stanza: "+stanzaDa);
		}
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
