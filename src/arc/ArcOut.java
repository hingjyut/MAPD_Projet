package arc;
import arc.Arc;
import place.Place;
import transition.Transition;

public class ArcOut extends Arc{
	private Transition transition;
	private Place place;
	private int value = 1;


	public ArcOut(Place place, Transition transition) {
		// TODO Auto-generated constructor stub
		super();
		this.transition = transition;
		this.place = place;
	}
	
	/**
	 * To determine if this arc's value is less than place's tokens
	 * 
	 * @return if it is: true; else false
	 */
	@Override
	public boolean draw() {
		// TODO Auto-generated method stub
		if (this.getPlace().getTokens()<this.getValue()) {
			return false;
		}
		return true;
	}
	
}
