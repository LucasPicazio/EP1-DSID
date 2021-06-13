package server;

public class Subcomponent {
	private Part subcomponent;
	private int quantity;
	
	public Subcomponent(Part subcomponent) {
		this.subcomponent = subcomponent;
		this.quantity = 1;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void addQuantity() {
		this.quantity++;
	}
	
	public void removeQuantity() {
		this.quantity--;
	}
	
	public Part getSubcomponent() {
		return subcomponent;
	}
	
	
}
