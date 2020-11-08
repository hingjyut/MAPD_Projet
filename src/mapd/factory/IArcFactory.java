package mapd.factory;

import mapd.arc.Arc;
import mapd.exception.ArcExistedException;
import mapd.exception.NoThisTypeArcException;
import mapd.place.Place;
import mapd.transition.Transition;

public interface IArcFactory {
	Arc createArc(String arcType, Transition transition, Place place) throws NoThisTypeArcException, ArcExistedException;
	public void deleteArc(Place place, Transition transition);
}
