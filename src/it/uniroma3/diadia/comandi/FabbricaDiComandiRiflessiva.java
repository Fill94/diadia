package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	@SuppressWarnings("deprecation")
	@Override
	public AbstractComando costruisciComando(String istruzione){
		// TODO Auto-generated method stub
		Scanner scannerDiPraole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		if(scannerDiPraole.hasNext())
			nomeComando = scannerDiPraole.next();
		if(scannerDiPraole.hasNext())
			parametro = scannerDiPraole.next();
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		try {
			comando = (AbstractComando) Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
		}finally {
			scannerDiPraole.close();
		}
		return comando;
	}

}
