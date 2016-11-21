package VehicleServices;

public class SIPPHelper {
		
	public static String returnCarType(char c) {
		switch (c) {
		case 'M':
			return "Mini";
		case 'E':
			return "Economy";
		case 'C':
			return "Compact";
		case 'I':
			return "Intermediate";
		case 'S':
			return "Standard";
		case 'F':
			return "Full size";
		case 'P':
			return "Premium";
		case 'L': 
			return "Luxury";
		case 'X':
			return "Special";
		default:
			return "NULL";
		}
	}
	
	public static String returnDoors(char c) {
		switch (c) {
		case 'B':
			return "2 doors";
		case 'C':
			return "4 doors";
		case 'D':
			return "5 doors";
		case 'W':
			return "Estate";
		case 'T':
			return "Convertible";
		case 'F':
			return "SUV";
		case 'P':
			return "Pick up";
		case 'V':
			return "Passenger Van";
		default:
			return "NULL";
		}
	}
	
	public static String returnTransmission(char c) {
		switch (c) {
		case 'M':
			return "Manual";
		case 'A':
			return "Automatic";
		default:
			return "NULL";
		}
	}
	
	public static String returnFuelAirCon(char c) {
		switch (c) {
		case 'N':
			return "Petrol - no AC";
		case 'R':
			return "Petrol - AC";
		default:
			return "NULL";
		}
	}
	
	
	
}
