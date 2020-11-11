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
	
	public int createPlace(int tokens);
	
	public int createTransition();
	
	public int createArcIn(int transitionId, int placeId);
	
	public int createArcInWithValue(int transitionId, int placeId, int value);
	
	public int createArcOut(int placeId, int transitionId) ;
	
	public int createArcOutWithValue(int placeId, int transitionId, int value);
	
	public int createZero(int placeId, int transitionId);
	
	public int createCleaner(int placeId, int transitionId);
	
	public void deletePlace(int placeId);
	
	public void deleteTransition(int transitionId);
	
	public void deleteArc(int arcId);

}
