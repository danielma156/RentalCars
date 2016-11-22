package VehicleServices;

import java.io.FileReader;
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
    
    System.out.println("==> List of Cars in ascending price order:");
    System.out.println(displayVehiclesWithPrices(vehicles));
    System.out.println("==> Car Specifications:");
    System.out.println(displayVehicleSpecification(vehicles));
    System.out.println("==> Highest Rated Supplier per Car Type:");
    System.out.println(displayRating(vehicles));
    System.out.println("==> Vehicle Scores:");
    System.out.println(displayScores(vehicles));
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
  
  public static String displayVehiclesWithPrices(List<Vehicle> vehicles) {
	  Collections.sort(vehicles);
	  int i = 1;
	  StringBuilder sb = new StringBuilder();
	  for (Vehicle vehicle : vehicles) {
		  sb.append(i + ". " + vehicle.toString() +"\n");
		  i++;
	  }
	  return sb.toString();
  }
  
  public static String displayVehicleSpecification(List<Vehicle> vehicles) {
	  int i = 1;
	  StringBuilder sb = new StringBuilder();
	  for (Vehicle vehicle : vehicles) {
		  sb.append(i + ". " + vehicle.getName() + " - " + vehicle.getSipp().toString()+"\n");
		  i++;
	  }
	  return sb.toString();
  }
  
  public static String displayRating(List<Vehicle> vehicles) {
	  // iterate through cars and find highest rated supplier for each car type
	  HashMap<String, Vehicle> hashmap = new HashMap<String, Vehicle>();
	  for (Vehicle vehicle : vehicles) {
		  String carType = vehicle.getSipp().getCarType();
		  Supplier supplier = vehicle.getSupplier();
		  
		  if (hashmap.get(carType) == null || supplier.compareTo(hashmap.get(carType).getSupplier()) > 0) 
		  	hashmap.put(carType, vehicle);
	  }
	  	
	  List<String> strings = new ArrayList<String>();
	  
	  // print results
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
	  for (String s : strings)
		  sb.append(s.substring(3)+"\n");
	  return sb.toString();
  }
  
  public static String displayScores(List<Vehicle> vehicles) throws Exception {
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
