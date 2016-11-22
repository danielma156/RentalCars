package com.dma.rentalcars.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.dma.rentalcars.rest.VehicleOperations;

public class VehicleOperationsTest {

	@Test(expected=NullPointerException.class)
	public void testReadJSON() throws Exception {
		VehicleOperations.readJSON("nowhere");
	}
	
	@Test(expected=NullPointerException.class)
	public void testReadJSONNullInput() throws Exception {
		VehicleOperations.readJSON(null);
	}

	@Test
	public void testDisplayVehiclesWithPrices() throws Exception {
		assertEquals(loadExpectedResult("cars.txt"), VehicleOperations.displayVehiclesWithPrices());
	}

	@Test
	public void testDisplayVehicleSpecification() throws Exception {
		assertEquals(loadExpectedResult("spec.txt"), VehicleOperations.displayVehicleSpecification());
	}

	@Test
	public void testDisplayRating() throws Exception {
		assertEquals(loadExpectedResult("ratings.txt"), VehicleOperations.displayRating());
	}

	@Test
	public void testDisplayScores() throws Exception {
		assertEquals(loadExpectedResult("scores.txt"), VehicleOperations.displayScores());
	}
	
	// loads in the expected result as a String with the relevant newlines
	private String loadExpectedResult(String filename) throws IOException, URISyntaxException {
		URL path = VehicleOperationsTest.class.getResource(filename);
		List<String> stream = Files.lines(Paths.get(path.toURI())).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder(); 
		for (String s : stream) {
			sb.append(s + "\n");
		}
		return sb.toString();
	}
}
