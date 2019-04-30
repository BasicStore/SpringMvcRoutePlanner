package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractItem extends DataModel {
	
	@Column(name="num_units")
	private int numUnits;
	
	// an item is always open until it is explicitly closed
	private boolean open = true;
	
	
	public AbstractItem() {
		
	}

	public AbstractItem(int numUnits) {
		this.numUnits = numUnits;
	}


	public int getNumUnits() {
		return numUnits;
	}


	public void setNumUnits(int numUnits) {
		this.numUnits = numUnits;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
}
