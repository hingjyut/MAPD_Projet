package PetriNet;
import Arc.ArcIn;
import Arc.ArcOut;
import Arc.Cleaner;
import Arc.Zero;
import IPetriNet.IPetriNet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import Arc.Arc;
import place.Place;
import transition.Transition;

public class PetriNet implements IPetriNet{
	
	private Map<Place, ArrayList<Transition>> arcs;
	
	public PetriNet() {
		// TODO Auto-generated constructor stub
		this.arcs = new HashMap<Place, ArrayList<Transition>>();
	}

	@Override
	public Place createPlace(int tokens) {
		// TODO Auto-generated method stub
		Place place = new Place(tokens);
		return place;
	}

	@Override
	public Transition createTransition() {
		// TODO Auto-generated method stub
		Transition transition = new Transition();
		return transition;
	}
	
	public void addArcToList(Transition transition, Place place) {
		// if the place is in the arcs list, we add the transition
		if (this.getArcs().containsKey(place)) {
			this.getArcs().get(place).add(transition);
		}else {
			// if the place isn't in arcs list, we create a new transition list for the place, then add it into list
			ArrayList<Transition> t = new ArrayList<>();
			t.add(transition);
			this.getArcs().put(place, t);
		}
	}
	
	public boolean conflict(Transition transition, Place place) {
		// if place exists in arcs
		if (this.getArcs().containsKey(place)) {
			// check if the transition in place's list
			if (this.getArcs().get(place).contains(transition)) {
				// if the (place, transition) exists, there is a conflict
				return true;
			}
			// if (place, transition) doesn't exist, there is no conflict
			return false;
		}else {
			// if there is not arc, there isn't conflict
			return false;
		}
	}

	
	@Override
	public ArcIn createArcIn(Transition transition, Place place){
		// TODO Auto-generated method stub
		// if there is no conflict, we add new arc
		if(!conflict(transition,place)) {
			ArcIn arcIn = new ArcIn(transition, place);
			addArcToList(transition, place);
			transition.addArcIn(arcIn);
			return arcIn;
		}else {
			throw new RuntimeErrorException(null, "This arc already exists");
		}
	}
	
	@Override
	public ArcIn createArcInWithValue(Transition transition, Place place, int value) {
		// TODO Auto-generated method stub
		// create a new arcIn
		ArcIn arcIn = createArcIn(transition, place);
		// change its value
		arcIn.changeValue(value);
		transition.addArcIn(arcIn);
		return arcIn;
	}

	@Override
	public ArcOut createArcOut(Place place, Transition transition) {
		// if there is no conflict, we add new arc
		if(!conflict(transition,place)) {
			ArcOut arcOut = new ArcOut(place, transition);
			// if the place is in the arcs list, we add the transition
			addArcToList(transition, place);
			transition.addArcOut(arcOut);
			return arcOut;
		}else {
			throw new RuntimeErrorException(null, "This arc already exists");
		}
	}

	@Override
	public ArcOut createArcOutWithValue(Place place, Transition transition, int value) {
		// TODO Auto-generated method stub
		ArcOut arcOut = createArcOut(place, transition);
		arcOut.changeValue(value);
		transition.addArcOut(arcOut);
		return arcOut;
	}

	@Override
	public Zero createZero(Place place, Transition transition) {
		// TODO Auto-generated method stub
		if (place.getTokens()!=0) {
			throw new RuntimeErrorException(null, "Place's tokens should be 0 for a zero arc");
		}else if (conflict(transition,place)) {
			// if there is a conflict
			throw new RuntimeErrorException(null, "This arc already exists");
		}else {
			// if place's tokens = 0 and there is no conflict, creates an arc
			Zero arcOut = new Zero(place, transition);
			addArcToList(transition, place);
			transition.addZero(arcOut);
			return arcOut;
		}
	}

	@Override
	public Cleaner createCleaner(Place place, Transition transition) {
		// TODO Auto-generated method stub
		// if there is no conflict, we add new arc
		if(!conflict(transition,place)) {
			Cleaner arcOut = new Cleaner(place, transition);
			arcOut.changeValue(place.getTokens());
			addArcToList(transition, place);
			transition.addCleaner(arcOut);
			return arcOut;
		}else {
			throw new RuntimeErrorException(null, "This arc already exists");
		}
	}

	@Override
	public void deletePlace(Place place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTransition(Transition transition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArc(Arc arc) {
		// TODO Auto-generated method stub
		
	}

	public Map<Place, ArrayList<Transition>> getArcs() {
		return arcs;
	}

	public void setArcs(Map<Place, ArrayList<Transition>> arcs) {
		this.arcs = arcs;
	}


}
