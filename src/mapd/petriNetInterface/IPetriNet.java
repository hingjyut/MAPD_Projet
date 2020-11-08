package mapd.petriNetInterface;
import mapd.arc.Arc;
import mapd.arc.ArcIn;
import mapd.arc.ArcOut;
import mapd.arc.Cleaner;
import mapd.arc.Zero;
import mapd.place.Place;
import mapd.transition.Transition;

/**
 * 
 * PetriNet interface
 *
 */
public interface IPetriNet {

	public Place createPlace(int tokens);
	
	public Transition createTransition();
	
	public ArcIn createArcIn(Transition transition, Place place);
	
	public ArcIn createArcInWithValue(Transition transition, Place place, int value);
	
	public ArcOut createArcOut(Place place, Transition transition) ;
	
	public ArcOut createArcOutWithValue(Place place, Transition transition, int value);
	
	public Zero createZero(Place place, Transition transition);
	
	public Cleaner createCleaner(Place place, Transition transition);
	
	public void deletePlace(Place place);
	
	public void deleteTransition(Transition transition);
	
	public void deleteArc(Arc arc);

}
