package com.dma.rentalcars.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dma.rentalcars.rest.Supplier;

public class SupplierTest {

	@Test
	public void testSupplier() {
		Supplier s = new Supplier("Europcar", "9.1");
		assertEquals("Europcar", s.getSupplier());
		assertEquals(new Double(9.1), s.getRating());
	}

	@Test
	public void testToString() {
		Supplier s = new Supplier("Europcar", "9.1");
		assertEquals("Europcar - 9.1", s.toString());
	}

}
