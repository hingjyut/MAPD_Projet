package arc;
import place.Place;
import transition.Transition;

public class Cleaner extends ArcOut{

	
	public Cleaner(Place place, Transition transition) {
		// TODO Auto-generated constructor stub
		super(place, transition);
	}
	
	/**
	 * To determine if place's tokens is less than 1, 
	 * there should be at least 1 token in place for arc cleaner
	 * 
	 * @return if it is: false; else true
	 */
	@Override
	public boolean draw() {
		if (this.getPlace().getTokens()<1) {
			return false;
		}
		return true;
	}
	

}
