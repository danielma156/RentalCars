package com.dma.rentalcars.rest;
/*
 * @author Daniel Ma
 * 
 * Describes and outlines the URL paths to retrieve the relevant
 * output from the four tasks in the Technical Test script.
 */
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("rest")
@Path("rentalcars")
public class VehicleService extends Application {

	@GET
	@Path("cars")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCars() throws Exception {
		return VehicleOperations.displayVehiclesWithPrices();
	}

	@GET
	@Path("spec")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSpecification() throws Exception {
		return VehicleOperations.displayVehicleSpecification();
	}

	@GET
	@Path("ratings")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRatings() throws Exception {
		return VehicleOperations.displayRating();
	}

	@GET
	@Path("scores")
	@Produces(MediaType.TEXT_PLAIN)
	public String getScores() throws Exception {
		return VehicleOperations.displayScores();
	}
}