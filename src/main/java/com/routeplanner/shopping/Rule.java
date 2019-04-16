package com.routeplanner.shopping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Rule {

	private int perActualItems;
	private BigDecimal effectivePayItems;
	private Set<Long> relevantItems;
	private LocalDateTime validStartTime;	
	private LocalDateTime validEndTime;
	
	public Rule() {
		
	}


	public Rule(int perActualItems, BigDecimal effectivePayItems, Set<Long> relevantItems, LocalDateTime validStartTime,
			LocalDateTime validEndTime) {
		this.perActualItems = perActualItems;
		this.effectivePayItems = effectivePayItems;
		this.relevantItems = relevantItems;
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


	public Set<Long> getRelevantItems() {
		return relevantItems;
	}


	public void setRelevantItems(Set<Long> relevantItems) {
		this.relevantItems = relevantItems;
	}


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

}
