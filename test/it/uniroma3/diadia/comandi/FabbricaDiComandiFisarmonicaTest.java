package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica factory;
	@Before
	public void setUp() throws Exception {
		this.factory = new FabbricaDiComandiFisarmonica();
	}
	@Test
	public void testCostruisciComandoNonValido() {
		
		AbstractComando comando = this.factory.costruisciComando("salta est");
		assertEquals("NonValido", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	public void testCostruisciComandoVai() {
		AbstractComando comando = this.factory.costruisciComando("vai sud");
		assertEquals("Vai", comando.getNome());
		assertEquals("SUD", comando.getParametro());
	}
	@Test
	public void testCostruisciComandoPrendi() {
		AbstractComando comando = this.factory.costruisciComando("prendi oggetto");
		assertEquals("Prendi", comando.getNome());
		assertEquals("oggetto", comando.getParametro());
	}
	@Test
	public void testCostruisciComandoPosa() {
		AbstractComando comando = this.factory.costruisciComando("posa oggetto");
		assertEquals("Posa", comando.getNome());
		assertEquals("oggetto", comando.getParametro());
	}
	@Test
	public void testCostruisciComandoGuarda() {
		AbstractComando comando = this.factory.costruisciComando("guarda");
		assertEquals("Guarda", comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test
	public void testCostruisciComandoFine() {
		AbstractComando comando = this.factory.costruisciComando("fine");
		assertEquals("Fine", comando.getNome());
		assertNull(comando.getParametro());
	}
}
