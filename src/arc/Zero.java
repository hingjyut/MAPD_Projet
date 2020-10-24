package arc;
import place.Place;
import transition.Transition;

public class Zero extends ArcOut{
	
	private int value;
	
	public Zero(Place place, Transition transition){
		super(place,transition);
		this.value = 0;
	}
	
	/**
	 * To determine if place's tokens is 0
	 * 
	 * @return if it is: true; else false
	 */
	@Override
	public boolean draw() {
		if (this.getPlace().getTokens()==0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void changeValue(int value) {
		System.out.println("Value in zero arc can't not be changed");
	}

}
