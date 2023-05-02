package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.List;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsaTest;
	private Attrezzo attrezzoTest;

	@Before
	public void setUp() {
		this.borsaTest = new Borsa();
		this.attrezzoTest = new Attrezzo("attrezzoTest", 1);
	}
	@Test
	public void testAddAttrezzoBorsaVuota() {
		assertTrue(this.borsaTest.addAttrezzo(attrezzoTest));
		assertEquals(1, this.borsaTest.getPeso());
	}
	@Test
	public void testAddAttrezzoBorsaNonVuota() {
		assertTrue(this.borsaTest.addAttrezzo(attrezzoTest));
		assertTrue(this.borsaTest.addAttrezzo(new Attrezzo("attrezzoTest2", 2)));
		assertEquals(3, this.borsaTest.getPeso());
	}
	@Test
	public void testAddAttrezzoStessoNome() {
		assertTrue(this.borsaTest.addAttrezzo(attrezzoTest));
		assertFalse(this.borsaTest.addAttrezzo(new Attrezzo("attrezzoTest", 1)));
	}
	@Test 
	public void testAddAttrezzoTroppoPeso() {
		this.borsaTest.addAttrezzo(new Attrezzo("attrezzoPesante", 10));
		assertFalse(this.borsaTest.addAttrezzo(attrezzoTest));
		assertEquals(10 , this.borsaTest.getPeso());
	}
	/*@Test
	public void testAddAttrezzoBorsaPiena() {
		for(int i = 0 ; i < 10 ; i++)
			this.borsaTest.addAttrezzo(new Attrezzo("attrezzo"+i, 0));
		assertFalse(this.borsaTest.addAttrezzo(attrezzoTest));
	}*/
	@Test
	public void testGetAttrezzo() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertSame(this.attrezzoTest, this.borsaTest.getAttrezzo("attrezzoTest"));
	}
	@Test
	public void testGetAttrezzoInesistente() {
		assertNull(this.borsaTest.getAttrezzo("attrezzoTest"));
	}

	@Test
	public void testIsEmptyTrue() {
		assertTrue(this.borsaTest.isEmpty());
	}
	@Test
	public void testIsEmptyFalse() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertFalse(this.borsaTest.isEmpty());
	}
	@Test
	public void testHasAttrezzoTrue() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertTrue(this.borsaTest.hasAttrezzo("attrezzoTest"));
	}

	@Test
	public void testRemoveAttrezzoUnAttrezzo() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertEquals(1, this.borsaTest.getPeso());
		assertSame(attrezzoTest, this.borsaTest.removeAttrezzo("attrezzoTest"));
		assertEquals(0, this.borsaTest.getPeso());
		assertTrue(this.borsaTest.isEmpty());
	}
	@Test
	public void testRemoveAttrezzoDueAttrezziNellaBorsa() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		this.borsaTest.addAttrezzo(new Attrezzo("attrezzoTest2", 2));
		assertTrue(this.borsaTest.hasAttrezzo("attrezzoTest"));
		assertSame(attrezzoTest, this.borsaTest.removeAttrezzo("attrezzoTest"));
		assertEquals(2, this.borsaTest.getPeso());
		assertFalse(this.borsaTest.hasAttrezzo("attrezzoTest"));
	}
	@Test 
	public void testRemoveDueAttrezziDueAttrezziNellaBorsa() {
		Attrezzo attrezzoTest2 = new Attrezzo("attrezzoTest2", 2);
		this.borsaTest.addAttrezzo(attrezzoTest);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		assertFalse(this.borsaTest.isEmpty());
		assertSame(attrezzoTest2, this.borsaTest.removeAttrezzo("attrezzoTest2"));
		assertEquals(1, this.borsaTest.getPeso());
		assertSame(attrezzoTest, this.borsaTest.removeAttrezzo("attrezzoTest"));
		assertEquals(0, this.borsaTest.getPeso());
		assertTrue(this.borsaTest.isEmpty());
	}
	@Test
	public void testgetContenutoOrdinatoPerPesoBorsaVuota() {
		assertTrue(this.borsaTest.getContenutoOrdinatoPerPeso().size() == 0);
	}
	@Test
	public void testgetContenutoOrdinatoPerPesoDueAttrezziPesoDiverso() {
		Attrezzo attrezzoPesoMaggiore = new Attrezzo("attrezzo peso 2", 2);
		this.borsaTest.addAttrezzo(attrezzoPesoMaggiore);
		this.borsaTest.addAttrezzo(attrezzoTest);
		List<Attrezzo> listaOrdinataPerPeso = this.borsaTest.getContenutoOrdinatoPerPeso();
		assertEquals(0, listaOrdinataPerPeso.indexOf(attrezzoTest));
		assertEquals(1, listaOrdinataPerPeso.indexOf(attrezzoPesoMaggiore));
	}
	@Test
	public void testgetContenutoOrdinatoPerPesoDueAttrezziPesoUguale() {
		Attrezzo attrezzoCheIncominciaPerA = new Attrezzo("a", 1);
		Attrezzo attrezzoCheIncominciaPerB = new Attrezzo("b", 1);
		this.borsaTest.addAttrezzo(attrezzoCheIncominciaPerB);
		this.borsaTest.addAttrezzo(attrezzoCheIncominciaPerA);
		List<Attrezzo> listaOrdinataPerPeso = this.borsaTest.getContenutoOrdinatoPerPeso();
		assertEquals(0, listaOrdinataPerPeso.indexOf(attrezzoCheIncominciaPerA));
		assertEquals(1, listaOrdinataPerPeso.indexOf(attrezzoCheIncominciaPerB));
	}
	@Test 
	public void testGetContenutoOrdinatoPerNomeDueAttrezziNomiDiversi() {
		Attrezzo attrezzoCheIncominciaPerA = new Attrezzo("a", 1);
		Attrezzo attrezzoCheIncominciaPerB = new Attrezzo("b", 1);
		this.borsaTest.addAttrezzo(attrezzoCheIncominciaPerB);
		this.borsaTest.addAttrezzo(attrezzoCheIncominciaPerA);
		SortedSet<Attrezzo> insiemeOrdinatoPerNome = this.borsaTest.getContenutoOrdinatoPerNome();
		assertEquals(attrezzoCheIncominciaPerA, insiemeOrdinatoPerNome.first());
		assertEquals(attrezzoCheIncominciaPerB, insiemeOrdinatoPerNome.last());
	}
	@Test
	public void testGetContenutoRaggruppatoPerPeso1Attrezzo() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertTrue(this.borsaTest.getContenutoRaggruppatoPerPeso().containsKey(attrezzoTest.getPeso()));
	}
	@Test
	public void testGetContenutoRaggruppatoPerPeso2Attrezzi() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		Attrezzo attrezzoTest2 = new Attrezzo("attrezzoTest2", 2);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		assertTrue(this.borsaTest.getContenutoRaggruppatoPerPeso().containsKey(attrezzoTest.getPeso()));
		assertEquals(1, this.borsaTest.getContenutoRaggruppatoPerPeso().get(attrezzoTest2.getPeso()).size());
		assertEquals(1, this.borsaTest.getContenutoRaggruppatoPerPeso().get(attrezzoTest.getPeso()).size());
	}
	@Test
	public void testSortedSetOrdinatoPerPesoDueAttrezziPesoDiverso() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		Attrezzo attrezzoTest2 = new Attrezzo("attrezzoTest2", 2);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertSame(attrezzoTest, this.borsaTest.getSortedSetOrdinatoPerPeso().first());
		assertSame(attrezzoTest2, this.borsaTest.getSortedSetOrdinatoPerPeso().last());
	}
	@Test
	public void testSortedSetOrdinatoPerPesoTreAttrezziPesoUgualeNomeDiverso() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		Attrezzo attrezzoTest2 = new Attrezzo("zappa", 1);
		Attrezzo attrezzoTest3 = new Attrezzo("zeppa", 1);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		this.borsaTest.addAttrezzo(attrezzoTest3);
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertSame(attrezzoTest, this.borsaTest.getSortedSetOrdinatoPerPeso().first());
		assertSame(attrezzoTest3, this.borsaTest.getSortedSetOrdinatoPerPeso().last());
		assertEquals(3, this.borsaTest.getSortedSetOrdinatoPerPeso().size());
	}
	@Test
	public void testSortedSetOrdinatoPerPesoDueAttrezziPesoUgualeNomeUguale() {
		this.borsaTest.addAttrezzo(attrezzoTest);
		Attrezzo attrezzoTest2 = this.attrezzoTest;
		this.borsaTest.addAttrezzo(attrezzoTest2);
		this.borsaTest.addAttrezzo(attrezzoTest);
		assertEquals(1, this.borsaTest.getSortedSetOrdinatoPerPeso().size());
	}
	
}
