package Arc;
import Arc.Arc;
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
	
	@Override
	public void changeValue(int value) {
		// TODO Auto-generated method stub
		if (value<0) {
			throw new ArithmeticException("Values can't be less than 0");
		}
		this.setValue(value);
	}
	
	@Override
	public boolean draw() {
		// TODO Auto-generated method stub
		if (this.getPlace().getTokens()<this.getValue()) {
			return false;
		}
		return true;
	}
	
}
