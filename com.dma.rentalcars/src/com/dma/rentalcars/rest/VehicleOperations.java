package com.dma.rentalcars.rest;
/*
 * @author Daniel Ma 
 * 
 * This is the original stand-alone Java console application, that can 
 * be executed and run with the org.json.simple jar and the vehicles.json
 * file, or through the REST API described in VehicleService. 
 * Contains the operations to read the JSON and carry out the
 * four tasks mentioned in the Technical Test script. 
 */
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class VehicleOperations {

	public static List<Vehicle> vehicles;

	public static void main(String[] args) throws Exception {
		readJSON();

		System.out.println("==> List of Cars in ascending price order:");
		System.out.println(displayVehiclesWithPrices());
		System.out.println("==> Car Specifications:");
		System.out.println(displayVehicleSpecification());
		System.out.println("==> Highest Rated Supplier per Car Type:");
		System.out.println(displayRating());
		System.out.println("==> Vehicle Scores:");
		System.out.println(displayScores());
	}

	@SuppressWarnings("unchecked")
	public static void readJSON() throws Exception {
		Object obj = null;
		try {
			JSONParser parser = new JSONParser();
			URL path = VehicleOperations.class.getResource("vehicles.json");
			obj = parser.parse(new FileReader(new File(path.getFile())));
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		JSONObject jsonObject = (JSONObject) obj;

		JSONArray arr = (JSONArray) ((JSONObject) jsonObject.get("Search")).get("VehicleList");

		vehicles = new ArrayList<Vehicle>();
		Iterator<JSONObject> iterator = arr.iterator();
		while (iterator.hasNext()) {
			JSONObject temp = iterator.next();
			Vehicle vehicle = new Vehicle((String)temp.get("sipp"), (String)temp.get("name"), 
					(Double)temp.get("price"), (String)temp.get("supplier"), temp.get("rating").toString());
			vehicles.add(vehicle);
		}
	}

	public static String displayVehiclesWithPrices() throws Exception {
		readJSON();
		Collections.sort(vehicles);
		int i = 1;
		StringBuilder sb = new StringBuilder();
		for (Vehicle vehicle : vehicles) {
			sb.append(i + ". " + vehicle.toString() +"\n");
			i++;
		}
		return sb.toString();
	}

	public static String displayVehicleSpecification() throws Exception {
		readJSON();
		int i = 1;
		StringBuilder sb = new StringBuilder();
		for (Vehicle vehicle : vehicles) {
			sb.append(i + ". " + vehicle.getName() + " - " + vehicle.getSipp().toString()+"\n");
			i++;
		}
		return sb.toString();
	}

	public static String displayRating() throws Exception {
		readJSON();
		// iterate through cars and find highest rated supplier for each car type
		HashMap<String, Vehicle> hashmap = new HashMap<String, Vehicle>();
		for (Vehicle vehicle : vehicles) {
			String carType = vehicle.getSipp().getCarType();
			Supplier supplier = vehicle.getSupplier();

			if (hashmap.get(carType) == null || supplier.compareTo(hashmap.get(carType).getSupplier()) > 0) 
				hashmap.put(carType, vehicle);
		}

		List<String> strings = new ArrayList<String>();

		// sort in order of descending ratings
		Set<String> set = hashmap.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			strings.add(hashmap.get(str).getSupplier().getRating() + 
					hashmap.get(str).getName() + " - " + str + " - " + hashmap.get(str).getSupplier());
		}
		Collections.sort(strings);
		Collections.reverse(strings);
		
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (String s : strings) {
			sb.append(i +". " + s.substring(3)+"\n");
			i++;
		}
		return sb.toString();
	}

	public static String displayScores() throws Exception {
		readJSON();
		for (Vehicle vehicle : vehicles) {
			int vehicleScore = 0;
			SIPP sipp = vehicle.getSipp();
			if (sipp.getTransmission().equals("Manual"))
				vehicleScore ++;
			else if (sipp.getTransmission().equals("Automatic"))
				vehicleScore += 5;
			else
				throw new Exception("Error: New transmission value present?");
			if (sipp.getFuelAirCon().equals("Petrol - AC"))
				vehicleScore += 2;
			vehicle.setVehicleScore(vehicleScore);
			double sum = vehicleScore + vehicle.getSupplier().getRating();
			vehicle.setTotalScore(sum);

		}

		Collections.sort(vehicles, new Comparator<Vehicle>() {
			public int compare(Vehicle one, Vehicle two) {
				return (one.getTotalScore()).compareTo(two.getTotalScore());
			}
		});
		Collections.reverse(vehicles);

		int i = 1;
		StringBuilder sb = new StringBuilder();
		for (Vehicle vehicle : vehicles) {
			sb.append(i + ". " + vehicle.getName() + " - " + vehicle.getVehicleScore() 
			+ " - " + vehicle.getSupplier().getRating() + " - " + vehicle.getTotalScore()+"\n");
			i++;
		}
		return sb.toString();
	}

}
