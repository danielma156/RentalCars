package VehicleServices;

public class Vehicle implements Comparable<Vehicle> {

//  private String sipp;
  private SIPP sipp;
  private String name;
  private double price;
  private Supplier supplier;
  private double vehicleScore;
  private double totalScore;

  public Vehicle(String sipp, String name, double price, String s, String rating){
    this.setSipp(new SIPP(sipp));
    this.setName(name);
    this.setPrice(price);
    this.setSupplier(new Supplier(s, rating));
  }

  public SIPP getSipp() {
    return sipp;
  }
  public void setSipp(SIPP sipp) {
    this.sipp = sipp;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = Double.valueOf(price);
  }

  public Supplier getSupplier() {
	return supplier;
  }
	
  public void setSupplier(Supplier s) {
	this.supplier = s;
  }
  
  @Override
  public String toString(){
    return this.getName() + " - " + this.getPrice();
  }

  @Override
  public int compareTo(Vehicle o) {
	return this.getPrice().compareTo(o.getPrice());
  }

public Double getTotalScore() {
	return totalScore;
}

public void setTotalScore(double totalScore) {
	this.totalScore = totalScore;
}

public double getVehicleScore() {
	return vehicleScore;
}

public void setVehicleScore(double vehicleScore) {
	this.vehicleScore = vehicleScore;
}

}
