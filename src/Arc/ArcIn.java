package Arc;

import java.util.Map.Entry;
import java.util.Vector;

import Arc.Arc;
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
	
	@Override
	public boolean draw() {
		// TODO Auto-generated method stub
		if (this.getPlace().getTokens()<this.getValue()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ArcIn "
				+ ", value=" + value;
				
	}
	
	
	
}
