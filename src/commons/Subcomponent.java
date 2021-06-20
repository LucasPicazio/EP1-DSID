package commons;

import java.io.Serializable;

public class Subcomponent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Part subcomponent;
	private int quantity;
	
	public Subcomponent(Part subcomponent) {
		this.subcomponent = subcomponent;
		this.quantity = 1;
	}
	
	public Subcomponent(Part subcomponent, int quantity) {
		this.subcomponent = subcomponent;
		this.quantity = quantity;
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
