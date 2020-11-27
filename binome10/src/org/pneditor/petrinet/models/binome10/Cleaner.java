package org.pneditor.petrinet.models.binome10;
import org.pneditor.petrinet.models.binome10.Place;
import org.pneditor.petrinet.models.binome10.Transition;

public class Cleaner extends ArcOut{
	
	private int id;

	
	public Cleaner(int id, Place place, Transition transition) {
		super(id, place, transition);
	}
	
	/**
	 * To determine if place's tokens is less than 1, 
	 * there should be at least 1 token in place for arc cleaner
	 * 
	 * @return if it is: false; else true
	 */
	@Override
	public boolean trigger() {
		if (this.getPlace().getTokens()<1) {
			return false;
		}
		return true;
	}	

}
