package org.pneditor.petrinet.models.binome10;

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;
import javax.management.RuntimeErrorException;
import org.pneditor.petrinet.models.binome10.Arc;
import org.pneditor.petrinet.models.binome10.ArcIn;
import org.pneditor.petrinet.models.binome10.ArcOut;
import org.pneditor.petrinet.models.binome10.Cleaner;
import org.pneditor.petrinet.models.binome10.Zero;
import org.pneditor.petrinet.models.binome10.ArcExistedException;
import org.pneditor.petrinet.models.binome10.NoThisTypeArcException;
import org.pneditor.petrinet.models.binome10.IPetriNet;
import org.pneditor.petrinet.models.binome10.Place;
import org.pneditor.petrinet.models.binome10.Transition;


public class PetriNet implements IPetriNet{
	
	/*
	 * increment place id and transition id and arc id
	 */
	private static int arcId = 1;
	private static int id=1;
	
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
	/*
	 * to store (placeid, transitions'ids)
	 */
	private Map<Integer, ArrayList<Integer>> checkArcConflict;
	
	/**
	 * constructor
	 */
	public PetriNet() {
		this.places = new HashMap<>();
		this.transitions = new HashMap<>();
		this.arcs = new HashMap<>();
		this.checkArcConflict = new HashMap<>();
	}
	
	
	/**
	 * create a new place
	 * 
	 * @return a new place
	 */
	public int createPlace() {
		// create a new place, 
//		Place place = new Place(this.placeId);
		Place place = new Place(this.id);
		// put this place into hashmap to store it
		this.places.put(place.getId(), place);
		// add placeId
		this.id++;
		// return placeId
		System.out.println("Create a new place which id = "+place.getId());
		return place.getId();
	}
	
	/**
	 * create a new place with specified tokens
	 * 
	 * @param tokens
	 * @return a new place
	 */
	@Override
	public int createPlace(int tokens) {
		int placeId = this.createPlace();
		this.places.get(placeId).setTokens(tokens);
		System.out.println("create a new place which id = "+placeId);
		return placeId;
	}
	
	/**
	 * User can delete a place by calling this function
	 * @param place
	 */
	@Override
	public void deletePlace(int placeId) {
		Place place = this.places.get(placeId);
		// delete place in checkArcConflict
		if (checkArcConflict.containsKey(placeId)) {
			checkArcConflict.remove(placeId);
		}
		// if we delete a place, we need to delete all arcs which link to this place
		for(Arc arc: place.getArcs()) {
			deleteArc(arc.getId());
		}
		this.places.remove(placeId);
		System.out.println("Deleted a place which id = "+placeId);
		
	}
	
	/**
	 * create a new transition
	 * 
	 * @return a new transition
	 */
	@Override
	public int createTransition() {
		Transition transition = new Transition(this.id);
		this.transitions.put(transition.getId(), transition);
		System.out.println("Created a new transition which id = "+transition.getId());
		this.id++;
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
		this.transitions.remove(transitionId);
		System.out.println("Deleted a transition with id = "+transitionId);
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
		/*
		 * we need to check if there is conflict in arcs
		 * when create an arc:
		 * 1. we need to add this arc to its relevant place
		 * 2. add this arc to transition
		 * 3. add this arc to arcs in petrinet
		 * 4. add this arc to checkArcConflict
		 */
		try {
			// if there is no conflict, we add new arc
			if (!conflict(transitionId, placeId)) {
				ArcIn arc = new ArcIn(this.arcId, this.transitions.get(transitionId), this.places.get(placeId));
				addArcFourSteps(arc, transitionId, placeId);
				this.arcId++;
				System.out.println("Created a new arc: "+arc.toString());
				return arc.getId();
			}
		} catch (ArcExistedException e) {
			System.err.println(e.toString());
		}
		return -1;
	}
	
	public boolean conflict(int transitionId, int placeId) throws ArcExistedException{
		// if place exists in arcs
		if (this.checkArcConflict.containsKey(placeId)) {
			// check if the transition in place's list
			if (this.checkArcConflict.get(placeId).contains(transitionId)) {
				// if the (place, transition) exists, there is a conflict
				throw new ArcExistedException(placeId, transitionId);
			}
			// if (place, transition) doesn't exist, there is no conflict
			return false;
		}else {
			// if there is not arc, there isn't conflict
			return false;
		}
	}
	
