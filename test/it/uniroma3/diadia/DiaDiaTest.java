package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiaDiaTest {
	private DiaDia diaDiaTest;
	private IOSimulator IOtest;
	@Before
	public void setUp() throws Exception {
		this.IOtest = new IOSimulator();
		this.diaDiaTest = new DiaDia(IOtest);
	}
	private IO impostaIO(String ...comandi ) {
		for(String s : comandi)
			IOtest.addComando(s);
		return this.IOtest;
	}
	
	@Test
	public void testParitaVinta() {
		this.impostaIO("vai est", "vai sud", "prendi chiave", "vai nord", "vai ovest", "vai nord", "posa chiave", "vai nord");
		diaDiaTest.gioca();
		assertTrue(this.IOtest.getLastMessaggio().contains("Complimenti, hai vinto!"));
	}
	@Test
	public void testParitaPersa() {
		String[] comandi = new String[21];
		for(int i = 0 ; i < 21; i++) {
			if(i%2 == 0)
				comandi[i]="vai sud";
			else
				comandi[i]="vai nord";
		}
		this.impostaIO(comandi);
		diaDiaTest.gioca();
		assertTrue(this.IOtest.getLastMessaggio().contains("CFU esauriti"));
	}
	

}
