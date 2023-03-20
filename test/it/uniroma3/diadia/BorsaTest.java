package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	public void testAddAttrezzoTroppoPeso() {
		this.borsaTest.addAttrezzo(new Attrezzo("attrezzoPesante", 10));
		assertFalse(this.borsaTest.addAttrezzo(attrezzoTest));
		assertEquals(10 , this.borsaTest.getPeso());
	}
	@Test
	public void testAddAttrezzoBorsaPiena() {
		for(int i = 0 ; i < 10 ; i++)
			this.borsaTest.addAttrezzo(new Attrezzo("attrezzo"+i, 0));
		assertFalse(this.borsaTest.addAttrezzo(attrezzoTest));
	}
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

}
