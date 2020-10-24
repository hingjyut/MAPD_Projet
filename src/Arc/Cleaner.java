package Arc;
import place.Place;
import transition.Transition;

public class Cleaner extends ArcOut{

	
	public Cleaner(Place place, Transition transition) {
		// TODO Auto-generated constructor stub
		super(place, transition);
	}
	
	@Override
	public boolean draw() {
		if (this.getPlace().getTokens()<1) {
			return false;
		}
		return true;
	}
	

}
