package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.routeplanner.dm.DataModel;

@MappedSuperclass
public abstract class AbstractItem extends DataModel {
	
	@Column(name="num_units")
	private int numUnits;
	
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
	
}
