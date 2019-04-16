package com.routeplanner.shopping;

public abstract class AbstractItem {
	
	private boolean open;
	
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
	
}
