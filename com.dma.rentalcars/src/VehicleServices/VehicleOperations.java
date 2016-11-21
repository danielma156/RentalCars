package VehicleServices;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class VehicleOperations {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    Object obj = null;
    try {
      JSONParser parser = new JSONParser();
      obj = parser.parse(new FileReader("vehicles.json"));
    }
    catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
    // read the JSON data
    List<Vehicle> vehicles = readJSON(obj);
    
    System.out.println(" List of Cars in ascending price order:");
    displayVehiclesWithPrices(vehicles);
    System.out.println("\n Car Specifications:");
    displayVehicleSpecification(vehicles);
    System.out.println("\n Highest Rated Supplier per Car Type:");
    displayRating(vehicles);
    System.out.println("\n Vehicle Scores:");
    displayScores(vehicles);
  }
  
  public static List<Vehicle> readJSON(Object obj) {
	  JSONObject jsonObject = (JSONObject) obj;

	    JSONArray arr = (JSONArray) ((JSONObject) jsonObject.get("Search")).get("VehicleList");

	    List<Vehicle> vehicles = new ArrayList<Vehicle>();
	    Iterator<JSONObject> iterator = arr.iterator();
	    while (iterator.hasNext()) {
	      // System.out.println(iterator.next().get("sipp"));
	      JSONObject temp = iterator.next();
	      Vehicle vehicle = new Vehicle((String)temp.get("sipp"), (String)temp.get("name"),
	        (Double)temp.get("price"), (String)temp.get("supplier"), temp.get("rating").toString());
	      vehicles.add(vehicle);
	    }
	    return vehicles;
  }
  
  public static void displayVehiclesWithPrices(List<Vehicle> vehicles) {
	  Collections.sort(vehicles);
	  int i = 1;
	  for (Vehicle vehicle : vehicles) {
		  System.out.println(i + ". " + vehicle.toString());
		  i++;
	  }
  }
  
  public static void displayVehicleSpecification(List<Vehicle> vehicles) {
	  int i = 1;
	  for (Vehicle vehicle : vehicles) {
		  System.out.println(i + ". " + vehicle.getName() + " - " + vehicle.getSipp().toString());
		  i++;
	  }
  }
  
  public static void displayRating(List<Vehicle> vehicles) {
	  // iterate through cars and find highest rated supplier for each car type
	  Comparator<Vehicle> c = new Comparator<Vehicle>() {
		  public int compare(Vehicle one, Vehicle two) {
			  return (one.getSupplier().getRating()).compareTo(two.getSupplier().getRating());
		  }
	  };
	  TreeMap<String, Vehicle> hashmap = new TreeMap<String, Vehicle>();
	  for (Vehicle vehicle : vehicles) {
		  String carType = vehicle.getSipp().getCarType();
		  Supplier supplier = vehicle.getSupplier();
		  
		  if (hashmap.get(carType) == null || supplier.compareTo(hashmap.get(carType).getSupplier()) > 0) 
		  	hashmap.put(carType, vehicle);	  
	  }
	  
	  TreeMap<Vehicle, String> tm = new TreeMap<Vehicle, String>(new Comparator<Vehicle>() {
		public int compare(Vehicle one, Vehicle two) {
			  return (one.getSupplier().getRating()).compareTo(two.getSupplier().getRating());
		  }
	  });
	  
	  // print results
	  Set<String> set = hashmap.keySet();
	  Iterator<String> iterator = set.iterator();
	  while (iterator.hasNext()) {
		  String str = iterator.next();
		  tm.put(hashmap.get(str), hashmap.get(str).getName() + " - " 
				  + str + " - " + hashmap.get(str).getSupplier());
//		  System.out.println(i + ". " + hashmap.get(str).getName() + " - " 
//				  + str + " - " + hashmap.get(str).getSupplier());
	  }
	  
	  int i = 1;
	  Set<Vehicle> set2 = tm.descendingKeySet();
	  Iterator<Vehicle> iterator2 = set2.iterator();
	  while (iterator2.hasNext()) {
		  System.out.println(i + ". " + tm.get(iterator2.next()));
		  i++;
	  }
  }
  
  public static void displayScores(List<Vehicle> vehicles) throws Exception {
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
	  for (Vehicle vehicle : vehicles) {
		  System.out.println(i + ". " + vehicle.getName() + " - " + vehicle.getVehicleScore() 
				  + " - " + vehicle.getSupplier().getRating() + " - " + vehicle.getTotalScore());
		  i++;
	  }
  }

}
