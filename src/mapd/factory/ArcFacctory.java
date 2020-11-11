package mapd.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mapd.arc.Arc;
import mapd.arc.ArcIn;
import mapd.arc.ArcOut;
import mapd.arc.Cleaner;
import mapd.arc.Zero;
import mapd.exception.ArcExistedException;
import mapd.exception.NoThisTypeArcException;
import mapd.place.Place;
import mapd.transition.Transition;

public class ArcFacctory implements IArcFactory {
	
	private int arcId;
	private Map<Place, ArrayList<Transition>> arcs;
	
	
	public ArcFacctory() {
		this.arcs = new HashMap<>();
	}
	
	/**
	 * PetriNet uses this function to create all kinds of arcs
	 * 
	 * @param arcType: imply arc's type
	 * @param transition: arc's transition
	 * @param place: arc's place
	 * @return a new arc
	 */
	public Arc createArc(String arcType, Transition transition, Place place) throws ArcExistedException, NoThisTypeArcException{

		if (!conflict(transition, place)) {
			
			this.arcId++;
			
			if (arcType.equalsIgnoreCase("arcin")) {
				// create an arc of type ArcIn
				ArcIn arc = new ArcIn(this.arcId, transition, place);
				// add this arc to list
				addArcToList(transition, place);
				// add this arc to relative transition
				transition.addArcIns(arc);
				return arc;
				
			}else if (arcType.equalsIgnoreCase("arcout")) {
				
				ArcOut arc = new ArcOut(this.arcId, place, transition);
				transition.addArcOuts(arc);
				addArcToList(transition, place);
				return arc;
				
			}else if (arcType.equalsIgnoreCase("cleaner")) {
				Cleaner arc = new Cleaner(this.arcId, place, transition);
				arc.changeValue(place.getTokens());
				transition.addArcOuts(arc);
				addArcToList(transition, place);
				return arc;
			}else if (arcType.equalsIgnoreCase("zero")) {
				Zero arc = new Zero(this.arcId, place, transition);
				transition.addArcOuts(arc);
				addArcToList(transition, place);
				return arc;
				
			}else{
				throw new NoThisTypeArcException(arcType);
				
			}
		}else {
			throw new ArcExistedException(place.getId(), transition.getId());
		}
	}
	
	/**
	 * It is used to check if there is a loop between this transition and this place
	 * 
	 * @param transition
	 * @param place
	 * @return true or false
	 * @throws ArcExistedException
	 */
	public boolean conflict(Transition transition, Place place) throws ArcExistedException{
		// if place exists in arcs
		if (this.getArcs().containsKey(place.getId())) {
			// check if the transition in place's list
			if (this.getArcs().get(place.getId()).contains(transition.getId())) {
				// if the (place, transition) exists, there is a conflict
				throw new ArcExistedException(place.getId(), transition.getId());
			}
			// if (place, transition) doesn't exist, there is no conflict
			return false;
		}else {
			// if there is not arc, there isn't conflict
			return false;
		}
	}
	
	/**
	 * 
	 * @param transition 	arc's transition 
	 * @param place			arc's place
	 * 
	 */
	public void addArcToList(Transition transition, Place place) {
		// if the place is in the arcs list, we add the transition
		if (this.getArcs().containsKey(place)) {
			this.getArcs().get(place).add(transition);
		}else {
			// if the place isn't in arcs list, we create a new transition list for the place, then add it into list
			ArrayList<Transition> transitionIds = new ArrayList<>();
			transitionIds.add(transition);
			this.getArcs().put(place, transitionIds);
		}
	}
	
	public Map<Place,ArrayList<Transition>> getArcs() {
		return this.arcs;
	}
	
	public void setArcs(Map<Place, ArrayList<Transition>> arcs) {
		this.arcs = arcs;
	}
	
	@Override
	public void deleteArc(Place place, Transition transition) {
		this.arcs.get(place).remove(transition);
	}

}
