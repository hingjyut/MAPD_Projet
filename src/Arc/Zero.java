package Arc;
import place.Place;
import transition.Transition;

public class Zero extends ArcOut{
	
	private int value = 0;
	
	public Zero(Place place, Transition transition){
		super(place,transition);
	}
	
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
