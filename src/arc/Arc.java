package arc;

import place.Place;
import transition.Transition;

public class Arc {
	
	/**
	 * Arc links a place and a transition, it has a value default by 1
	 */
	private int value = 1;
	private Place place;
	private Transition transition;
	
	/**
	 * changes arc's value
	 * 
	 * @param value
	 */
	public void changeValue(int value) {
		if (value<0) {
			throw new ArithmeticException("Values can't be less than 0");
		}
		this.setValue(value);
	}

	/**
	 * To determine if this arc fits to all conditions
	 * 
	 * @return if this arc can be drawed, returns true, else false
	 */
	public boolean draw() {
		return true;
	}
	
	public int getValue() {
		return value;
	}

	public Place getPlace() {
		return place;
	}

	public Transition getTransition() {
		return transition;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}
}
