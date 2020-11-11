package mapd.petriNet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import mapd.arc.Arc;
import mapd.arc.ArcIn;
import mapd.arc.ArcOut;
import mapd.arc.Cleaner;
import mapd.arc.Zero;
import mapd.exception.ArcExistedException;
import mapd.exception.NoThisTypeArcException;
import mapd.factory.ArcFacctory;
import mapd.petriNetInterface.IPetriNet;
import mapd.place.Place;
import mapd.transition.Transition;


public class PetriNet implements IPetriNet{
	
	private int placeId;
	private int transitionId;
	/*
	 * a factory to create arc
	 */
	private ArcFacctory arcFacctory;
	/*
	 * to store all places in petriNet
	 */
	private Map<Integer, Place> places;
	/*
	 * to store all transitions in petriNet
	 */
	private Map<Integer, Transition> transitions;
	/*
	 * to store all arcs in petriNet
	 */
	private Map<Integer, Arc> arcs;
	
	public PetriNet() {
		this.placeId = 1;
		this.transitionId = 1;
		this.arcFacctory = new ArcFacctory();
		this.places = new HashMap<>();
		this.transitions = new HashMap<>();
		this.arcs = new HashMap<>();
	}
	
	/**
	 * create a new place
	 * 
	 * @param tokens
	 * @return a new place
	 */
	@Override
	public int createPlace(int tokens) {
		Place place = new Place(this.placeId, tokens);
		this.places.put(this.placeId, new Place(this.placeId, tokens));
		this.placeId++;
		return place.getId();
	}
	
	/**
	 * User can delete a place by calling this function
	 * @param place
	 */
	@Override
	public void deletePlace(int placeId) {
		Place place = this.places.get(placeId);
		// delete place in factory
		if (this.arcFacctory.getArcs().containsKey(place)) {
			this.arcFacctory.getArcs().remove(place);
		}
		// if we delete a place, we need to delete all arcs which link to this place
		for(Arc arc: place.getArcs()) {
			deleteArc(arc.getId());
		}
		this.places.remove(place.getId());
		
	}
	
	/**
	 * create a new transition
	 * 
	 * @return a new transition
	 */
	@Override
	public int createTransition() {
		Transition transition = new Transition(this.transitionId);
		this.transitions.put(transition.getId(), transition);
		this.transitionId++;
		return transition.getId();
	}
	
	/**
	 *  User can delete a transition by calling this function
	 * @param transition : to be deleted transition
	 */
	@Override
	public void deleteTransition(int transitionId) {
		
		/**
		 * if a transition is going to be deleted, all arcs between 
		 * this transition and places which linked to this transition 
		 * will need to be deleted too
		 */
		Transition transition = this.transitions.get(transitionId);
		for(Integer arcId: transition.getArcIns().keySet()) {
			deleteArc(arcId);
		}
		
		for(Integer arcId: transition.getArcOuts().keySet()) {
			deleteArc(arcId);
		}
		
		// delete this transition
		this.transitions.remove(transition.getId());
	}
	
	/**
	 * It serves for this class only
	 * @param arcId
	 */
	@Override
	public void deleteArc(int arcId) {
		Arc arc = this.arcs.get(arcId);
		// delete arc in this.arcs
		this.arcs.remove(arcId);
		// delete arc in factory
		this.arcFacctory.deleteArc(arc.getPlace(), arc.getTransition());
		// delete this arc in place
		this.places.get(arc.getPlace().getId()).deletArc(arc);
		// delete this arc here
		this.arcs.remove(arcId);
	}
	
	/**
	 * User can create an arc by calling this function, the following 
	 * createArc*() work similarly
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @return  a new ArcIn
	 */
	@Override
	public int createArcIn(int transitionId, int placeId){
		// if there is no conflict, we add new arc
		try {
			ArcIn arcIn = (ArcIn) arcFacctory.createArc("arcin", this.transitions.get(transitionId), this.places.get(placeId));
			arcs.put(arcIn.getId(), arcIn);
			return arcIn.getId();
		} catch (NoThisTypeArcException e) {
			System.err.println(e.toString());
		}catch (ArcExistedException e) {
			System.err.println(e.toString());
		}
		return 0;
	}
	

	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @param value			arc's value
	 * @return  a new ArcIn
	 */
	@Override
	public int createArcInWithValue(int transitionId, int placeId, int value) {
		// create a new arcIn
		int arcInId = createArcIn(transitionId, placeId);
		// change its value
		this.arcs.get(arcInId).changeValue(value);
		return arcInId;
	}
	
	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @param value			arc's value
	 * @return  a new ArcOut
	 * @throws ArcExistedException 
	 */
	@Override
	public int createArcOut(int placeId, int transitionId) {
		// if there is no conflict, we add new arc
		try {
			ArcOut arcOut = (ArcOut) arcFacctory.createArc("arcout", this.transitions.get(transitionId), this.places.get(placeId));
			arcs.put(arcOut.getId(), arcOut);
			return arcOut.getId();
		} catch (ArcExistedException e) {
			System.err.println(e.toString());
		}catch (NoThisTypeArcException e) {
			System.err.println(e.toString());
		}
		
		return 0;
	}

	
	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @param value			arc's value
	 * @return  a new ArcOut
	 * @throws ArcExistedException 
	 */
	@Override
	public int createArcOutWithValue(int placeId, int transitionId, int value) {
		int arcOutId = createArcOut(placeId, transitionId);
		this.arcs.get(arcOutId).changeValue(value);
		return arcOutId;
	}
	
	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @return  a new arc with type of Zero
	 * @throws NoThisTypeArcException 
	 * @throws ArcExistedException 
	 */
	@Override
	public int createZero(int placeId, int transitionId) {
		Place place = this.places.get(placeId);
		if (place.getTokens()!=0) {
			throw new RuntimeErrorException(null,"Place's tokens are more than zero");
		}
		try {
			Zero zero = (Zero) arcFacctory.createArc("zero", this.transitions.get(transitionId), place);
			arcs.put(zero.getId(), zero);
			return zero.getId();
		} catch (ArcExistedException | NoThisTypeArcException e) {
			// TODO Auto-generated catch block
			System.err.println(e.toString());
		}
		return 0;
	}
	
	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @return  a new arc with type of Cleaner
	 */
	@Override
	public int createCleaner(int placeId, int transitionId) {
		try {
			Transition transition = this.transitions.get(transitionId);
			Place place = this.places.get(placeId);
			Cleaner cleaner = (Cleaner) arcFacctory.createArc("cleaner", transition, place);
			cleaner.changeValue(place.getTokens());
			arcs.put(cleaner.getId(), cleaner);
			return cleaner.getId();
		} catch (ArcExistedException | NoThisTypeArcException e) {
			// TODO Auto-generated catch block
			System.err.println(e.toString());
		}
		return 0;
	}
	
	public ArcFacctory getArcFactory() {
		return this.arcFacctory;
	}

	public Map<Integer, Place> getPlaces() {
		return places;
	}

	public Map<Integer, Transition> getTransitions() {
		return transitions;
	}

	public Map<Integer, Arc> getArcs() {
		return arcs;
	}


}
