package com.dma.rentalcars.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dma.rentalcars.rest.SIPP;
import com.dma.rentalcars.rest.Supplier;
import com.dma.rentalcars.rest.Vehicle;

public class VehicleTest {

	@Test
	public void testVehicle() {
		Vehicle v = new Vehicle("CXMR", "VW Golf", 200.10, "Europcar", "9.1");
		assertEquals("VW Golf", v.getName());
		assertEquals(new Double(200.10), v.getPrice());
		
		Supplier s = new Supplier("Europcar", "9.1");
		assertEquals(s.getSupplier(), v.getSupplier().getSupplier());
		assertEquals(s.getRating(), v.getSupplier().getRating());
		
		SIPP sipp = new SIPP("CXMR");
		assertEquals(sipp.getCarType(), v.getSipp().getCarType());
		assertEquals(sipp.getDoors(), v.getSipp().getDoors());
		assertEquals(sipp.getTransmission(), v.getSipp().getTransmission());
		assertEquals(sipp.getFuelAirCon(), v.getSipp().getFuelAirCon());
		assertEquals(sipp.getSIPP(), v.getSipp().getSIPP());
	}

	@Test
	public void testToString() {
		Vehicle v = new Vehicle("CXMR", "VW Golf", 200.10, "Europcar", "9.1");
		assertEquals("VW Golf - 200.10", v.toString());
	}

	@Test
	public void testCompareTo() {
		Vehicle v = new Vehicle("CXMR", "VW Golf", 200.10, "Europcar", "9.1");
		Vehicle v2 = new Vehicle("CXMR", "Mercedes Benz", 10000, "Europcar", "9.1");
		assertEquals(-1, v.compareTo(v2));
	}

}
