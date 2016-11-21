package RentalCarsRESTImpl;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
	public static void loadJSON(JsonObject json) {
		
	}
}
