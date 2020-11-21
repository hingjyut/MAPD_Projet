package org.pneditor.petrinet.models.binome10;
import org.pneditor.petrinet.models.binome10.Place;
import org.pneditor.petrinet.models.binome10.Transition;

public class Zero extends ArcOut{
	
	private int value;
	private int id;
	
	public Zero(int id, Place place, Transition transition){
		super(id, place,transition);
		this.value = 0;
	}
	
	/**
	 * To determine if place's tokens is 0
	 * 
	 * @return if it is: true; else false
	 */
	@Override
	public boolean trigger() {
		if (this.getPlace().getTokens()==0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void changeValue(int value) {
		System.out.println("Value in zero arc can't not be changed");
	}

}
