package VehicleServices;

public class Supplier implements Comparable<Supplier>{
	
	private String supplier;
	private Double rating;
	
	public Supplier(String supp, String rating) {
		this.setSupplier(supp);
		this.setRating(Double.valueOf(rating));
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Override
	public int compareTo(Supplier o) {
		return this.getRating().compareTo(o.getRating());
	}
	
	@Override
	public String toString() {
		return this.getSupplier() + " - " + this.getRating();
	}
}
