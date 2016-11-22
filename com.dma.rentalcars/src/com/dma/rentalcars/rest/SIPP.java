package com.dma.rentalcars.rest;
/*
 * @author Daniel Ma
 * 
 * Object representation of the SIPP for a Vehicle.
 * Each Vehicle has an associated SIPP.
 */
public class SIPP {
	
	private String sipp;
	private String carType;
	private String doors;
	private String transmission;
	private String fuelAirCon;
	
	public SIPP(String sipp) {
		this.setSIPP(sipp);
		
		char[] arr = sipp.toCharArray();
		this.setCarType(SIPPHelper.returnCarType(arr[0]));
		this.setDoors(SIPPHelper.returnDoors(arr[1]));
		this.setTransmission(SIPPHelper.returnTransmission(arr[2]));
		this.setFuelAirCon(SIPPHelper.returnFuelAirCon(arr[3]));
	}
	
	public String getSIPP() {
		return sipp;
	}

	public void setSIPP(String sipp) {
		this.sipp = sipp;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getDoors() {
		return doors;
	}

	public void setDoors(String doors) {
		this.doors = doors;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getFuelAirCon() {
		return fuelAirCon;
	}

	public void setFuelAirCon(String fuelAirCon) {
		this.fuelAirCon = fuelAirCon;
	}
	
	@Override
	public String toString() {
		return this.getSIPP() + " - " + this.getCarType() + " - " + this.getDoors() + " - "
				+ this.getTransmission() + " - " + this.getFuelAirCon();
	}
}
