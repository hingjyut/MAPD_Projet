package petriNet_interface;
import arc.Arc;
import arc.ArcIn;
import arc.ArcOut;
import arc.Cleaner;
import arc.Zero;
import place.Place;
import transition.Transition;

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
	public ArcOut createArcOut(Place place, Transition transition);
	public ArcOut createArcOutWithValue(Place place, Transition transition, int value);
	public Zero createZero(Place place, Transition transition);
	public Cleaner createCleaner(Place place, Transition transition); 
	public void deletePlace(Place place);
	public void deleteTransition(Transition transition);
	public void deleteArc(Arc arc);

}
