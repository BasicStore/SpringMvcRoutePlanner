package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractItem extends DataModel {
	
	private boolean open;
	
	@Column(name="num_units")
	private int numUnits;
	
	
	public AbstractItem() {
		
	}

	public AbstractItem(boolean open, int numUnits) {
		this.open = open;
		this.numUnits = numUnits;
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}


	public int getNumUnits() {
		return numUnits;
	}


	public void setNumUnits(int numUnits) {
		this.numUnits = numUnits;
	}

	@Override
	public String toString() {
		return "AbstractItem [open=" + open + ", numUnits=" + numUnits + "]";
	}
	
	
}
