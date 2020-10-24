package Arc;

import place.Place;
import transition.Transition;

public class Arc {
	
	private int value = 1;
	private Place place;
	private Transition transition;

	public void changeValue(int value) {
		if (value<0) {
			throw new ArithmeticException("Values can't be less than 0");
		}
		this.setValue(value);
	}

	//	public boolean draw(int tokens);
	public boolean draw() {
		return false;
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
