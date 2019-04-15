package com.routeplanner.engine;
import java.util.LinkedList;

import com.routeplanner.dm.INode;
import com.routeplanner.ex.NoJourneyFoundException;


public interface IRouteEnquiry
{
	public void execute(INode start, INode destination);
    
	public LinkedList getPredecessorList(INode iNode) throws NoJourneyFoundException;
    
	public int getShortestDistance(INode INode);
     
}
