package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	@Override
	public Comando costruisciComando(String istruzione){
		// TODO Auto-generated method stub
		Scanner scannerDiPraole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if(scannerDiPraole.hasNext())
			nomeComando = scannerDiPraole.next();
		if(scannerDiPraole.hasNext())
			parametro = scannerDiPraole.next();
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		try {
			comando = (Comando) Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
		}
		return comando;
	}

}
