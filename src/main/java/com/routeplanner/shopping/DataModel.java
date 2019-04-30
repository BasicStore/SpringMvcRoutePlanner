package com.routeplanner.shopping;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class DataModel 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	public DataModel() 
	{
		
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
}
