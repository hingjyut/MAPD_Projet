package mapd.arc;

import mapd.place.Place;
import mapd.transition.Transition;

public interface Arc {
	
	public int getId();
	
	public Place getPlace();
	
	public Transition getTransition();
	
	/**
	 * changes arc's value
	 * 
	 * @param value
	 */
	public void changeValue(int value);

	/**
	 * To determine if this arc fits to all conditions
	 * 
	 * @return if this arc can be triggered, returns true, else false
	 */
	public boolean trigger();
	
	public int getValue();
	
	
}
