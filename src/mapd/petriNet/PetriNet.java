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
	public Place createPlace(int tokens) {
		Place place = new Place(this.placeId, tokens);
		this.places.put(this.placeId, new Place(this.placeId, tokens));
		this.placeId++;
		return place;
	}
	
	/**
	 * User can delete a place by calling this function
	 * @param place
	 */
	@Override
	public void deletePlace(Place place) {
		Map<Place, ArrayList<Transition>> factoryArcs = this.arcFacctory.getArcs();
		// delete place in factory
		if (factoryArcs.containsKey(place)) {
			factoryArcs.remove(place);
			this.arcFacctory.setArcs(factoryArcs);
		}
		// delete arc in for all
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
	public Transition createTransition() {
		Transition transition = new Transition(this.transitionId);
		this.transitions.put(transition.getId(), transition);
		this.transitionId++;
		return transition;
	}
	
	/**
	 *  User can delete a transition by calling this function
	 * @param transition : to be deleted transition
	 */
	public void deleteTransition(Transition transition) {
		
		/**
		 * if a transition is going to be deleted, all arcs between 
		 * this transition and places which linked to this transition 
		 * will need to be deleted too
		 */
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
	private void deleteArc(int arcId) {
		Arc arc = this.arcs.get(arcId);
		// delete arc in this.arcs
		this.arcs.remove(arcId);
		// delete arc in factory
		this.deleteFactoryArc(arc);
		// delete this arc in place
		this.places.get(arc.getPlace().getId()).deletArc(arc);
		// delete this arc here
		this.arcs.remove(arcId);
	}
	
	/**
	 * User can delete an arc by calling this function
	 * @param arc	: to be deleted arc
	 */
	@Override
	public void deleteArc(Arc arc) {
		// delete arc in this.arcs
		this.arcs.remove(arc.getId());
		// delete arc in factory
		this.deleteFactoryArc(arc);
		// delete this arc in place
		this.places.get(arc.getPlace().getId()).deletArc(arc);
		// delete this arc here
		this.arcs.remove(arc.getId());
	}

	/**
	 * User can create an arc by calling this function, the following 
	 * createArc*() work similarly
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @return  a new ArcIn
	 */
//	@Override
	public ArcIn createArcIn(Transition transition, Place place){
		// if there is no conflict, we add new arc
		try {
			ArcIn arcIn = (ArcIn) arcFacctory.createArc("arcin", transition, place);
			arcs.put(arcIn.getArcId(), arcIn);
			return arcIn;
		} catch (NoThisTypeArcException e) {
			System.err.println(e.toString());
		}catch (ArcExistedException e) {
			System.err.println(e.toString());
		}
		return null;
	}
	

	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @param value			arc's value
	 * @return  a new ArcIn
	 */
	@Override
	public ArcIn createArcInWithValue(Transition transition, Place place, int value) {
		// create a new arcIn
		ArcIn arcIn = createArcIn(transition, place);
		// change its value
		arcIn.changeValue(value);
		arcs.put(arcIn.getArcId(), arcIn);
		return arcIn;
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
	public ArcOut createArcOut(Place place, Transition transition) {
		// if there is no conflict, we add new arc
		try {
			ArcOut arcOut = (ArcOut) arcFacctory.createArc("arcout", transition, place);
			arcs.put(arcOut.getId(), arcOut);
			return arcOut;
		} catch (ArcExistedException e) {
			System.err.println(e.toString());
		}catch (NoThisTypeArcException e) {
			System.err.println(e.toString());
		}
		
		return null;
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
	public ArcOut createArcOutWithValue(Place place, Transition transition, int value) {
		ArcOut arcOut = createArcOut(place, transition);
		arcOut.changeValue(value);
		arcs.put(arcOut.getId(), arcOut);
		return arcOut;
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
	public Zero createZero(Place place, Transition transition) {
		if (place.getTokens()!=0) {
			throw new RuntimeErrorException(null,"Place's tokens are more than zero");
		}
		try {
			Zero zero = (Zero) arcFacctory.createArc("zero", transition, place);
			arcs.put(zero.getId(), zero);
			return zero;
		} catch (ArcExistedException | NoThisTypeArcException e) {
			// TODO Auto-generated catch block
			System.err.println(e.toString());
		}
		return null;
	}
	
	/**
	 * 
	 * @param transition 	arc's transition
	 * @param place			arc's place
	 * @return  a new arc with type of Cleaner
	 */
	@Override
	public Cleaner createCleaner(Place place, Transition transition) {
		try {
			Cleaner cleaner = (Cleaner) arcFacctory.createArc("cleaner", transition, place);
			cleaner.changeValue(place.getTokens());
			arcs.put(cleaner.getId(), cleaner);
			return cleaner;
		} catch (ArcExistedException | NoThisTypeArcException e) {
			// TODO Auto-generated catch block
			System.err.println(e.toString());
		}
		return null;
	}
	
	/**
	 * Delete arcs in arcFactory
	 * @param arc
	 */
	public void deleteFactoryArc(Arc arc) {
		this.arcFacctory.deleteArc(arc.getPlace(), arc.getTransition());
	}
	
	
	public ArcFacctory getArcFactory() {
		return this.arcFacctory;
	}

}
