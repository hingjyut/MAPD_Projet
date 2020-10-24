package arc;

import java.util.Map.Entry;

import arc.Arc;

import java.util.Vector;

import place.Place;
import transition.Transition;

/**
 * 
 * @author hingjyutcheung
 *
 */
public class ArcIn extends Arc{
	/**
	 * Arc value is 1 by default
	 */
	private int value = 1;
	private Transition transition;
	private Place place;
	
	public ArcIn(Transition trans, Place place) {
		// TODO Auto-generated constructor stub
		super();
		this.place = place;
		this.transition = trans;
	}
	
	
	/**
	 * ArcIn is always drawable
	 * 
	 * @return true
	 */
	@Override
	public boolean draw() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "ArcIn "
				+ ", value=" + value;
				
	}
	
	
	
}
