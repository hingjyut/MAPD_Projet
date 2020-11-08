package mapd.arc;
import javax.management.RuntimeErrorException;

import mapd.arc.Arc;
import mapd.place.Place;
import mapd.transition.Transition;

public class ArcOut implements Arc{
	
	private int value = 1;
	private Place place;
	private Transition transition;
	private int id;
	
	/**
	 * ArcOut constructor
	 * @param id
	 * @param place
	 * @param transition
	 */
	public ArcOut(int id, Place place, Transition transition) {
		this.id = id;
		this.transition = transition;
		this.place = place;
	}

	@Override
	public void changeValue(int value) {
		if (value>0) {
			this.value = value;
		}else {
			throw new RuntimeErrorException(null, "Arc's value can't be less than 0");
		}
	}

	@Override
	public boolean trigger() {
		if (this.getValue()>=this.getPlace().getTokens()) {
			return true;
		}
		return false;
	}

	@Override
	public int getId() {
		return this.id;
	}
	
	
	public int getValue() {
		return this.value;
	}

	@Override
	public Place getPlace() {
		return this.place;
	}

	@Override
	public Transition getTransition() {
		return this.transition;
	}

	@Override
	public String toString() {
		return "ArcOut: " + this.id 
				+ ", TransitionId: "+this.getTransition().getId()
				+ ", PlaceId: "+ this.getPlace().getId()
				+ ", value=" + this.getValue();
				
	}

	
	
	
	
}
