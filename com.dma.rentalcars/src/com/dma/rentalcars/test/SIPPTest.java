package com.dma.rentalcars.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dma.rentalcars.rest.SIPP;

public class SIPPTest {

	@Test
	public void testSIPP() {
		SIPP sipp = new SIPP("CXMR");
		assertEquals("Compact", sipp.getCarType());
		assertEquals("NULL", sipp.getDoors());
		assertEquals("Manual", sipp.getTransmission());
		assertEquals("Petrol - AC", sipp.getFuelAirCon());
		assertEquals("CXMR", sipp.getSIPP());
	}

	@Test
	public void testToString() {
		SIPP sipp = new SIPP("CXMR");
		assertEquals("CXMR - Compact - NULL - Manual - Petrol - AC", sipp.toString());
	}

}
