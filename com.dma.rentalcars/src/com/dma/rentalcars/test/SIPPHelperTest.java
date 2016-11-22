package com.dma.rentalcars.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dma.rentalcars.rest.SIPPHelper;

public class SIPPHelperTest {

	@Test
	public void testReturnCarType() {
		assertEquals(SIPPHelper.returnCarType('M'), "Mini");
		assertEquals(SIPPHelper.returnCarType('E'), "Economy");
		assertEquals(SIPPHelper.returnCarType('C'), "Compact");
		assertEquals(SIPPHelper.returnCarType('I'), "Intermediate");
		assertEquals(SIPPHelper.returnCarType('S'), "Standard");
		assertEquals(SIPPHelper.returnCarType('F'), "Full size");
		assertEquals(SIPPHelper.returnCarType('P'), "Premium");
		assertEquals(SIPPHelper.returnCarType('L'), "Luxury");
		assertEquals(SIPPHelper.returnCarType('X'), "Special");
		assertEquals(SIPPHelper.returnCarType('Z'), "NULL");
	}

	@Test
	public void testReturnDoors() {
		assertEquals(SIPPHelper.returnDoors('B'), "2 doors");
		assertEquals(SIPPHelper.returnDoors('C'), "4 doors");
		assertEquals(SIPPHelper.returnDoors('D'), "5 doors");
		assertEquals(SIPPHelper.returnDoors('W'), "Estate");
		assertEquals(SIPPHelper.returnDoors('T'), "Convertible");
		assertEquals(SIPPHelper.returnDoors('F'), "SUV");
		assertEquals(SIPPHelper.returnDoors('P'), "Pick up");
		assertEquals(SIPPHelper.returnDoors('V'), "Passenger Van");
		assertEquals(SIPPHelper.returnDoors('X'), "NULL");
	}

	@Test
	public void testReturnTransmission() {
		assertEquals(SIPPHelper.returnTransmission('M'), "Manual");
		assertEquals(SIPPHelper.returnTransmission('A'), "Automatic");
		assertEquals(SIPPHelper.returnTransmission('X'), "NULL");
	}
	
	@Test
	public void testReturnFuelAirCon() {
		assertEquals(SIPPHelper.returnFuelAirCon('N'), "Petrol - no AC");
		assertEquals(SIPPHelper.returnFuelAirCon('R'), "Petrol - AC");
		assertEquals(SIPPHelper.returnFuelAirCon('X'), "NULL");
	}

}
