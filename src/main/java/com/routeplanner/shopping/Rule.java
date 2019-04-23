package com.routeplanner.shopping;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rule")
public class Rule extends DataModel {

	@Column(name="per_items")
	private int perActualItems;
	
	@Column(name="effective_items")
	private BigDecimal effectivePayItems;
	
	@Column(name="start_time")
	private LocalDateTime validStartTime;
	
	@Column(name="end_time")
	private LocalDateTime validEndTime;
	
	// for simplicity, ignore this for now.....
	// private Set<Long> relevantItems;
	
	
	public Rule() {
		
	}
	
	
	public Rule(int perActualItems, BigDecimal effectivePayItems, LocalDateTime validStartTime,
			LocalDateTime validEndTime) {
		this.perActualItems = perActualItems;
		this.effectivePayItems = effectivePayItems;
		//this.relevantItems = relevantItems;     // Set<Long> relevantItems, 
		this.validStartTime = validStartTime;
		this.validEndTime = validEndTime;
	}


	public int getPerActualItems() {
		return perActualItems;
	}


	public void setPerActualItems(int perActualItems) {
		this.perActualItems = perActualItems;
	}


	public BigDecimal getEffectivePayItems() {
		return effectivePayItems;
	}


	public void setEffectivePayItems(BigDecimal effectivePayItems) {
		this.effectivePayItems = effectivePayItems;
	}


//	public Set<Long> getRelevantItems() {
//		return relevantItems;
//	}
//
//
//	public void setRelevantItems(Set<Long> relevantItems) {
//		this.relevantItems = relevantItems;
//	}


	public LocalDateTime getValidStartTime() {
		return validStartTime;
	}


	public void setValidStartTime(LocalDateTime validStartTime) {
		this.validStartTime = validStartTime;
	}


	public LocalDateTime getValidEndTime() {
		return validEndTime;
	}


	public void setValidEndTime(LocalDateTime validEndTime) {
		this.validEndTime = validEndTime;
	}


	@Override
	public String toString() {
		return "Rule [perActualItems=" + perActualItems + ", effectivePayItems=" + effectivePayItems
				+ ", validStartTime=" + validStartTime + ", validEndTime=" + validEndTime + ", getPerActualItems()="
				+ getPerActualItems() + ", getEffectivePayItems()=" + getEffectivePayItems() + ", getValidStartTime()="
				+ getValidStartTime() + ", getValidEndTime()=" + getValidEndTime() + ", getId()=" + getId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
