package RentalCarsRESTImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

import VehicleServices.VehicleOperations;

@Path("/rc")
public class RentalCarsRESTImpl {
	
	@GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello Jersey";
	}
	
	@PUT
	@Path("/load")
	@Produces(MediaType.TEXT_PLAIN)
	public void loadJSON(JSONObject json) {
		
	}
	
	@GET
	@Path("/cars")
	@Produces(MediaType.TEXT_PLAIN)
	public String showCars() {
		return VehicleOperations.displayVehiclesWithPrices(vehicles);
	}
}
