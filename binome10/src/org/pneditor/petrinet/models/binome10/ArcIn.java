package org.pneditor.petrinet.models.binome10;

import java.util.Map.Entry;

import org.pneditor.petrinet.models.binome10.Arc;
import org.pneditor.petrinet.models.binome10.Place;
import org.pneditor.petrinet.models.binome10.Transition;

import java.util.Vector;

/**
 * 
 * @author hingjyutcheung
 *
 */
public class ArcIn implements Arc{
	/**
	 * Arc value is 1 by default
	 */
	private int value = 1;
	private Place place;
	private Transition transition;
	private int arcId;
	
	public ArcIn(int arcId, Transition transition, Place place) {
		// TODO Auto-generated constructor stub
		this.arcId = arcId;
		this.transition = transition;
		this.place = place;
	}
	
	@Override
	public void changeValue(int value) {
		this.value = value;
	}

	// ArcIn can always be triggered
	@Override
	public boolean trigger() {
		return true;
	}

	public int getValue() {
		return this.value;
	}
	
	@Override
	public int getId() {
		return this.arcId;
	}

	@Override
	public Transition getTransition() {
		return this.transition;
	}
	
	@Override
	public Place getPlace() {
		return this.place;
	}
	
	@Override
	public String toString() {
		return "ArcIn: " + this.arcId 
				+ ", TransitionId: "+this.getTransition().getId()
				+ ", PlaceId: "+ this.getPlace().getId()
				+ ", value=" + this.getValue();
				
	}

	@Override
	public boolean isArcIn() {
		
		return true;
	}
	
}