	public void addArcFourSteps(Arc arc, int transitionId, int placeId) {
		// 1. add this arc to place
		this.places.get(placeId).addArc(arc);
		// 2. add this arc to transition
		if (arc instanceof ArcIn) {
			this.transitions.get(transitionId).addArcIns((ArcIn) arc);
		}else {
			System.out.println(arc.toString());
			this.transitions.get(transitionId).addArcOuts((ArcOut) arc);
		}
		// 3. add this arc in petrinet
		arcs.put(arc.getId(), arc);
		// 4. add this arc to checkArcConflict
		if (this.checkArcConflict.containsKey(placeId)) {
			this.checkArcConflict.get(placeId).add(transitionId);
		}else {
			// if the place isn't in arcs list, we create a new transition list for the place, then add it into list
			ArrayList<Integer> transitionIds = new ArrayList<>();
			transitionIds.add(transitionId);
			this.checkArcConflict.put(placeId, transitionIds);
		}
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
			if (!conflict(transitionId, placeId)) {
				ArcOut arcOut = new ArcOut(this.arcId, this.places.get(placeId), this.transitions.get(transitionId));
				addArcFourSteps(arcOut, transitionId, placeId);
				this.arcId++;
				System.out.println("Created a new arc: "+arcOut.toString());
				return arcOut.getId();
			}
		} catch (ArcExistedException e) {
			System.err.println(e.toString());
		}
		
		return -1;
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
		System.out.println("Created a new arc: "+this.arcs.get(arcOutId).toString());
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
			throw new RuntimeErrorException(null, "Place's tokens are more than zero");
		}
		try {
			if (!conflict(transitionId, placeId)) {
				Zero zero = new Zero(this.arcId, this.places.get(placeId), this.transitions.get(transitionId));
				addArcFourSteps(zero, transitionId, placeId);
				this.arcId++;
				System.out.println("Created a new arc: "+zero.toString());
				return zero.getId();
			}
		} catch (ArcExistedException e) {
			System.err.println(e.toString());
		}
		return -1;
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
			if (!conflict(transitionId, placeId)) {
				Cleaner cleaner = new Cleaner(this.arcId, this.places.get(placeId), this.transitions.get(transitionId));
				cleaner.changeValue(this.places.get(placeId).getTokens());
				addArcFourSteps(cleaner, transitionId, placeId);
				this.arcId++;
				System.out.println("Created a new arc: "+cleaner.toString());
				return cleaner.getId();
			}
		} catch (ArcExistedException e) {
			System.err.println(e.toString());
		}
		return -1;
	}
	
	@Override
	public void deleteArc(int sourceId, int destId) {
		
		for(Integer key : this.arcs.keySet()) {
			System.out.println("arc key value:"+key);
			if (this.arcs.get(key).getPlace().getId()==sourceId&&this.arcs.get(key).getTransition().getId()==destId) {
				this.deleteArc(key);
				System.out.println("Deleted an arcout whose id = "+key);
			}
			if (this.arcs.get(key).getPlace().getId()==destId&&this.arcs.get(key).getTransition().getId()==sourceId) {
				this.deleteArc(key);
				System.out.println("Deleted an arcin whose id = "+key);
			}
		}	
	}
	
	/**
	 * It serves for this class only
	 * For deleting an arc, we need to 
	 * 1. delete this arc in its relevant place
	 * 2. delete this arc in its relevant transition
	 * 3. delete this arc in petrinet
	 * 4. delete this arc in checkArcConflict
	 * @param arcId
	 */

	private void deleteArc(int arcId) {
		Arc arc = this.arcs.get(arcId);
		int placeId = arc.getPlace().getId();
		int transitionId = arc.getTransition().getId();
		// 1. delete this arc in place
		this.places.get(placeId).deletArc(arc);
		// 2. delete this arc in its relevant transition
		arc.getTransition().deleteArc(arcId);
		// 3. delete this arc in petrinet
		this.arcs.remove(arcId);
		// 4. delete this arc in checkArcConflict
		this.checkArcConflict.get(placeId).remove(transitionId);
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
